package hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionAction;

public interface TimedActionWrapper {

    public void countDown();

    public boolean isFinished();

    public FactionAction getDesignatedAction();

    public String getDescriptionString();

    public int getTurnsTillComplete();
}
