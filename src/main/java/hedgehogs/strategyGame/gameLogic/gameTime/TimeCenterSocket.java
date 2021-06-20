package hedgehogs.strategyGame.gameLogic.gameTime;

import hedgehogs.strategyGame.gameLogic.gameTime.timeCounters.TimeCounter;
import hedgehogs.strategyGame.gameLogic.gameTime.timeCounters.TimeCounterTurns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimeCenterSocket {
    @Autowired
    private TimeChangeTriggers timeChangeTriggers;
    private TimeCounter timeCounter;

    public TimeCenterSocket() {
    }

    public void bootUpCounter() {
        this.timeCounter = new TimeCounterTurns(this.timeChangeTriggers);
    }

    public void advanceTime() {
        if(this.timeCounter == null) {
            return;
        }
        this.timeCounter.advanceTime();
    }

    public String getCurrentTime() {
        return this.timeCounter.getCurrentTimeString();
    }

}
