package hedgehogs.strategyGame.gameLogic.land.buildings.officeBuildingSlot;

import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.gameLogic.land.buildings.buildingSlots.BuildingSlot;
import hedgehogs.strategyGame.gameLogic.land.buildings.cityBuildings.base.CityBuilding;
import hedgehogs.strategyGame.gameLogic.land.buildings.officeBuildings.base.OfficeBuilding;
import hedgehogs.strategyGame.gameLogic.land.buildings.offices.base.Office;

import java.util.List;

public interface OfficeBuildingManager {

    public int getAmountOfSlots();

    public int getAmountOfUnusedSlots();

    public int getAmountOfUsedSlots();

    public List<OfficeBuildingSlot> getAllSlots();

    public Office getMasterOffice();

    public boolean attemptToConstructBuildingHere(OfficeBuilding newBuilding);

    public int countBuilding(OfficeBuilding targetType);

    public void performBuildingSlotAmountCheck();
}
