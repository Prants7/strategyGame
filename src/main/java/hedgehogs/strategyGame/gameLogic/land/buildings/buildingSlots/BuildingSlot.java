package hedgehogs.strategyGame.gameLogic.land.buildings.buildingSlots;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.buildings.cityBuildings.base.CityBuilding;

public interface BuildingSlot {

    public String getContentString();

    public boolean isEmpty();

    public boolean canAddNewBuilding(CityBuilding cityBuilding);

    public boolean addNewBuilding(CityBuilding cityBuilding);

    public boolean hasOwner();

    public Faction getOwner();

    public CityBuilding getBuilding();
}
