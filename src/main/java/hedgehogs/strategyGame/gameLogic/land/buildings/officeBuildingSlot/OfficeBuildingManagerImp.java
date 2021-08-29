package hedgehogs.strategyGame.gameLogic.land.buildings.officeBuildingSlot;

import hedgehogs.strategyGame.gameLogic.factions.FactionPhoneBook;
import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.gameLogic.land.buildings.buildingSlots.BuildingSlot;
import hedgehogs.strategyGame.gameLogic.land.buildings.citySlotTable.CitySlotTable;
import hedgehogs.strategyGame.gameLogic.land.buildings.officeBuildings.base.OfficeBuilding;
import hedgehogs.strategyGame.gameLogic.land.buildings.officeBuildings.instances.TestOfficeBuilding;
import hedgehogs.strategyGame.gameLogic.land.buildings.offices.base.Office;

import java.util.ArrayList;
import java.util.List;

public class OfficeBuildingManagerImp implements OfficeBuildingManager {
    private Office masterOffice;
    private List<OfficeBuildingSlot> slotsHere;
    // private CitySlotTable citySlotTable;
    // private FactionPhoneBook factionPhoneBook;

    public OfficeBuildingManagerImp(Office masterOffice) {
        this.masterOffice = masterOffice;
        this.slotsHere = new ArrayList<>();
        this.addSomeBuildingSlots();
        this.addTestBuilding();
    }

    private void addSomeBuildingSlots() {
        this.slotsHere.add(new OfficeBuildingSlotImp(this));
        this.slotsHere.add(new OfficeBuildingSlotImp(this));
    }

    private void addTestBuilding() {
        this.slotsHere.get(0).addNewBuilding(new TestOfficeBuilding());
    }

    @Override
    public int getAmountOfSlots() {
        return this.slotsHere.size();
    }

    @Override
    public int getAmountOfUnusedSlots() {
        return (int) this.slotsHere.stream().filter( oneSlot -> oneSlot.isEmpty()).count();
    }

    @Override
    public int getAmountOfUsedSlots() {
        return (int) this.slotsHere.stream().filter( oneSlot -> !oneSlot.isEmpty()).count();
    }

    @Override
    public List<OfficeBuildingSlot> getAllSlots() {
        return new ArrayList<>(this.slotsHere);
    }

    @Override
    public Office getMasterOffice() {
        return this.masterOffice;
    }

    @Override
    public boolean attemptToConstructBuildingHere(OfficeBuilding newBuilding) {
        if(getAmountOfUnusedSlots() < 1) {
            return false;
        }
        if(!newBuilding.allowedToBuildInOffice(this.masterOffice)) {
            return false;
        }
        OfficeBuildingSlot targetSlot = this.getFirstAvailableSlot();
        if(!targetSlot.canAddNewBuilding(newBuilding)) {
            System.out.println("ERROR: first empty slot doesn't allow designated building");
            return false;
        }
        targetSlot.addNewBuilding(newBuilding);
        return true;
    }

    @Override
    public int countBuilding(OfficeBuilding targetType) {
        int count = (int) this.slotsHere.stream().filter(
                oneSlot -> {
                    if(oneSlot.getBuilding() == null) {
                        return false;
                    }
                    else {
                        return oneSlot.getBuilding().getClass() == targetType.getClass();
                    }
                }
        ).count();
        return count;
    }

    @Override
    public void performBuildingSlotAmountCheck() {

    }

    private OfficeBuildingSlot getFirstAvailableSlot() {
        for(OfficeBuildingSlot oneSlot : this.slotsHere) {
            if(oneSlot.isEmpty()) {
                return oneSlot;
            }
        }
        return null;
    }
}
