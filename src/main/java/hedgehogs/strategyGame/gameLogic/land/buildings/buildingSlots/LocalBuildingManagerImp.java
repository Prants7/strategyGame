package hedgehogs.strategyGame.gameLogic.land.buildings.buildingSlots;

import hedgehogs.strategyGame.gameLogic.land.Province;

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
        return 0;
    }

    @Override
    public List<BuildingSlot> getAllSlots() {
        return new ArrayList<>(this.slotsHere);
    }

    @Override
    public Province getMasterProvince() {
        return this.masterProvince;
    }
}
