package hedgehogs.strategyGame.gameLogic.land.buildings.officeBuildingSlot;

import hedgehogs.strategyGame.gameLogic.land.buildings.officeBuildings.base.OfficeBuilding;
import hedgehogs.strategyGame.gameLogic.land.buildings.offices.base.Office;

public interface OfficeBuildingSlot {

    public String getContentString();

    public boolean isEmpty();

    public boolean canAddNewBuilding(OfficeBuilding officeBuilding);

    public boolean addNewBuilding(OfficeBuilding officeBuilding);

    public Office getOwnerOffice();

    public OfficeBuilding getBuilding();
}
