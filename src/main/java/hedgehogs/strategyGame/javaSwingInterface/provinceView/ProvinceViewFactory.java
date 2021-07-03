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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

@Component
public class ProvinceViewFactory {
    private Province lastSelectedProvince;
    private JPanel mainPanel;
    private JLabel provinceNameLabel;
    //private JLabel developedLand;
    private JLabel prosperity;
    //private JLabel playerInfluence;
    //private JLabel ownerShip;
    //private JList<String> ownershipList;
    /*private JLabel officeListLabel;
    private JList<String> officeList;*/
    //private JLabel controllingFaction;
    private ControlTable controlTable;
    private LandFractionsTable landFractionsTable;

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
        this.setCoordinatesForLayout(layoutConstraint, 0, 0);
        this.mainPanel.add(this.getProvinceNameLabel(), layoutConstraint);

        this.setCoordinatesForLayout(layoutConstraint, 1, 0);
        this.mainPanel.add(this.getProsperityLabel(), layoutConstraint);

        /*this.setCoordinatesForLayout(layoutConstraint, 0, 1);
        this.mainPanel.add(this.getDevelopedLandLabel(), layoutConstraint);*/

        this.landFractionsTable = new LandFractionsTable(this.getPlayerFaction());
        this.setCoordinatesForLayout(layoutConstraint, 0, 1);
        this.mainPanel.add(this.landFractionsTable.getPanelObject(), layoutConstraint);

        /*this.setCoordinatesForLayout(layoutConstraint, 0, 2);
        this.mainPanel.add(this.getOwnerShipLabel(), layoutConstraint);*/

        /*this.setCoordinatesForLayout(layoutConstraint, 1, 2);
        this.mainPanel.add(this.getPlayerInfluenceLabel(), layoutConstraint);*/

        /*this.setCoordinatesForLayout(layoutConstraint, 0, 3);
        this.mainPanel.add(this.getOwnershipList(), layoutConstraint);*/

        /*this.setCoordinatesForLayout(layoutConstraint, 0, 4);
        this.mainPanel.add(this.getOfficeListLabel(), layoutConstraint);

        this.setCoordinatesForLayout(layoutConstraint, 0, 5);
        this.mainPanel.add(this.getOfficeList(), layoutConstraint);*/

        this.controlTable = new ControlTable(this.getPlayerFaction());
        this.setCoordinatesForLayout(layoutConstraint, 0, 4);
        this.mainPanel.add(this.controlTable.getPanelObject(), layoutConstraint);

        /*this.setCoordinatesForLayout(layoutConstraint, 1, 4);
        this.mainPanel.add(this.getControllingFactionLabel(), layoutConstraint);*/

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

    private JLabel getProvinceNameLabel() {
        this.provinceNameLabel = new JLabel("-");
        return this.provinceNameLabel;
    }

    /*private JLabel getDevelopedLandLabel() {
        this.developedLand = new JLabel("-");
        return this.developedLand;
    }*/

    private JLabel getProsperityLabel() {
        this.prosperity = new JLabel("-");
        return this.prosperity;
    }

    /*private JLabel getOwnerShipLabel() {
        //String infoText = "Land fraction ownership: ";
        this.ownerShip = new JLabel("Land fraction ownership: ");
        return this.ownerShip;
    }*/

    /*private JLabel getPlayerInfluenceLabel() {
        this.playerInfluence = new JLabel("-");
        return this.playerInfluence;
    }*/

    /*private JList getOwnershipList() {
        this.ownershipList = new JList<>();
        return ownershipList;
    }*/

    /*private JLabel getOfficeListLabel() {
        this.officeListLabel = new JLabel("Faction offices:");
        return this.officeListLabel;
    }

    private JList getOfficeList() {
        this.officeList = new JList<>();
        return this.officeList;
    }*/

    /*private JLabel getControllingFactionLabel() {
        this.controllingFaction = new JLabel("");
        return this.controllingFaction;
    }*/

    private JButton getClearLandButton() {
        this.clearLand = new JButton("Clear land "+this.getFactionActionInterfaceAsImp().getLandClearAction().getCostsString());
        this.clearLand.addActionListener( e -> {
            this.activateLandClearOnSelectedProvince();
        });
        //this.clearLand.addActionListener(new ClearLandButtonActionListener(this));
        return this.clearLand;
    }

    private JButton getBuyLandButton() {
        this.buyLand = new JButton("Buy land "+this.getFactionActionInterfaceAsImp().getLandPurchaseAction().getCostsString());
        this.buyLand.addActionListener( e -> {
            this.activateLandPurchaseOnSelectedProvince();
        });
        //this.buyLand.addActionListener(new PurchaseLandButtonActionListener(this));
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
        this.addTextToProvinceNameLabel();
        //this.addTextToDevelopedLand();
        //this.writeOwnershipTable();
        //this.writePlayerInfluenceText();
        this.addTextToProsperity();
        //this.writeOfficeTable();
        //writeControllingFactionString();
        this.controlTable.refreshElements();
        this.landFractionsTable.refreshElements();
    }

    private void addTextToProvinceNameLabel() {
        this.provinceNameLabel.setText(this.lastSelectedProvince.getProvinceName());
    }

    /*private void addTextToDevelopedLand() {
        this.developedLand.setText("Developed land: "+this.lastSelectedProvince.getAmountOfSettledLand()
                + ", undeveloped: "+this.lastSelectedProvince.getAmountOfUnsettledLand()
        +" total: "+this.lastSelectedProvince.getAllLandFractions().size());
    }*/

    private void addTextToProsperity() {
        this.prosperity.setText("Prosperity: "+this.lastSelectedProvince.accessProsperity().getCurrentValue());
    }

    /*private void writePlayerInfluenceText() {
        String infoText = "Influence: "
                +this.lastSelectedProvince.getProvinceInfluenceTable().getFactionInfluenceHere(this.getPlayerFaction());
        this.playerInfluence.setText(infoText);
    }*/

    /*private void writeOwnershipTable() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for(Map.Entry<Faction, Integer> oneEntry : this.lastSelectedProvince.getFractionOwnershipMap().entrySet()) {
            if(oneEntry.getKey() != null) {
                listModel.addElement(oneEntry.getKey().getFactionName()+": "+oneEntry.getValue());
            }
        }
        this.ownershipList.setModel(listModel);
    }*/

    /*private void writeOfficeTable() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for(Map.Entry<Faction, Office> oneEntry : this.lastSelectedProvince.accessLocationOffices().getAllOffices().entrySet()) {
            if(oneEntry.getKey() != null) {
                listModel.addElement(oneEntry.getKey().getFactionName()+": "+oneEntry.getValue().getBuildingTypeName());
            }
        }
        this.officeList.setModel(listModel);
    }*/

    /*private void writeControllingFactionString() {
        this.controllingFaction.setText(this.lastSelectedProvince.accessLocationOffices().getControlSituation());
    }*/

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
