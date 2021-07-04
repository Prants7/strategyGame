package hedgehogs.strategyGame.gameLogic.agents.base;

import hedgehogs.strategyGame.gameLogic.factions.Faction;

public interface Agent {

    public String getName();

    public String getRole();

    public Faction getAlignmentFaction();
}
