package hedgehogs.strategyGame.gameLogic.gameTime.calculationHubs.provinceBuildingSlotHub;

import hedgehogs.strategyGame.gameLogic.gameTime.calculationHubs.CalculationHub;
import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.gameLogic.world.World;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ProvinceBuildingSlot")
public class ProvinceBuildingSlotHubImp implements CalculationHub {
    @Autowired
    private World gameWorld;

    @Override
    public void triggerCalculations() {
        triggerSlotCalculationForWholeGameWorld();
    }

    private void triggerSlotCalculationForWholeGameWorld() {
        for(Province oneProvince: this.gameWorld.getAllProvinces()) {
            this.triggerSlotCalculationForOneProvince(oneProvince);
        }
    }

    private void triggerSlotCalculationForOneProvince(Province province) {
        province.accessBuildings().performBuildingSlotAmountCheck();
    }
}
