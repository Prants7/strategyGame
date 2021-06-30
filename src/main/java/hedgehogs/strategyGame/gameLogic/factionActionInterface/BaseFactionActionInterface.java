package hedgehogs.strategyGame.gameLogic.factionActionInterface;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionActionBase;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.adminLandAssign.AdminLandAssignAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.buildOfficeAction.BuildOfficeAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landClearAction.LandClearAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landPurchaseAction.LandPurchaseAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.seizeControlFromLocalsAction.SeizeControlFromLocalsAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper.TimedActionWaitList;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper.TimedActionWrapper;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public abstract class BaseFactionActionInterface implements FactionActionInterface {
    @Autowired
    private LandPurchaseAction landPurchaseAction;
    @Autowired
    private LandClearAction landClearAction;
    @Autowired
    private AdminLandAssignAction adminLandAssignAction;
    @Autowired
    private TimedActionWaitList timedActionWaitList;
    @Autowired
    private BuildOfficeAction buildOfficeAction;
    @Autowired
    private SeizeControlFromLocalsAction seizeControlAction;

    @Override
    public void performAdminLandAssign(Faction forFaction, Province targetProvince) {
        if(!this.checkIfAllowedToAssignLand(forFaction, targetProvince)) {
            return;
        }
        this.doLandAssignForFaction(forFaction, targetProvince);
    }

    protected void performFactionActionBaseTypeAction(Faction targetFaction, FactionActionBase action, Province targetProvince, int amount) {
        if(!action.allowedToDoAction(targetFaction, targetProvince, amount)) {
            return;
        }
        action.doAction(targetFaction, targetProvince, amount);
    }

    protected boolean checkIfAllowedToAssignLand(Faction targetFaction, Province targetProvince) {
        return this.adminLandAssignAction.allowedToDoAction(targetFaction, targetProvince, 1);
    }

    protected void doLandAssignForFaction(Faction targetFaction, Province targetProvince) {
        this.adminLandAssignAction.doAction(targetFaction, targetProvince, 1);
    }

    @Override
    public void performLandPurchase(Faction callingFaction, Province targetProvince) {
        this.performFactionActionBaseTypeTimedAction(callingFaction, this.landPurchaseAction, targetProvince, 1);
    }

    protected void performFactionActionBaseTypeTimedAction(Faction targetFaction, FactionActionBase action, Province targetProvince, int amount) {
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

    public LandPurchaseAction getLandPurchaseAction() {
        return landPurchaseAction;
    }

    public LandClearAction getLandClearAction() {
        return landClearAction;
    }

    private TimedActionWrapper getNewTimedAction(FactionActionBase actionBase, Faction targetFaction, Province location, int amount) {
        TimedActionWrapper newAction = new TimedActionWrapper(actionBase, targetFaction, location, amount);
        return newAction;
    }
}
