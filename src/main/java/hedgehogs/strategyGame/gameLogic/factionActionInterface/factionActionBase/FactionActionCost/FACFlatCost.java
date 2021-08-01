package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionActionCost;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.FactionActionInput;
import hedgehogs.strategyGame.gameLogic.factionReousrceInterface.ResourceType;
import lombok.Data;

public class FACFlatCost implements FactionActionCost {
    private ResourceType resourceType;
    private int amount;

    public FACFlatCost(ResourceType resourceType, int amount) {
        this.resourceType = resourceType;
        this.amount = amount;
    }


    @Override
    public ResourceType getResourceType() {
        return this.resourceType;
    }

    @Override
    public int getAmount(FactionActionInput input) {
        return this.amount;
    }
}
