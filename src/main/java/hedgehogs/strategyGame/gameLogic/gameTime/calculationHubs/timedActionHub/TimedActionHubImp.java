package hedgehogs.strategyGame.gameLogic.gameTime.calculationHubs.timedActionHub;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper.TimedActionWaitList;
import hedgehogs.strategyGame.gameLogic.gameTime.calculationHubs.CalculationHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("TimedAction")
public class TimedActionHubImp implements CalculationHub {
    @Autowired
    private TimedActionWaitList timedActionWaitList;

    @Override
    public void triggerCalculations() {
        this.timedActionWaitList.advanceTime();
    }
}
