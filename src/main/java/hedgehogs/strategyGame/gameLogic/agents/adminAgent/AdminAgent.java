package hedgehogs.strategyGame.gameLogic.agents.adminAgent;

import hedgehogs.strategyGame.gameLogic.agents.base.AbstractAgent;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;

public class AdminAgent extends AbstractAgent {

    public AdminAgent(Faction alignment, Province location) {
        super("ADMIN AGENT", alignment, location);
    }

    @Override
    protected String bootGiveRoleName() {
        return "Admin agent";
    }


}
