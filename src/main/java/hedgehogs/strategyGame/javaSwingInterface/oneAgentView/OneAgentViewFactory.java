package hedgehogs.strategyGame.javaSwingInterface.oneAgentView;

import hedgehogs.strategyGame.gameLogic.agents.base.Agent;
import hedgehogs.strategyGame.javaSwingInterface.generalBuildObjects.AbstractUIObjectFactory;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class OneAgentViewFactory extends AbstractUIObjectFactory {
    private Agent selectedAgent;
    private JLabel agentNameLabel;
    private JLabel factionLabel;
    private JLabel locationLabel;


    @Override
    protected void makeAllMinorElements() {
        this.agentNameLabel = new JLabel("");
        this.addNewElementToPanel(agentNameLabel, 0, 0);
        this.factionLabel = new JLabel("");
        this.addNewElementToPanel(factionLabel, 0, 1);
        this.locationLabel = new JLabel("");
        this.addNewElementToPanel(locationLabel, 0, 2);
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
        this.updateAllValues();
    }
}
