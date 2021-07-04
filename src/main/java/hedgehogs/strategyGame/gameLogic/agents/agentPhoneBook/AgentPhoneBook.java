package hedgehogs.strategyGame.gameLogic.agents.agentPhoneBook;

import hedgehogs.strategyGame.gameLogic.agents.base.Agent;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.factions.FactionPhoneBook;
import hedgehogs.strategyGame.gameLogic.land.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class AgentPhoneBook {
    @Autowired
    private FactionPhoneBook factionPhoneBook;
    private Map<Faction, List<Agent>> agentPhoneBook;

    @PostConstruct
    public void bootUp() {
        this.agentPhoneBook = new HashMap<>();
    }

    private void makeListForFaction(Faction keyFaction) {
        this.agentPhoneBook.put(keyFaction, new ArrayList<>());
    }

    private boolean factionAgentsInBook(Faction faction) {
        return this.agentPhoneBook.containsKey(faction);
    }

    public void addAgentToBook(Agent newAgent) {
        if(!factionAgentsInBook(newAgent.getAlignmentFaction())) {
            this.makeListForFaction(newAgent.getAlignmentFaction());
        }
        accessFactionList(newAgent.getAlignmentFaction()).add(newAgent);
    }

    private List<Agent> accessFactionList(Faction keyFaction) {
        return this.agentPhoneBook.get(keyFaction);
    }

    public List<Agent> getFactionAgents(Faction keyFaction) {
        if(factionAgentsInBook(keyFaction)) {
            return new ArrayList<Agent>(accessFactionList(keyFaction));
        }
        return new ArrayList<>();
    }

    public List<Agent> getFactionsAgentsOnLocation(Faction faction, Province location) {
        List<Agent> factionAgents = this.getFactionAgents(faction);
        if(factionAgents.isEmpty()) {
            return factionAgents;
        }
        else {
            return factionAgents.stream().filter(oneAgent -> oneAgent.getLocation().equals(location)).collect(Collectors.toList());
        }
    }
}
