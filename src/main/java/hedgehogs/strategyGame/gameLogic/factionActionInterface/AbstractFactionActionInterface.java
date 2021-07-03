package hedgehogs.strategyGame.gameLogic.factionActionInterface;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.buildOfficeAction.BuildOfficeAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landClearAction.LandClearAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landPurchaseAction.LandPurchaseAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.seizeControlFromLocalsAction.SeizeControlFromLocalsAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper.TimedActionWaitList;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper.TimedActionWrapper;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractFactionActionInterface implements FactionActionInterface {
    private FactionAction landPurchaseAction;
    private FactionAction landClearAction;
    private TimedActionWaitList timedActionWaitList;
    private FactionAction buildOfficeAction;
    private FactionAction seizeControlAction;

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
    }

    @Override
    public void performAdminLandAssign(Faction forFaction, Province targetProvince) {
        this.landPurchaseAction.forceDoAction(forFaction, targetProvince, 1);
    }

    protected void performFactionActionBaseTypeAction(Faction targetFaction, FactionAction action, Province targetProvince, int amount) {
        if(!action.allowedToDoAction(targetFaction, targetProvince, amount)) {
            return;
        }
        action.doAction(targetFaction, targetProvince, amount);
    }

    @Override
    public void performLandPurchase(Faction callingFaction, Province targetProvince) {
        this.performFactionActionBaseTypeTimedAction(callingFaction, this.landPurchaseAction, targetProvince, 1);
    }

    protected void performFactionActionBaseTypeTimedAction(Faction targetFaction, FactionAction action, Province targetProvince, int amount) {
        if(!action.allowedToDoAction(targetFaction, targetProvince, amount)) {
            return;
        }
        TimedActionWrapper newAction = this.getNewTimedAction(action, targetFaction, targetProvince, 1);
        this.timedActionWaitList.addNewTimedAction(newAction);
    }

    @Override
    public void performLandClearance(Faction callingFaction, Province targetProvince, int amount) {
        this.performFactionActionBaseTypeTimedAction(callingFaction, this.landClearAction, targetProvince, 1);

    }

    @Override
    public void performFamilyHallBuild(Faction callingFaction, Province targetProvince) {
        performFactionActionBaseTypeAction(callingFaction, this.buildOfficeAction, targetProvince, 1);
    }

    @Override
    public void seizeControlInCity(Faction callingFaction, Province targetProvince) {
        performFactionActionBaseTypeAction(callingFaction, this.seizeControlAction, targetProvince, 1);
    }

    public FactionAction getLandPurchaseAction() {
        return landPurchaseAction;
    }

    public FactionAction getLandClearAction() {
        return landClearAction;
    }

    private TimedActionWrapper getNewTimedAction(FactionAction actionBase, Faction targetFaction, Province location, int amount) {
        TimedActionWrapper newAction = actionBase.getActionAsTimedElement(targetFaction, location, amount);
        return newAction;
    }
}
