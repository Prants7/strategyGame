package hedgehogs.strategyGame.gameLogic.gameTime.timeCounters;

import hedgehogs.strategyGame.gameLogic.gameTime.TimeChangeTriggers;

public class TimeCounterTurns implements TimeCounter {
    private TimeChangeTriggers timeChangeTriggers;
    private int turnNr;

    public TimeCounterTurns(TimeChangeTriggers timeChangeTriggers) {
        this.timeChangeTriggers = timeChangeTriggers;
        this.turnNr = 0;
    }

    @Override
    public void advanceTime() {
        if(timeChangeTriggers == null) {
            return;
        }
        this.doGeneralTurnChangeCommands();
        this.advanceTimeCounter();
    }

    private void doGeneralTurnChangeCommands() {
        this.timeChangeTriggers.callOnIncomeCollectionFromLandOwnership();
    }

    private void advanceTimeCounter() {
        this.turnNr++;
    }

    @Override
    public String getCurrentTimeString() {
        return this.makeCurrentTimeString();
    }

    private String makeCurrentTimeString() {
        return "Turn: "+this.turnNr;
    }
}
