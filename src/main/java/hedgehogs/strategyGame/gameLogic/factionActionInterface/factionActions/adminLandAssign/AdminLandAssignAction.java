package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.adminLandAssign;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landPurchaseAction.LandPurchaseCheckModule;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landPurchaseAction.LandPurchaseModule;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//For now will do everything that normal land purchase does, just without the cost and gain
@Component
public class AdminLandAssignAction {
    @Autowired
    private LandPurchaseCheckModule landPurchaseCheck;
    @Autowired
    private LandPurchaseModule landPurchaseModule;

    public boolean allowedToDoAction(Faction callerFaction, Province location, int amount) {
        return this.landPurchaseCheck.allowedToPerformLandPurchaseForFaction(callerFaction, location);
    }

    public void doAction(Faction callerFaction, Province location, int amount) {
        if(this.allowedToDoAction(callerFaction, location, amount)) {
            this.landPurchaseModule.doLandPurchase(callerFaction, location);
        }
    }
}
