package hedgehogs.strategyGame.javaSwingInterface.oneAgentView;

import hedgehogs.strategyGame.gameLogic.agents.base.Agent;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.FactionActionInterface;
import hedgehogs.strategyGame.gameLogic.factions.FactionPhoneBook;
import hedgehogs.strategyGame.javaSwingInterface.generalBuildObjects.AbstractUIObjectFactory;
import hedgehogs.strategyGame.javaSwingInterface.mainWindowBooter.MainWindowFactory;
import hedgehogs.strategyGame.javaSwingInterface.oneAgentView.agentActionButtons.AgentActionButtons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class OneAgentViewFactory extends AbstractUIObjectFactory {
    @Autowired
    private FactionActionInterface factionActionInterface;
    @Autowired
    private MainWindowFactory mainWindowFactory;
    @Autowired
    private FactionPhoneBook factionPhoneBook;
    private Agent selectedAgent;
    private JLabel agentNameLabel;
    private JLabel factionLabel;
    private JLabel locationLabel;
    private AgentActionButtons agentActionButtons;


    @Override
    protected void makeAllMinorElements() {
        this.agentNameLabel = new JLabel("");
        this.addNewElementToPanel(agentNameLabel, 0, 0);
        this.factionLabel = new JLabel("");
        this.addNewElementToPanel(factionLabel, 0, 1);
        this.locationLabel = new JLabel("");
        this.addNewElementToPanel(locationLabel, 0, 2);
        this.agentActionButtons = new AgentActionButtons(
                this.factionPhoneBook.getPlayerFaction(), this.factionActionInterface, this.mainWindowFactory);
        this.addNewElementToPanel(this.agentActionButtons.getPanelObject(), 0, 3);
    }

    @Override
    protected void elementContentRefresh() {
        if(this.selectedAgent == null) {
            return;
        }
        this.updateAllValues();
    }

    private void updateAllValues() {
        this.writeContentToNameLabel();
        this.writeFactionLabel();
        this.writeLocationLabel();
        this.agentActionButtons.refreshElements();
    }

    private void writeContentToNameLabel() {
        this.agentNameLabel.setText("Name: "+this.selectedAgent.getName());
    }

    private void writeFactionLabel() {
        this.factionLabel.setText("Faction: "+this.selectedAgent.getAlignmentFaction().getFactionName());
    }

    private void writeLocationLabel() {
        this.locationLabel.setText("Location: "+this.selectedAgent.getLocation().getProvinceName());
    }

    public void selectAgent(Agent selectedAgent) {
        this.selectedAgent = selectedAgent;
        this.agentActionButtons.setLastSelectedAgent(selectedAgent);
        this.updateAllValues();
    }
}
