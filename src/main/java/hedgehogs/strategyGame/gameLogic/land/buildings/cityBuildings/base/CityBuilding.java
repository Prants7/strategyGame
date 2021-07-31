package hedgehogs.strategyGame.gameLogic.land.buildings.cityBuildings.base;

import hedgehogs.strategyGame.gameLogic.land.Province;

public interface CityBuilding {

    public String getName();

    public boolean finished(Province location);

}
