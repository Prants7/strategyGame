package hedgehogs.strategyGame.gameLogic.factionActionInterface;

import hedgehogs.strategyGame.gameLogic.agents.base.Agent;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.buildOfficeAction.BuildOfficeAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landClearAction.LandClearAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landPurchaseAction.LandPurchaseAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.seizeControlFromLocalsAction.SeizeControlFromLocalsAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper.TimedActionWaitList;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper.TimedActionWrapper;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper.TimedActionWrapperImp;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFactionActionInterface implements FactionActionInterface {
    private FactionAction landPurchaseAction;
    private FactionAction landClearAction;
    private TimedActionWaitList timedActionWaitList;
    private FactionAction buildOfficeAction;
    private FactionAction seizeControlAction;
    private List<FactionAction> actionList;

    @Autowired
    public AbstractFactionActionInterface(LandPurchaseAction landPurchaseAction,
                                          LandClearAction landClearAction,
                                          TimedActionWaitList timedActionWaitList,
                                          BuildOfficeAction buildOfficeAction,
                                          SeizeControlFromLocalsAction seizeControlAction) {
        this.landPurchaseAction = landPurchaseAction;
        this.landClearAction = landClearAction;
        this.timedActionWaitList = timedActionWaitList;
        this.buildOfficeAction = buildOfficeAction;
        this.seizeControlAction = seizeControlAction;
        this.makeActionList();
    }

    private void makeActionList() {
        this.actionList = new ArrayList<>();
        this.actionList.add(this.landClearAction);
        this.actionList.add(this.landPurchaseAction);
        this.actionList.add(this.buildOfficeAction);
        this.actionList.add(this.seizeControlAction);
    }

    @Override
    public void performAdminLandAssign(Faction forFaction, Province targetProvince) {
        adminPerformAction(this.landPurchaseAction, forFaction, targetProvince);
    }


    protected void adminPerformAction(FactionAction action, Faction targetFaction, Province targetProvince) {
        action.forceDoAction(targetFaction, targetProvince);
    }

    @Override
    public void performLandPurchase(Agent agent) {
        this.performStandardVersionOfAction(this.landPurchaseAction, agent);
    }

    protected void performStandardVersionOfAction(FactionAction action, Agent agent) {
        if(!action.allowedToDoAction(agent)) {
            return;
        }
        TimedActionWrapper newAction = this.getNewTimedAction(action, agent);
        this.timedActionWaitList.addNewTimedAction(newAction);
    }

    @Override
    public void performLandClearance(Agent agent) {
        this.performStandardVersionOfAction(this.landClearAction, agent);

    }

    @Override
    public void performFamilyHallBuild(Agent agent) {
        this.performStandardVersionOfAction(this.buildOfficeAction, agent);
    }

    @Override
    public void performAdminFamilyHallBuild(Faction callingFaction, Province targetProvince) {
        this.adminPerformAction(this.buildOfficeAction, callingFaction, targetProvince);
    }

    @Override
    public void seizeControlInCity(Agent agent) {
        this.performStandardVersionOfAction(this.seizeControlAction, agent);
    }

    public FactionAction getLandPurchaseAction() {
        return landPurchaseAction;
    }

    public FactionAction getLandClearAction() {
        return landClearAction;
    }

    private TimedActionWrapper getNewTimedAction(FactionAction actionBase, Agent agent) {
        TimedActionWrapper newAction = actionBase.getActionAsTimedElement(agent);
        return newAction;
    }

    @Override
    public List<FactionAction> getListOfUsableFactionActions() {
        return new ArrayList<>(this.actionList);
    }

    @Override
    public boolean tryToPerformActionWithAgent(FactionAction desiredAction, Agent targetAgent) {
        this.performStandardVersionOfAction(desiredAction, targetAgent);
        return true;
    }
}
