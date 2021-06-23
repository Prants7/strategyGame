package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionCostAndGains;

import hedgehogs.strategyGame.gameLogic.factionReousrceInterface.ResourceType;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
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
