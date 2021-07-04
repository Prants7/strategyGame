package hedgehogs.strategyGame.gameLogic.agents.envoy;

import hedgehogs.strategyGame.gameLogic.agents.base.AbstractAgent;
import hedgehogs.strategyGame.gameLogic.factions.Faction;

public class Envoy extends AbstractAgent {

    public Envoy(String name, Faction alignment) {
        super(name, alignment);
    }

    @Override
    protected String bootGiveRoleName() {
        return "Envoy";
    }
}
