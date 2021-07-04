package hedgehogs.strategyGame.javaSwingInterface.agentList;

import hedgehogs.strategyGame.gameLogic.agents.agentPhoneBook.AgentPhoneBook;
import hedgehogs.strategyGame.gameLogic.agents.base.Agent;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.factions.FactionPhoneBook;
import hedgehogs.strategyGame.javaSwingInterface.generalBuildObjects.AbstractUIObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.Map;

@Component
public class AgentList extends AbstractUIObjectFactory {
    private AgentPhoneBook agentPhoneBook;
    private FactionPhoneBook factionPhoneBook;

    private JLabel agentLabel;
    private JList<Agent> agentList;

    @Autowired
    public AgentList(AgentPhoneBook agentPhoneBook, FactionPhoneBook factionPhoneBook) {
        this.agentPhoneBook = agentPhoneBook;
        this.factionPhoneBook = factionPhoneBook;
    }

    @Override
    protected void makeAllMinorElements() {
        this.makeAgentLabel();
        this.makeAgentsList();
    }

    private void makeAgentLabel() {
        this.agentLabel = new JLabel(this.getDisplayedFaction().getFactionName() + " agents: ");
        this.addNewElementToPanel(this.agentLabel, 0, 0);
    }

    private void makeAgentsList() {
        this.agentList = new JList<>();
        this.addNewElementToPanel(this.agentList, 0, 1);
    }

    @Override
    protected void elementContentRefresh() {
        writeAgentsList();
    }

    private void writeAgentsList() {
        DefaultListModel<Agent> listModel = new DefaultListModel<>();
        for(Agent oneAgent: this.agentPhoneBook.getFactionAgents(this.getDisplayedFaction())) {
            listModel.addElement(oneAgent);
        }
        this.agentList.setModel(listModel);
    }

    private Faction getDisplayedFaction() {
        return this.factionPhoneBook.getPlayerFaction();
    }
}
