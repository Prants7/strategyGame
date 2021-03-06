package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.FactionActionInput;
import hedgehogs.strategyGame.gameLogic.factionReousrceInterface.ResourceType;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;

import java.util.Map;

public interface TimedActionCommandInterface {

    public boolean doActionCosts(FactionActionInput input);

    public boolean doActionFunctionality(FactionActionInput input);

    public boolean doGiveActionRewards(FactionActionInput input);

    public Map<ResourceType, Integer> getMapOfCostResources(FactionActionInput input);
}
