package hedgehogs.strategyGame.gameLogic.land.buildings.officeBuildings.instances;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionActionCost.FactionActionCost;
import hedgehogs.strategyGame.gameLogic.land.buildings.officeBuildings.base.OfficeBuilding;
import hedgehogs.strategyGame.gameLogic.land.buildings.offices.base.Office;

import java.util.ArrayList;
import java.util.List;

public class TestOfficeBuilding implements OfficeBuilding {

    @Override
    public String getName() {
        return "test building";
    }

    @Override
    public boolean finished(Office location) {
        return true;
    }

    @Override
    public boolean allowedToBuildInOffice(Office targetLocation) {
        return true;
    }

    @Override
    public List<FactionActionCost> getBuildingSpecificCosts() {
        return new ArrayList<>();
    }
}
