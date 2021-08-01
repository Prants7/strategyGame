package hedgehogs.strategyGame.gameLogic.land.buildings.cityBuildings.instances;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionActionCost.FACIncreaseBuildingAmount;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionActionCost.FactionActionCost;
import hedgehogs.strategyGame.gameLogic.factionReousrceInterface.ResourceType;
import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.gameLogic.land.buildings.cityBuildings.base.AbstractCityBuilding;
import hedgehogs.strategyGame.gameLogic.land.settlementStats.statBase.StatName;
import hedgehogs.strategyGame.gameLogic.land.settlementStats.statChangeData.StatChangeDataImp;

import java.util.List;

public class VillageBuilding extends AbstractCityBuilding {

    @Override
    protected String bootGiveName() {
        return "Village";
    }

    @Override
    protected int bootGiveBuildLimit() {
        return 3;
    }

    @Override
    protected void bootAddCosts(List<FactionActionCost> addLocation) {
        addLocation.add( new FACIncreaseBuildingAmount(ResourceType.GOLD, this, 5));
    }

    @Override
    protected void addFinishStats(Province location) {
        location.accessStats().sendStatChange(new StatChangeDataImp(StatName.LAND_AMOUNT, 5));
    }
}
