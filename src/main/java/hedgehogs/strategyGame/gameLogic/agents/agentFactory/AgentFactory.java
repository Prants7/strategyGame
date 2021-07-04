package hedgehogs.strategyGame.gameLogic.agents.agentFactory;

import hedgehogs.strategyGame.gameLogic.agents.agentPhoneBook.AgentPhoneBook;
import hedgehogs.strategyGame.gameLogic.agents.base.Agent;
import hedgehogs.strategyGame.gameLogic.agents.envoy.Envoy;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AgentFactory {
    @Autowired
    private AgentPhoneBook agentPhoneBook;

    public Agent makeNewEnvoyForFaction(String name, Faction alignment, Province location) {
        Agent newAgent = new Envoy(name, alignment, location);
        agentPhoneBook.addAgentToBook(newAgent);
        return newAgent;
    }
}
