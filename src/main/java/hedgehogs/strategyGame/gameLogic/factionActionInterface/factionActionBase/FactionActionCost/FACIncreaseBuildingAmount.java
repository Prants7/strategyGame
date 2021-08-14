package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionActionCost;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.FactionActionInput;
import hedgehogs.strategyGame.gameLogic.factionReousrceInterface.ResourceType;
import hedgehogs.strategyGame.gameLogic.land.buildings.cityBuildings.base.CityBuilding;

public class FACIncreaseBuildingAmount implements FactionActionCost {
    private ResourceType type;
    private CityBuilding dependentBuilding;
    private int baseCost;


    public FACIncreaseBuildingAmount(ResourceType type, CityBuilding dependentBuilding, int baseCost) {
        this.type = type;
        this.dependentBuilding = dependentBuilding;
        this.baseCost = baseCost;
    }

    @Override
    public ResourceType getResourceType() {
        return null;
    }

    @Override
    public int getAmount(FactionActionInput input) {
        int buildingAmount = input.getAgent().getLocation().accessBuildings().countBuilding(this.dependentBuilding);
        double finalAmount = Math.pow(2, buildingAmount);
        return (int) (baseCost * finalAmount);
    }
}
