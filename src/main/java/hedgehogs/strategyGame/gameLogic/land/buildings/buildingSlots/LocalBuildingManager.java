package hedgehogs.strategyGame.gameLogic.land.buildings.buildingSlots;

import hedgehogs.strategyGame.gameLogic.land.Province;

import java.util.List;

public interface LocalBuildingManager {

    public int getAmountOfSlots();

    public int getAmountOfUnusedSlots();

    public int getAmountOfUsedSlots();

    public List<BuildingSlot> getAllSlots();

    public Province getMasterProvince();
}
