package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionActionCost;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.FactionActionInput;
import hedgehogs.strategyGame.gameLogic.factionReousrceInterface.ResourceType;

public interface FactionActionCost {

    public ResourceType getResourceType();

    public int getAmount(FactionActionInput input);

}
