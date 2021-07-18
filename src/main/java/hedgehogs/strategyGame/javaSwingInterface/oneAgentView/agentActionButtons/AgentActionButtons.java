package hedgehogs.strategyGame.javaSwingInterface.oneAgentView.agentActionButtons;

import hedgehogs.strategyGame.gameLogic.agents.base.Agent;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.FactionActionInterface;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.FactionActionInput;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.FactionActionInputImp;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.buildCityBuilding.BuildCityBuildingAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.moveAgentAction.MoveAgentAction;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.buildings.cityBuildings.base.CityBuildingFactory;
import hedgehogs.strategyGame.javaSwingInterface.generalBuildObjects.MinorAbstractUIObjectFactory;
import hedgehogs.strategyGame.javaSwingInterface.mainWindowBooter.MainWindowFactory;

import javax.swing.*;
import java.util.ArrayList;

public class AgentActionButtons extends MinorAbstractUIObjectFactory {
    private Agent lastSelectedAgent;
    private FactionActionInterface factionActionInterface;
    private MainWindowFactory mainWindowFactory;
    private java.util.List<JButton> allButtons;
    private CityBuildingFactory buildingFactory;

    public AgentActionButtons(Faction perspectiveFaction,
                              FactionActionInterface factionActionInterface,
                              MainWindowFactory mainWindowFactory,
                              CityBuildingFactory buildingFactory) {
        super(perspectiveFaction);
        this.factionActionInterface = factionActionInterface;
        this.mainWindowFactory = mainWindowFactory;
        this.buildingFactory = buildingFactory;
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
        makeMoveAgentButton();
        makeBuildVillageButton();
        int buttonY = 1;
        for(FactionAction oneAction : actions) {
            JButton newButton = makeButtonForAction(oneAction);
            this.allButtons.add(newButton);
            this.addNewElementToPanel(newButton, 0, buttonY);
            buttonY++;
        }
    }

    private JButton makeButtonForAction(FactionAction oneAction) {
        JButton newButton = new JButton(oneAction.getActionName() + " " + oneAction.getCostsString());
        newButton.addActionListener( e -> {
            performActionWithoutExtraInput(oneAction);
        });
        return newButton;
    }

    private void performActionWithoutExtraInput(FactionAction oneAction) {
        System.out.println("trying to do action "+oneAction.getActionName()+ " with agent " + this.lastSelectedAgent.getName());
        FactionActionInput input = new FactionActionInputImp().setAgent(this.lastSelectedAgent);
        oneAction.startAction(input);
        this.makeContentRefreshCall();
    }

    private void makeMoveAgentButton() {
        MoveAgentAction moveAgent = this.factionActionInterface.getMoveAgentAction();
        JButton newButton = new JButton(moveAgent.getActionName());
        newButton.addActionListener( e -> {
            System.out.println("trying to activate move agent");
            FactionActionInput newInput = new FactionActionInputImp().setAgent(this.lastSelectedAgent);
            this.mainWindowFactory.openMainMapForActionInput(moveAgent, newInput);
        });
        this.addNewElementToPanel(newButton, 0, 0);
    }

    private void makeBuildVillageButton() {
        FactionAction buildAction = this.factionActionInterface.getBuildCityBuildingAction();
        JButton newButton = new JButton("Build new Village");
        newButton.addActionListener( e -> {
            System.out.println("trying to build a new village");
            FactionActionInput newInput = new FactionActionInputImp().setAgent(this.lastSelectedAgent)
                    .setCityBuilding(this.buildingFactory.getNewVillageBuildingBuilding());
            buildAction.startAction(newInput);
            this.makeContentRefreshCall();
        });
        this.addNewElementToPanel(newButton, 1, 0);
    }

    @Override
    protected void elementContentRefresh() {
        if(!this.hasSelectedAgent()) {
            return;
        }
    }

    private boolean hasSelectedAgent() {
        return lastSelectedAgent != null;
    }

    public void setLastSelectedAgent(Agent lastSelectedAgent) {
        this.lastSelectedAgent = lastSelectedAgent;
    }
}
