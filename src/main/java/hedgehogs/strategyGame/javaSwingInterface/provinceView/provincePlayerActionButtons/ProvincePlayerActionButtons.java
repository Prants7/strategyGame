package hedgehogs.strategyGame.javaSwingInterface.provinceView.provincePlayerActionButtons;

import hedgehogs.strategyGame.gameLogic.agents.agentPhoneBook.AgentPhoneBook;
import hedgehogs.strategyGame.gameLogic.agents.base.Agent;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.FactionActionInterface;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.FactionActionInterfaceImp;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.javaSwingInterface.generalBuildObjects.MinorAbstractUIObjectFactory;
import hedgehogs.strategyGame.javaSwingInterface.provinceView.ProvinceViewFactory;

import javax.swing.*;

public class ProvincePlayerActionButtons extends MinorAbstractUIObjectFactory {
    private Province lastSelectedProvince;
    private ProvinceViewFactory masterTable;
    private FactionActionInterface factionActionInterface;
    private AgentPhoneBook agentPhoneBook;
    private JButton clearLand;
    private JButton buyLand;
    private JButton seizeControl;

    public ProvincePlayerActionButtons(Faction perspectiveFaction,
                                       ProvinceViewFactory masterTable,
                                       FactionActionInterface factionActionInterface,
                                       AgentPhoneBook agentPhoneBook) {
        super(perspectiveFaction);
        this.masterTable = masterTable;
        this.factionActionInterface = factionActionInterface;
        this.agentPhoneBook = agentPhoneBook;
    }

    @Override
    protected void makeContentRefreshCall() {
        if(!this.hasSelectedProvince()) {
            return;
        }
        this.masterTable.callSuperUpdateOnGameInterface();
    }

    @Override
    protected void makeAllMinorElements() {
        makeClearLandButton();
        makeBuyLandButton();
        makeSeizeControlButton();
    }

    private void makeClearLandButton() {
        this.clearLand = new JButton("Clear land "+this.getFactionActionInterfaceAsImp().getLandClearAction().getCostsString());
        this.clearLand.addActionListener( e -> {
            this.activateLandClearOnSelectedProvince();
        });
        this.addNewElementToPanel(this.clearLand, 0, 0);
    }

    public void activateLandClearOnSelectedProvince() {
        if(this.lastSelectedProvince == null) {
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
    }

    private void makeBuyLandButton() {
        this.buyLand = new JButton("Buy land "+this.getFactionActionInterfaceAsImp().getLandPurchaseAction().getCostsString());
        this.buyLand.addActionListener( e -> {
            this.activateLandPurchaseOnSelectedProvince();
        });
        this.addNewElementToPanel(this.buyLand, 0, 1);
    }

    public void activateLandPurchaseOnSelectedProvince() {
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
    }

    private void makeSeizeControlButton() {
        this.seizeControl = new JButton("Seize control for "+this.getPerspectiveFaction().getFactionName());
        this.seizeControl.addActionListener( e -> {
            this.activateSeizeControl();
        });
        this.addNewElementToPanel(this.seizeControl, 0, 2);
    }

    private void activateSeizeControl() {
        if(this.lastSelectedProvince == null) {
            return;
        }
        if(!this.tempHasAgentsInCity()) {
            return;
        }
        this.factionActionInterface.seizeControlInCity(this.getFirstAgentInCity());
        makeContentRefreshCall();
    }

    private FactionActionInterfaceImp getFactionActionInterfaceAsImp() {
        return (FactionActionInterfaceImp) this.factionActionInterface;
    }

    @Override
    protected void elementContentRefresh() {
        if(!this.hasSelectedProvince()) {
            return;
        }
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

    private boolean tempHasAgentsInCity() {
        return !this.agentPhoneBook.getFactionsAgentsOnLocation(this.getPerspectiveFaction(), this.lastSelectedProvince).isEmpty();
    }

    private Agent getFirstAgentInCity() {
        Agent firstAgent = this.agentPhoneBook.getFactionsAgentsOnLocation(this.getPerspectiveFaction(), this.lastSelectedProvince).get(0);
        System.out.println("Getting first agent: "+firstAgent.getName());
        return firstAgent;
    }
}
