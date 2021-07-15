package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase;

import hedgehogs.strategyGame.gameLogic.agents.base.Agent;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.FactionActionInput;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper.TimedActionWrapper;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper.TimedActionWrapperImp;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;

public interface FactionAction {

    public boolean allowedToDoAction(FactionActionInput input);

    public void doAction(FactionActionInput input);

    public void forceDoAction(Faction callerFaction, Province location);

    public boolean callToDoCosts(Faction callerFaction, Province location);

    public String getCostsString();

    public String getActionName();

    public TimedActionWrapper getActionAsTimedElement(FactionActionInput input);
}
