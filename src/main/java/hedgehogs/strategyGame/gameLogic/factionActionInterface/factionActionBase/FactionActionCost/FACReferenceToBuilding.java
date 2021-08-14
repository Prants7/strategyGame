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
        System.out.println("When calculating building dependent cost, found amount of costs: "+foundCosts.size());
        int total = 0;
        for(FactionActionCost oneCost: foundCosts) {
            total += oneCost.getAmount(input);
        }
        System.out.println("When calculating building dependent cost, total: "+total);
        return total;

    }
}
