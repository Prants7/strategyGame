package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landClearAction;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;

public interface LandClearCheck {

    public boolean allowedToDoAction(Faction callerFaction, Province location, int amount);
}
