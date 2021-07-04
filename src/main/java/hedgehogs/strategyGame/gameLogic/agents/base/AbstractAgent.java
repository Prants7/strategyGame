package hedgehogs.strategyGame.gameLogic.agents.base;

import hedgehogs.strategyGame.gameLogic.factions.Faction;

public abstract class AbstractAgent implements Agent {
    private String name;
    private Faction alignment;
    private String roleName;

    public AbstractAgent(String name, Faction alignment) {
        this.name = name;
        this.alignment = alignment;
        this.roleName = this.bootGiveRoleName();
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
}
