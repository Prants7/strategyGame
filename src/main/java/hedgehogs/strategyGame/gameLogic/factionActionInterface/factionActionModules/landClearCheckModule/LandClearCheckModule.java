package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionModules.landClearCheckModule;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;

public interface LandClearCheckModule {

    public boolean checkIfAllowedToClearLandInProvince(Faction callerFaction, Province targetProvince, int amount);
}
