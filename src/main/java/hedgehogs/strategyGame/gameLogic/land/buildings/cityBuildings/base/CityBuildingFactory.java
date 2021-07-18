package hedgehogs.strategyGame.gameLogic.land.buildings.cityBuildings.base;

import hedgehogs.strategyGame.gameLogic.land.buildings.cityBuildings.instances.VillageBuilding;
import org.springframework.stereotype.Component;

@Component
public class CityBuildingFactory {

    public VillageBuilding getNewVillageBuildingBuilding() {
        return new VillageBuilding();
    }
}
