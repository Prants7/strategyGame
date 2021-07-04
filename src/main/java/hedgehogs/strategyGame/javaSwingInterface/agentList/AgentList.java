package hedgehogs.strategyGame.javaSwingInterface.agentList;

import hedgehogs.strategyGame.gameLogic.agents.agentPhoneBook.AgentPhoneBook;
import hedgehogs.strategyGame.gameLogic.agents.base.Agent;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.factions.FactionPhoneBook;
import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.javaSwingInterface.generalBuildObjects.AbstractUIObjectFactory;
import hedgehogs.strategyGame.javaSwingInterface.generalBuildObjects.MinorAbstractUIObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.Map;

public class AgentList extends MinorAbstractUIObjectFactory {
    private AgentPhoneBook agentPhoneBook;
    private Province lastSelectedProvince;

    private JLabel agentLabel;
    private JList<Agent> agentList;

    @Autowired
    public AgentList(Faction perspectiveFaction, AgentPhoneBook agentPhoneBook) {
        super(perspectiveFaction);
        this.agentPhoneBook = agentPhoneBook;
    }

    @Override
    protected void makeAllMinorElements() {
        this.makeAgentLabel();
        this.makeAgentsList();
    }

    private void makeAgentLabel() {
        this.agentLabel = new JLabel(this.getPerspectiveFaction().getFactionName() + " agents: ");
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
        if(!this.hasSelectedProvince()) {
            return;
        }
        DefaultListModel<Agent> listModel = new DefaultListModel<>();
        for(Agent oneAgent: this.agentPhoneBook.getFactionsAgentsOnLocation(this.perspectiveFaction, this.lastSelectedProvince)) {
            listModel.addElement(oneAgent);
        }
        this.agentList.setModel(listModel);
    }

    @Override
    protected void makeContentRefreshCall() {

    }

    private boolean hasSelectedProvince() {
        return lastSelectedProvince != null;
    }

    public Province getLastSelectedProvince() {
        return lastSelectedProvince;
    }

    public void setLastSelectedProvince(Province lastSelectedProvince) {
        this.lastSelectedProvince = lastSelectedProvince;
    }
}
