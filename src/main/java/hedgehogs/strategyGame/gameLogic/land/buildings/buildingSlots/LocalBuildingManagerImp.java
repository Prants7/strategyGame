package hedgehogs.strategyGame.gameLogic.land.buildings.buildingSlots;

import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.gameLogic.land.buildings.cityBuildings.base.CityBuilding;

import java.util.ArrayList;
import java.util.List;

public class LocalBuildingManagerImp implements  LocalBuildingManager {
    private Province masterProvince;
    private List<BuildingSlot> slotsHere;

    public LocalBuildingManagerImp(Province masterProvince) {
        this.masterProvince = masterProvince;
        this.slotsHere = new ArrayList<>();
        updateBuildingSlots();
    }

    private void updateBuildingSlots() {
        if(isThereNeedForMoreSlots()) {
            this.addMoreSlots();
        }
    }

    private boolean isThereNeedForMoreSlots() {
        return this.calculateCurrentlyAllowedAmountOfSlots() > this.slotsHere.size();
    }

    private void addMoreSlots() {
        while(isThereNeedForMoreSlots()) {
            slotsHere.add(new BuildingSlotImp());
        }
    }

    private int calculateCurrentlyAllowedAmountOfSlots() {
        return 3;
    }

    @Override
    public int getAmountOfSlots() {
        return this.slotsHere.size();
    }

    @Override
    public int getAmountOfUnusedSlots() {
        return this.slotsHere.size();
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
        BuildingSlot targetSlot = this.getFirstAvailableSlot();
        if(!targetSlot.canAddNewBuilding(newBuilding)) {
            System.out.println("ERROR: first empty slot doesn't allow designated building");
            return false;
        }
        targetSlot.addNewBuilding(newBuilding);
        return true;
    }

    private BuildingSlot getFirstAvailableSlot() {
        for(BuildingSlot oneSlot : this.slotsHere) {
            if(oneSlot.isEmpty()) {
                return oneSlot;
            }
        }
        return null;
    }
}
