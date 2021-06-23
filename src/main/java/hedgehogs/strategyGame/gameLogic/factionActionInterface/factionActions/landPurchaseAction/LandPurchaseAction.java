package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landPurchaseAction;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LandPurchaseAction {
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
