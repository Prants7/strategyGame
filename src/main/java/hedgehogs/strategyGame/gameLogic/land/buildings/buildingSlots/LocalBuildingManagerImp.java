package hedgehogs.strategyGame.gameLogic.land.buildings.buildingSlots;

import hedgehogs.strategyGame.gameLogic.factions.FactionPhoneBook;
import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.gameLogic.land.buildings.cityBuildings.base.CityBuilding;
import hedgehogs.strategyGame.gameLogic.land.buildings.citySlotTable.CitySlotTable;

import java.util.ArrayList;
import java.util.List;

public class LocalBuildingManagerImp implements  LocalBuildingManager {
    private Province masterProvince;
    private List<BuildingSlot> slotsHere;
    private CitySlotTable citySlotTable;
    private FactionPhoneBook factionPhoneBook;

    public LocalBuildingManagerImp(Province masterProvince, CitySlotTable citySlotTable, FactionPhoneBook factionPhoneBook) {
        this.masterProvince = masterProvince;
        this.slotsHere = new ArrayList<>();
        this.citySlotTable = citySlotTable;
        this.factionPhoneBook = factionPhoneBook;
        updateBuildingSlots();
    }

    private void updateBuildingSlots() {
        if(isThereNeedForMoreSlots()) {
            this.addMoreSlots();
        }
    }

    private boolean isThereNeedForMoreSlots() {
        if(this.getAmountOfSlots() < this.citySlotTable.getBaseBuildingAmount() + 1) {
            return true;
        }
        double currentAmountOfProsperity = this.masterProvince.accessProsperity().getCurrentValue();
        double prosperityMark = this.citySlotTable.getProsperityMarkForNextSlot(this.getAmountOfSlots());
        return currentAmountOfProsperity > prosperityMark;
    }

    private void addMoreSlots() {
        while(isThereNeedForMoreSlots()) {
            slotsHere.add(makeNewBuildingSlotOwnedByThePlayer());
        }
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
    public List<BuildingSlot> getAllSlots() {
        return new ArrayList<>(this.slotsHere);
    }

    @Override
    public Province getMasterProvince() {
        return this.masterProvince;
    }

    @Override
    public boolean attemptToConstructBuildingHere(CityBuilding newBuilding) {
        if(getAmountOfUnusedSlots() < 1) {
            return false;
        }
        if(!newBuilding.allowedToBuildInProvince(this.masterProvince)) {
            return false;
        }
        BuildingSlot targetSlot = this.getFirstAvailableSlot();
        if(!targetSlot.canAddNewBuilding(newBuilding)) {
            System.out.println("ERROR: first empty slot doesn't allow designated building");
            return false;
        }
        targetSlot.addNewBuilding(newBuilding);
        return true;
    }

    @Override
    public int countBuilding(CityBuilding targetType) {
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
        this.updateBuildingSlots();
    }

    private BuildingSlot getFirstAvailableSlot() {
        for(BuildingSlot oneSlot : this.slotsHere) {
            if(oneSlot.isEmpty()) {
                return oneSlot;
            }
        }
        return null;
    }

    private BuildingSlot makeNewBuildingSlotOwnedByThePlayer() {
        BuildingSlot newSlot = new BuildingSlotImp();
        newSlot.setOwner(this.factionPhoneBook.getPlayerFaction());
        return newSlot;
    }
}
