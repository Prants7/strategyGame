package hedgehogs.strategyGame.gameLogic.factionActionInterface;

import hedgehogs.strategyGame.gameLogic.agents.base.Agent;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.FactionActionInput;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.buildOfficeAction.BuildOfficeAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landClearAction.LandClearAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landPurchaseAction.LandPurchaseAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.moveAgentAction.MoveAgentAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.seizeControlFromLocalsAction.SeizeControlFromLocalsAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper.TimedActionWaitList;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper.TimedActionWrapper;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper.TimedActionWrapperImp;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FactionActionInterfaceImp implements FactionActionInterface {
    private FactionAction landPurchaseAction;
    private FactionAction landClearAction;
    private TimedActionWaitList timedActionWaitList;
    private FactionAction buildOfficeAction;
    private FactionAction seizeControlAction;
    private List<FactionAction> actionList;
    private MoveAgentAction moveAgentAction;

    @Autowired
    public FactionActionInterfaceImp(LandPurchaseAction landPurchaseAction,
                                     LandClearAction landClearAction,
                                     TimedActionWaitList timedActionWaitList,
                                     BuildOfficeAction buildOfficeAction,
                                     SeizeControlFromLocalsAction seizeControlAction,
                                     MoveAgentAction moveAgentAction) {
        this.landPurchaseAction = landPurchaseAction;
        this.landClearAction = landClearAction;
        this.timedActionWaitList = timedActionWaitList;
        this.buildOfficeAction = buildOfficeAction;
        this.seizeControlAction = seizeControlAction;
        this.makeActionList();
        this.moveAgentAction = moveAgentAction;
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
    public void performLandPurchase(FactionActionInput input) {
        this.performStandardVersionOfAction(this.landPurchaseAction, input);
    }

    protected void performStandardVersionOfAction(FactionAction action, FactionActionInput input) {
        if(!action.allowedToDoAction(input)) {
            return;
        }
        TimedActionWrapper newAction = this.getNewTimedAction(action, input);
        this.timedActionWaitList.addNewTimedAction(newAction);
    }

    @Override
    public void performLandClearance(FactionActionInput input) {
        this.performStandardVersionOfAction(this.landClearAction, input);

    }

    @Override
    public void performFamilyHallBuild(FactionActionInput input) {
        this.performStandardVersionOfAction(this.buildOfficeAction, input);
    }

    @Override
    public void performAdminFamilyHallBuild(Faction callingFaction, Province targetProvince) {
        this.adminPerformAction(this.buildOfficeAction, callingFaction, targetProvince);
    }

    @Override
    public void seizeControlInCity(FactionActionInput input) {
        this.performStandardVersionOfAction(this.seizeControlAction, input);
    }

    public FactionAction getLandPurchaseAction() {
        return landPurchaseAction;
    }

    public FactionAction getLandClearAction() {
        return landClearAction;
    }

    private TimedActionWrapper getNewTimedAction(FactionAction actionBase, FactionActionInput input) {
        TimedActionWrapper newAction = actionBase.getActionAsTimedElement(input);
        return newAction;
    }

    @Override
    public List<FactionAction> getListOfUsableFactionActions() {
        return new ArrayList<>(this.actionList);
    }

    @Override
    public boolean tryToPerformActionWithAgent(FactionAction desiredAction, FactionActionInput input) {
        this.performStandardVersionOfAction(desiredAction, input);
        return true;
    }

    @Override
    public MoveAgentAction getMoveAgentAction() {
        return this.moveAgentAction;
    }
}
