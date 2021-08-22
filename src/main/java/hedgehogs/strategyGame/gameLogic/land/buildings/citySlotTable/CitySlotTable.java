package hedgehogs.strategyGame.gameLogic.land.buildings.citySlotTable;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class CitySlotTable {
    private final int baseBuildingAmount = 2;
    private final double startProsperityGrowthValue = 200;
    private Map<Integer, Double> buildingSlotProsperityValues;

    @PostConstruct
    private void bootUpBuildingSlotProsperityValues() {
        buildingSlotProsperityValues = new HashMap<>();
        buildingSlotProsperityValues.put(1, this.startProsperityGrowthValue);
    }

    public int getBaseBuildingAmount() {
        return this.baseBuildingAmount;
    }

    public double getProsperityMarkForNextSlot(int currentBuildingSlotsAmount) {
        if(currentBuildingSlotsAmount <= this.baseBuildingAmount) {
            return 0;
        }
        else {
            int nonBaseSlotsAmount = currentBuildingSlotsAmount - baseBuildingAmount;
            if(!this.buildingSlotProsperityValues.containsKey(nonBaseSlotsAmount)) {
                this.calculateMissingMapValue(nonBaseSlotsAmount);
            }
            return this.buildingSlotProsperityValues.get(nonBaseSlotsAmount);
        }
    }

    private void calculateMissingMapValue(int targetLevel) {
        if(targetLevel < 2) {
            System.out.println("ERROR: Called calculate on level that is lower then 2");
            return;
        }
        if(buildingSlotProsperityValues.containsKey(targetLevel)) {
            System.out.println("ERROR: Called calculate on level that already exists");
            return;
        }
        if(!this.buildingSlotProsperityValues.containsKey(targetLevel - 1)) {
            this.calculateMissingMapValue(targetLevel - 1);
        }
        double previousCost = this.buildingSlotProsperityValues.get(targetLevel -1);
        double addedCost = this.getNewSlotIndividualCost(targetLevel);
        this.buildingSlotProsperityValues.put(targetLevel, previousCost + addedCost);
    }

    private double getNewSlotIndividualCost(int targetLevel) {
        return startProsperityGrowthValue * Math.pow(2, targetLevel);
    }




}
