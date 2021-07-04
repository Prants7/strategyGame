package hedgehogs.strategyGame.gameLogic.agents.base;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;

public interface Agent {

    public String getName();

    public String getRole();

    public Faction getAlignmentFaction();

    public Province getLocation();
}
