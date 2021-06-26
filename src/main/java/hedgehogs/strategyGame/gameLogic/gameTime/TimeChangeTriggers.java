package hedgehogs.strategyGame.gameLogic.gameTime;

import hedgehogs.strategyGame.gameLogic.gameTime.calculationHubs.CalculationHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class TimeChangeTriggers {
    @Autowired
    @Qualifier("LandOwnership")
    private CalculationHub landOwnershipHub;
    @Autowired
    @Qualifier("TimedAction")
    private CalculationHub timedActionHub;

    public void callOnIncomeCollectionFromLandOwnership() {
        this.landOwnershipHub.triggerCalculations();
    }

    public void callOnAdvanceTimeOnTimedActions() {
        this.timedActionHub.triggerCalculations();
    }

}
