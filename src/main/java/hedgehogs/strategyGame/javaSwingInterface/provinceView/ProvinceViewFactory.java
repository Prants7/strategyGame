package hedgehogs.strategyGame.javaSwingInterface.provinceView;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.FactionActionInterface;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.FactionActionInterfaceImp;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landClearAction.LandClearAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landPurchaseAction.LandPurchaseAction;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.factions.FactionPhoneBook;
import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.gameLogic.land.buildings.offices.base.Office;
import hedgehogs.strategyGame.javaSwingInterface.mainWindowBooter.MainWindowFactory;
import hedgehogs.strategyGame.javaSwingInterface.provinceView.controlTable.ControlTable;
import hedgehogs.strategyGame.javaSwingInterface.provinceView.landFractionsTable.LandFractionsTable;
import hedgehogs.strategyGame.javaSwingInterface.provinceView.provinceDescriptionTable.ProvinceDescriptionTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

@Component
public class ProvinceViewFactory {
    private Province lastSelectedProvince;
    private JPanel mainPanel;
    private ControlTable controlTable;
    private LandFractionsTable landFractionsTable;
    private ProvinceDescriptionTable provinceDescriptionTable;

    private JButton clearLand;
    private JButton buyLand;
    private JButton seizeControl;
    @Autowired
    private FactionActionInterface factionActionInterface;
    @Autowired
    private FactionPhoneBook factionPhoneBook;
    @Autowired
    private MainWindowFactory mainWindowFactory;


    public JPanel giveProvinceView() {
        this.setUpMainPanel();
        GridBagConstraints layoutConstraint = new GridBagConstraints();

        layoutConstraint.fill = GridBagConstraints.HORIZONTAL;

        this.provinceDescriptionTable = new ProvinceDescriptionTable(this.getPlayerFaction());
        this.setCoordinatesForLayout(layoutConstraint, 0, 0);
        this.mainPanel.add(this.provinceDescriptionTable.getPanelObject(), layoutConstraint);

        this.landFractionsTable = new LandFractionsTable(this.getPlayerFaction());
        this.setCoordinatesForLayout(layoutConstraint, 0, 1);
        this.mainPanel.add(this.landFractionsTable.getPanelObject(), layoutConstraint);

        this.controlTable = new ControlTable(this.getPlayerFaction());
        this.setCoordinatesForLayout(layoutConstraint, 0, 4);
        this.mainPanel.add(this.controlTable.getPanelObject(), layoutConstraint);

        this.setCoordinatesForLayout(layoutConstraint, 0, 6);
        this.mainPanel.add(this.getClearLandButton(), layoutConstraint);

        this.setCoordinatesForLayout(layoutConstraint, 1, 6);
        this.mainPanel.add(this.getBuyLandButton(), layoutConstraint);

        this.setCoordinatesForLayout(layoutConstraint, 0, 7);
        this.mainPanel.add(this.getSeizeControlButton(), layoutConstraint);

        return this.mainPanel;
    }

    private void setUpMainPanel() {
        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new GridBagLayout());
    }

    private GridBagConstraints setCoordinatesForLayout(GridBagConstraints layoutConstraint, int xLocation, int yLocation) {
        layoutConstraint.gridx = xLocation;
        layoutConstraint.gridy = yLocation;
        return layoutConstraint;
    }

    private JButton getClearLandButton() {
        this.clearLand = new JButton("Clear land "+this.getFactionActionInterfaceAsImp().getLandClearAction().getCostsString());
        this.clearLand.addActionListener( e -> {
            this.activateLandClearOnSelectedProvince();
        });
        return this.clearLand;
    }

    private JButton getBuyLandButton() {
        this.buyLand = new JButton("Buy land "+this.getFactionActionInterfaceAsImp().getLandPurchaseAction().getCostsString());
        this.buyLand.addActionListener( e -> {
            this.activateLandPurchaseOnSelectedProvince();
        });
        return this.buyLand;
    }

    private JButton getSeizeControlButton() {
        this.seizeControl = new JButton("Seize control for "+this.getPlayerFaction().getFactionName());
        this.seizeControl.addActionListener( e -> {
            this.activateSeizeControl();
        });
        return this.seizeControl;
    }


    public void openViewForProvince(Province province) {
        if(province == null) {
            return;
        }
        this.lastSelectedProvince = province;
        this.provinceDescriptionTable.setLastSelectedProvince(province);
        this.controlTable.setLastSelectedProvince(province);
        this.landFractionsTable.setLastSelectedProvince(province);
        updateAllDataHere();
    }

    public void updateText() {
        if(this.lastSelectedProvince == null) {
            return;
        }
        updateAllDataHere();
    }

    private void updateAllDataHere() {
        this.provinceDescriptionTable.refreshElements();
        this.controlTable.refreshElements();
        this.landFractionsTable.refreshElements();
    }

    public void activateLandClearOnSelectedProvince() {
        if(this.lastSelectedProvince == null) {
            return;
        }
        this.activateLandClear(this.getPlayerFaction(), this.lastSelectedProvince, 1);
        callSuperUpdateOnGameInterface();
    }

    private void activateLandClear(Faction playerFaction, Province targetProvince, int amount) {
        this.factionActionInterface.performLandClearance(playerFaction, targetProvince, amount);
    }

    private Faction getPlayerFaction() {
        return this.factionPhoneBook.getPlayerFaction();
    }

    public void activateLandPurchaseOnSelectedProvince() {
        if(this.lastSelectedProvince == null) {
            return;
        }
        activateLandPurchase(this.getPlayerFaction(), this.lastSelectedProvince, 1);
        callSuperUpdateOnGameInterface();
    }

    private void callSuperUpdateOnGameInterface() {
        this.mainWindowFactory.updateTexts();
    }

    private void activateLandPurchase(Faction playerFaction, Province targetProvince, int amount) {
        this.factionActionInterface.performLandPurchase(playerFaction, targetProvince);
    }

    private FactionActionInterfaceImp getFactionActionInterfaceAsImp() {
        return (FactionActionInterfaceImp) this.factionActionInterface;
    }

    private void activateSeizeControl() {
        if(this.lastSelectedProvince == null) {
            return;
        }
        this.factionActionInterface.seizeControlInCity(this.getPlayerFaction(), this.lastSelectedProvince);
        callSuperUpdateOnGameInterface();
    }

}
