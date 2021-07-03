package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;

public interface FactionAction {

    public boolean allowedToDoAction(Faction callerFaction, Province location, int amount);

    public void doAction(Faction callerFaction, Province location, int amount);

    public void forceDoAction(Faction callerFaction, Province location, int amount);

    public boolean callToDoCosts(Faction callerFaction, Province location);

    public String getCostsString();

    public String getActionName();
}
