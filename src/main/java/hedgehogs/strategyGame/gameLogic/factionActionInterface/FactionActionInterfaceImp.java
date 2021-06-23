package hedgehogs.strategyGame.gameLogic.factionActionInterface;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landClearAction.LandClearAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landPurchaseAction.LandPurchaseAction;
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

    @Override
    protected boolean checkIfAllowedToPurchaseLand(Faction targetFaction, Province targetProvince) {
        return this.landPurchaseAction.allowedToDoAction(targetFaction, targetProvince, 1);
    }

    @Override
    protected void doLandPurchaseForFaction(Faction targetFaction, Province targetProvince) {
        this.landPurchaseAction.doAction(targetFaction, targetProvince, 1);
    }

    @Override
    protected boolean checkIfAllowedToClearLand(Faction callingFaction, Province targetProvince, int amount) {
        return this.landClearAction.allowedToDoAction(callingFaction, targetProvince, amount);
    }

    @Override
    protected void doClearLand(Faction callingFaction, Province targetProvince, int amount) {
        this.landClearAction.doAction(callingFaction, targetProvince, amount);

    }


}
