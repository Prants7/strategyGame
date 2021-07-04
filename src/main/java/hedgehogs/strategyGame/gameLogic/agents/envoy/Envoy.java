package hedgehogs.strategyGame.gameLogic.agents.envoy;

import hedgehogs.strategyGame.gameLogic.agents.base.AbstractAgent;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;

public class Envoy extends AbstractAgent {

    public Envoy(String name, Faction alignment, Province location) {
        super(name, alignment, location);
    }

    @Override
    protected String bootGiveRoleName() {
        return "Envoy";
    }
}
