package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase;

import hedgehogs.strategyGame.gameLogic.factionReousrceInterface.ResourceType;
import lombok.Data;

@Data
public class FactionActionGainImp {
    private ResourceType resourceType;
    private int amount;

    public FactionActionGainImp(ResourceType resourceType, int amount) {
        this.resourceType = resourceType;
        this.amount = amount;
    }
}
