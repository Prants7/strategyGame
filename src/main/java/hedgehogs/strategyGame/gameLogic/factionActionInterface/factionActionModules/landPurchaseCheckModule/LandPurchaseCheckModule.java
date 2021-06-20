package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionModules.landPurchaseCheckModule;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;

public interface LandPurchaseCheckModule {

    public boolean allowedToPerformLandPurchaseForFaction(Faction targetFaction, Province targetProvince);

}
