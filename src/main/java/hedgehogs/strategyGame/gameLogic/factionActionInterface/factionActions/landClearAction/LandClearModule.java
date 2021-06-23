package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landClearAction;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;

public interface LandClearModule {

    public void doClearLandInProvince(Faction callerFaction, Province targetProvince, int clearAmount);
}
