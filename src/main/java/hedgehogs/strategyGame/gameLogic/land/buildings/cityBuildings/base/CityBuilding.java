package hedgehogs.strategyGame.gameLogic.land.buildings.cityBuildings.base;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionActionCost.FactionActionCost;
import hedgehogs.strategyGame.gameLogic.land.Province;

import java.util.List;

public interface CityBuilding {

    public String getName();

    public boolean finished(Province location);

    public boolean allowedToBuildInProvince(Province targetLocation);

    public List<FactionActionCost> getBuildingSpecificCosts();

}
