package hedgehogs.strategyGame.gameLogic.land.buildings.buildingSlots;

import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.gameLogic.land.buildings.cityBuildings.base.CityBuilding;

import java.util.List;

public interface LocalBuildingManager {

    public int getAmountOfSlots();

    public int getAmountOfUnusedSlots();

    public int getAmountOfUsedSlots();

    public List<BuildingSlot> getAllSlots();

    public Province getMasterProvince();

    public boolean attemptToConstructBuildingHere(CityBuilding newBuilding);

    public int countBuilding(CityBuilding targetType);

    public void performBuildingSlotAmountCheck();
}
