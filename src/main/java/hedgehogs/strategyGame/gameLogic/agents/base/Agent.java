package hedgehogs.strategyGame.gameLogic.agents.base;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper.TimedActionWrapper;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;

public interface Agent {

    public String getName();

    public String getRole();

    public Faction getAlignmentFaction();

    public Province getLocation();

    public boolean moveAgent(Province newLocation);

    public boolean isLockedInTask();

    public void lockToATask(TimedActionWrapper targetTask);

    public void unlockFromTask();

    public TimedActionWrapper getCurrentTask();
}
