package hedgehogs.strategyGame.gameLogic.agents.agentFactory;

import hedgehogs.strategyGame.gameLogic.agents.agentPhoneBook.AgentPhoneBook;
import hedgehogs.strategyGame.gameLogic.agents.base.Agent;
import hedgehogs.strategyGame.gameLogic.agents.envoy.Envoy;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AgentFactory {
    @Autowired
    private AgentPhoneBook agentPhoneBook;

    public Agent makeNewEnvoyForFaction(String name, Faction alignment) {
        Agent newAgent = new Envoy(name, alignment);
        agentPhoneBook.addAgentToBook(newAgent);
        return new Envoy(name, alignment);
    }
}
