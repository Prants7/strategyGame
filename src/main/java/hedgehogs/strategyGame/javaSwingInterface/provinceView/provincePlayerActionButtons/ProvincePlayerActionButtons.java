package hedgehogs.strategyGame.javaSwingInterface.provinceView.provincePlayerActionButtons;

import hedgehogs.strategyGame.gameLogic.agents.agentPhoneBook.AgentPhoneBook;
import hedgehogs.strategyGame.gameLogic.agents.base.Agent;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.FactionActionInterface;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.FactionActionInterfaceImp;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionAction;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.javaSwingInterface.generalBuildObjects.MinorAbstractUIObjectFactory;
import hedgehogs.strategyGame.javaSwingInterface.mainWindowBooter.MainWindowFactory;
import hedgehogs.strategyGame.javaSwingInterface.provinceView.ProvinceViewFactory;

import javax.swing.*;
import java.util.ArrayList;

public class ProvincePlayerActionButtons extends MinorAbstractUIObjectFactory {
    private Agent lastSelectedAgent;
    private FactionActionInterface factionActionInterface;
    private MainWindowFactory mainWindowFactory;
    //private JButton clearLand;
    //private JButton buyLand;
    //private JButton seizeControl;
    private java.util.List<JButton> allButtons;

    public ProvincePlayerActionButtons(Faction perspectiveFaction,
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
        /*makeClearLandButton();
        makeBuyLandButton();
        makeSeizeControlButton();*/
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

    /*private void makeClearLandButton() {
        this.clearLand = new JButton("Clear land "+this.getFactionActionInterfaceAsImp().getLandClearAction().getCostsString());
        this.clearLand.addActionListener( e -> {
            this.activateLandClearOnSelectedProvince();
        });
        this.addNewElementToPanel(this.clearLand, 0, 0);
    }*/

    /*public void activateLandClearOnSelectedProvince() {
        if(this.lastSelectedAgent == null) {
            return;
        }
        this.activateLandClear(this.getPerspectiveFaction(), this.lastSelectedProvince, 1);
        makeContentRefreshCall();
    }

    private void activateLandClear(Faction playerFaction, Province targetProvince, int amount) {
        if(!this.tempHasAgentsInCity()) {
            return;
        }
        this.factionActionInterface.performLandClearance(this.getFirstAgentInCity());
    }*/

    /*private void makeBuyLandButton() {
        this.buyLand = new JButton("Buy land "+this.getFactionActionInterfaceAsImp().getLandPurchaseAction().getCostsString());
        this.buyLand.addActionListener( e -> {
            this.activateLandPurchaseOnSelectedProvince();
        });
        this.addNewElementToPanel(this.buyLand, 0, 1);
    }*/

    /*public void activateLandPurchaseOnSelectedProvince() {
        if(this.lastSelectedProvince == null) {
            return;
        }
        activateLandPurchase(this.getPerspectiveFaction(), this.lastSelectedProvince, 1);
        makeContentRefreshCall();
    }

    private void activateLandPurchase(Faction playerFaction, Province targetProvince, int amount) {
        if(!this.tempHasAgentsInCity()) {
            return;
        }
        this.factionActionInterface.performLandPurchase(this.getFirstAgentInCity());
    }*/

    /*private void makeSeizeControlButton() {
        this.seizeControl = new JButton("Seize control for "+this.getPerspectiveFaction().getFactionName());
        this.seizeControl.addActionListener( e -> {
            this.activateSeizeControl();
        });
        this.addNewElementToPanel(this.seizeControl, 0, 2);
    }*/

    /*private void activateSeizeControl() {
        if(this.lastSelectedProvince == null) {
            return;
        }
        if(!this.tempHasAgentsInCity()) {
            return;
        }
        this.factionActionInterface.seizeControlInCity(this.getFirstAgentInCity());
        makeContentRefreshCall();
    }*/

    private FactionActionInterfaceImp getFactionActionInterfaceAsImp() {
        return (FactionActionInterfaceImp) this.factionActionInterface;
    }

    @Override
    protected void elementContentRefresh() {
        if(!this.hasSelectedAgent()) {
            return;
        }
        this.getPanelObject().removeAll();
        this.allButtons.clear();
        this.makeAllActionButtons(this.factionActionInterface.getListOfUsableFactionActions());
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

    /*private boolean tempHasAgentsInCity() {
        return !this.agentPhoneBook.getFactionsAgentsOnLocation(this.getPerspectiveFaction(), this.lastSelectedProvince).isEmpty();
    }

    private Agent getFirstAgentInCity() {
        Agent firstAgent = this.agentPhoneBook.getFactionsAgentsOnLocation(this.getPerspectiveFaction(), this.lastSelectedProvince).get(0);
        System.out.println("Getting first agent: "+firstAgent.getName());
        return firstAgent;
    }*/
}
