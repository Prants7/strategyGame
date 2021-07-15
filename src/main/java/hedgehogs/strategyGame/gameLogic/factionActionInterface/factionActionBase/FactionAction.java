package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase;

import hedgehogs.strategyGame.gameLogic.agents.base.Agent;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.FactionActionInput;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper.TimedActionWrapper;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper.TimedActionWrapperImp;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;

public interface FactionAction {

    public boolean allowedToDoAction(FactionActionInput input);

    public void startAction(FactionActionInput input);

    public void forceDoAction(FactionActionInput input);

    public String getCostsString();

    public String getActionName();

}
