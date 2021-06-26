package hedgehogs.strategyGame.gameLogic.factionActionInterface;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionActionBase;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.adminLandAssign.AdminLandAssignAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landClearAction.LandClearAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landPurchaseAction.LandPurchaseAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper.TimedActionWrapper;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper.TimedActionWaitList;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FactionActionInterfaceImp extends BaseFactionActionInterface {
    @Autowired
    private LandPurchaseAction landPurchaseAction;
    @Autowired
    private LandClearAction landClearAction;
    @Autowired
    private AdminLandAssignAction adminLandAssignAction;
    @Autowired
    private TimedActionWaitList timedActionWaitList;

    @Override
    protected boolean checkIfAllowedToAssignLand(Faction targetFaction, Province targetProvince) {
        return this.adminLandAssignAction.allowedToDoAction(targetFaction, targetProvince, 1);
    }

    @Override
    protected void doLandAssignForFaction(Faction targetFaction, Province targetProvince) {
        this.adminLandAssignAction.doAction(targetFaction, targetProvince, 1);
    }

    @Override
    protected boolean checkIfAllowedToPurchaseLand(Faction targetFaction, Province targetProvince) {
        return this.landPurchaseAction.allowedToDoAction(targetFaction, targetProvince, 1);
    }

    @Override
    protected void doLandPurchaseForFaction(Faction targetFaction, Province targetProvince) {
        TimedActionWrapper newAction = this.getNewTimedAction(this.landPurchaseAction, targetFaction, targetProvince, 1);
        this.timedActionWaitList.addNewTimedAction(newAction);
        //this.landPurchaseAction.doAction(targetFaction, targetProvince, 1);
    }

    @Override
    protected boolean checkIfAllowedToClearLand(Faction callingFaction, Province targetProvince, int amount) {
        return this.landClearAction.allowedToDoAction(callingFaction, targetProvince, amount);
    }

    @Override
    protected void doClearLand(Faction callingFaction, Province targetProvince, int amount) {
        //this.landClearAction.doAction(callingFaction, targetProvince, amount);
        TimedActionWrapper newAction = this.getNewTimedAction(this.landClearAction, callingFaction, targetProvince, amount);
        this.timedActionWaitList.addNewTimedAction(newAction);

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
