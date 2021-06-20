package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionModules.landPurchaseModule;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;

public interface LandPurchaseModule {

    public void doLandPurchase(Faction callerFaction, Province targetProvince);
}
