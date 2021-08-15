package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionActionCost;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.FactionActionInput;
import hedgehogs.strategyGame.gameLogic.factionReousrceInterface.ResourceType;

import java.util.List;
import java.util.stream.Collectors;

public class FACReferenceToBuilding implements FactionActionCost {
    private ResourceType designatedResource;

    public FACReferenceToBuilding(ResourceType targetResource) {
        this.designatedResource = targetResource;
    }

    @Override
    public ResourceType getResourceType() {
        return designatedResource;
    }

    @Override
    public int getAmount(FactionActionInput input) {
        List<FactionActionCost> foundCosts = input.getCityBuilding().getBuildingSpecificCosts();
        int total = 0;
        for(FactionActionCost oneCost: foundCosts) {
            if(this.designatedResource == oneCost.getResourceType()) {
                total += oneCost.getAmount(input);
            }
        }
        return total;

    }
}
