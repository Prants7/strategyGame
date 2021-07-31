package hedgehogs.strategyGame.gameLogic.land.buildings.cityBuildings.instances;

import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.gameLogic.land.buildings.cityBuildings.base.AbstractCityBuilding;
import hedgehogs.strategyGame.gameLogic.land.settlementStats.statBase.StatName;
import hedgehogs.strategyGame.gameLogic.land.settlementStats.statChangeData.StatChangeDataImp;

public class VillageBuilding extends AbstractCityBuilding {

    @Override
    protected String bootGiveName() {
        return "Village";
    }

    @Override
    protected void addFinishStats(Province location) {
        location.accessStats().sendStatChange(new StatChangeDataImp(StatName.LAND_AMOUNT, 5));
    }
}
