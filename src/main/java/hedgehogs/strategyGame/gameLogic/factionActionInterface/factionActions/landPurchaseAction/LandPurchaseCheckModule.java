package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landPurchaseAction;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;

public interface LandPurchaseCheckModule {

    public boolean allowedToPerformLandPurchaseForFaction(Faction targetFaction, Province targetProvince);

}
