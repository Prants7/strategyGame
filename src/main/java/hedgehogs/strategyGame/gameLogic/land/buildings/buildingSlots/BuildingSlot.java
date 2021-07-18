package hedgehogs.strategyGame.gameLogic.land.buildings.buildingSlots;

import hedgehogs.strategyGame.gameLogic.land.buildings.cityBuildings.base.CityBuilding;

public interface BuildingSlot {

    public String getContentString();

    public boolean isEmpty();

    public boolean canAddNewBuilding(CityBuilding cityBuilding);

    public boolean addNewBuilding(CityBuilding cityBuilding);
}
