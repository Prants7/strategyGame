package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase;

import hedgehogs.strategyGame.gameLogic.agents.base.Agent;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper.TimedActionWrapper;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper.TimedActionWrapperImp;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;

public interface FactionAction {

    public boolean allowedToDoAction(Agent agent);

    public void doAction(Agent agent);

    public void forceDoAction(Faction callerFaction, Province location);

    public boolean callToDoCosts(Faction callerFaction, Province location);

    public String getCostsString();

    public String getActionName();

    public TimedActionWrapper getActionAsTimedElement(Agent agent);
}
