package hedgehogs.strategyGame.gameLogic.agents.base;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper.TimedActionWrapper;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;

public abstract class AbstractAgent implements Agent {
    private String name;
    private Faction alignment;
    private String roleName;
    private Province location;
    private boolean actionLock;
    private TimedActionWrapper currentTask;

    public AbstractAgent(String name, Faction alignment, Province location) {
        this.name = name;
        this.alignment = alignment;
        this.roleName = this.bootGiveRoleName();
        this.location = location;
        this.actionLock = false;
    }

    @Override
    public String getName() {
        return this.name;
    }

    protected abstract String bootGiveRoleName();

    @Override
    public String getRole() {
        return this.roleName;
    }

    @Override
    public Faction getAlignmentFaction() {
        return this.alignment;
    }

    @Override
    public String toString() {
        return this.roleName + ": "+this.name;
    }

    @Override
    public Province getLocation() {
        return this.location;
    }

    @Override
    public boolean moveAgent(Province newLocation) {
        this.location = newLocation;
        return true;
    }

    @Override
    public boolean isLockedInTask() {
        return this.actionLock;
    }

    @Override
    public void lockToATask(TimedActionWrapper targetTask) {
        this.actionLock = true;
        this.currentTask = targetTask;
    }

    @Override
    public void unlockFromTask() {
        this.actionLock = false;
        this.currentTask = null;
    }

    @Override
    public TimedActionWrapper getCurrentTask() {
        return this.currentTask;
    }
}
