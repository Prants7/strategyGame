package hedgehogs.strategyGame.javaSwingInterface.oneAgentView.agentActionButtons;

import hedgehogs.strategyGame.gameLogic.agents.base.Agent;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.FactionActionInterface;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionAction;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.javaSwingInterface.generalBuildObjects.MinorAbstractUIObjectFactory;
import hedgehogs.strategyGame.javaSwingInterface.mainWindowBooter.MainWindowFactory;

import javax.swing.*;
import java.util.ArrayList;

public class AgentActionButtons extends MinorAbstractUIObjectFactory {
    private Agent lastSelectedAgent;
    private FactionActionInterface factionActionInterface;
    private MainWindowFactory mainWindowFactory;
    private java.util.List<JButton> allButtons;

    public AgentActionButtons(Faction perspectiveFaction,
                              FactionActionInterface factionActionInterface,
                              MainWindowFactory mainWindowFactory) {
        super(perspectiveFaction);
        this.factionActionInterface = factionActionInterface;
        this.mainWindowFactory = mainWindowFactory;
        this.allButtons = new ArrayList<>();
    }

    @Override
    protected void makeContentRefreshCall() {
        if(!this.hasSelectedAgent()) {
            return;
        }
        this.mainWindowFactory.updateTexts();
    }

    @Override
    protected void makeAllMinorElements() {
        makeAllActionButtons(this.factionActionInterface.getListOfUsableFactionActions());
    }

    private void makeAllActionButtons(java.util.List<FactionAction> actions) {
        int buttonY = 0;
        for(FactionAction oneAction : actions) {
            JButton newButton = new JButton(oneAction.getActionName() + " " + oneAction.getCostsString());
            newButton.addActionListener( e -> {
                System.out.println("trying to do action "+oneAction.getActionName()+ " with agent " + this.lastSelectedAgent.getName());
                this.factionActionInterface.tryToPerformActionWithAgent(oneAction, this.lastSelectedAgent);
                this.makeContentRefreshCall();
            });
            this.allButtons.add(newButton);
            this.addNewElementToPanel(newButton, 0, buttonY);
            buttonY++;
        }
    }

    @Override
    protected void elementContentRefresh() {
        if(!this.hasSelectedAgent()) {
            return;
        }
        //this.getPanelObject().removeAll();
        //this.allButtons.clear();
        //this.makeAllActionButtons(this.factionActionInterface.getListOfUsableFactionActions());
    }

    private boolean hasSelectedAgent() {
        return lastSelectedAgent != null;
    }

    public Agent getLastSelectedAgent() {
        return this.lastSelectedAgent;
    }

    public void setLastSelectedAgent(Agent lastSelectedAgent) {
        this.lastSelectedAgent = lastSelectedAgent;
    }
}
