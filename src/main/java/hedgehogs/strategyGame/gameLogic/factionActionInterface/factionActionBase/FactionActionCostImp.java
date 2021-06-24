package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase;

import hedgehogs.strategyGame.gameLogic.factionReousrceInterface.ResourceType;
import lombok.Data;

@Data
public class FactionActionCostImp {
    private ResourceType resourceType;
    private int amount;

    public FactionActionCostImp(ResourceType resourceType, int amount) {
        this.resourceType = resourceType;
        this.amount = amount;
    }
}
