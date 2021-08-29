package hedgehogs.strategyGame.gameLogic.land.buildings.officeBuildings.base;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionActionCost.FactionActionCost;
import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.gameLogic.land.buildings.offices.base.Office;

import java.util.List;

public interface OfficeBuilding {

    public String getName();

    public boolean finished(Office location);

    public boolean allowedToBuildInOffice(Office targetLocation);

    public List<FactionActionCost> getBuildingSpecificCosts();
}
