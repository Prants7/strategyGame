package hedgehogs.strategyGame.gameLogic.land.buildings.buildingSlots;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.buildings.cityBuildings.base.CityBuilding;

public class BuildingSlotImp implements BuildingSlot {
    private CityBuilding building;
    private Faction owner;

    private boolean hasBuilding() {
        return this.building != null;
    }

    @Override
    public String getContentString() {
        if(!hasBuilding()) {
            return "Vacant building slot";
        }
        return building.getName();
    }

    @Override
    public boolean isEmpty() {
        return !hasBuilding();
    }

    @Override
    public boolean canAddNewBuilding(CityBuilding cityBuilding) {
        if(this.hasBuilding()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean addNewBuilding(CityBuilding cityBuilding) {
        if(!this.canAddNewBuilding(cityBuilding)) {
            return false;
        }
        this.building = cityBuilding;
        return true;
    }

    @Override
    public boolean hasOwner() {
        return this.owner != null;
    }

    @Override
    public Faction getOwner() {
        return this.owner;
    }
}
