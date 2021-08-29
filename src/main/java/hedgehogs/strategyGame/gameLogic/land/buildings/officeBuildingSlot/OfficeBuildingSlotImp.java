package hedgehogs.strategyGame.gameLogic.land.buildings.officeBuildingSlot;

import hedgehogs.strategyGame.gameLogic.land.buildings.officeBuildings.base.OfficeBuilding;
import hedgehogs.strategyGame.gameLogic.land.buildings.offices.base.Office;

public class OfficeBuildingSlotImp implements OfficeBuildingSlot {
    private OfficeBuilding building;
    private OfficeBuildingManager masterManager;

    public OfficeBuildingSlotImp(OfficeBuildingManager masterManager) {
        this.masterManager = masterManager;
    }

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
    public boolean canAddNewBuilding(OfficeBuilding officeBuilding) {
        if(this.hasBuilding()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean addNewBuilding(OfficeBuilding officeBuilding) {
        if(!this.canAddNewBuilding(officeBuilding)) {
            return false;
        }
        this.building = officeBuilding;
        return true;
    }

    @Override
    public Office getOwnerOffice() {
        return this.masterManager.getMasterOffice();
    }

    @Override
    public OfficeBuilding getBuilding() {
        return this.building;
    }
}
