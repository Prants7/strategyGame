package hedgehogs.strategyGame.javaSwingInterface.provinceView;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.FactionActionInterface;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.FactionActionInterfaceImp;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landClearAction.LandClearAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landPurchaseAction.LandPurchaseAction;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.factions.FactionPhoneBook;
import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.gameLogic.land.buildings.offices.base.Office;
import hedgehogs.strategyGame.javaSwingInterface.generalBuildObjects.AbstractUIObjectFactory;
import hedgehogs.strategyGame.javaSwingInterface.mainWindowBooter.MainWindowFactory;
import hedgehogs.strategyGame.javaSwingInterface.provinceView.controlTable.ControlTable;
import hedgehogs.strategyGame.javaSwingInterface.provinceView.landFractionsTable.LandFractionsTable;
import hedgehogs.strategyGame.javaSwingInterface.provinceView.provinceDescriptionTable.ProvinceDescriptionTable;
import hedgehogs.strategyGame.javaSwingInterface.provinceView.provincePlayerActionButtons.ProvincePlayerActionButtons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

@Component
public class ProvinceViewFactory extends AbstractUIObjectFactory  {
    private FactionActionInterface factionActionInterface;
    private FactionPhoneBook factionPhoneBook;
    private MainWindowFactory mainWindowFactory;

    private Province lastSelectedProvince;
    private ControlTable controlTable;
    private LandFractionsTable landFractionsTable;
    private ProvinceDescriptionTable provinceDescriptionTable;
    private ProvincePlayerActionButtons playerActionButtons;

    @Autowired
    public ProvinceViewFactory(FactionActionInterface factionActionInterface,
                               FactionPhoneBook factionPhoneBook,
                               MainWindowFactory mainWindowFactory) {
        this.factionActionInterface = factionActionInterface;
        this.factionPhoneBook = factionPhoneBook;
        this.mainWindowFactory = mainWindowFactory;
    }

    @Override
    protected void makeAllMinorElements() {
        this.provinceDescriptionTable = new ProvinceDescriptionTable(this.getPlayerFaction());
        this.addNewElementToPanel(this.provinceDescriptionTable.getPanelObject(), 0, 0);
        /*this.setCoordinatesForLayout(layoutConstraint, 0, 0);
        this.mainPanel.add(this.provinceDescriptionTable.getPanelObject(), layoutConstraint);*/

        this.landFractionsTable = new LandFractionsTable(this.getPlayerFaction());
        this.addNewElementToPanel(this.landFractionsTable.getPanelObject(), 0, 1);
        /*this.setCoordinatesForLayout(layoutConstraint, 0, 1);
        this.mainPanel.add(this.landFractionsTable.getPanelObject(), layoutConstraint);*/

        this.controlTable = new ControlTable(this.getPlayerFaction());
        this.addNewElementToPanel(this.controlTable.getPanelObject(), 0, 2);
        /*this.setCoordinatesForLayout(layoutConstraint, 0, 4);
        this.mainPanel.add(this.controlTable.getPanelObject(), layoutConstraint);*/

        this.playerActionButtons =
                new ProvincePlayerActionButtons(this.getPlayerFaction(), this, this.factionActionInterface);
        this.addNewElementToPanel(this.playerActionButtons.getPanelObject(), 0, 3);
        /*this.setCoordinatesForLayout(layoutConstraint, 0, 5);
        this.mainPanel.add(this.playerActionButtons.getPanelObject(), layoutConstraint);*/
    }

    /*public JPanel giveProvinceView() {
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

        this.playerActionButtons =
                new ProvincePlayerActionButtons(this.getPlayerFaction(), this, this.factionActionInterface);
        this.setCoordinatesForLayout(layoutConstraint, 0, 5);
        this.mainPanel.add(this.playerActionButtons.getPanelObject(), layoutConstraint);

        return this.mainPanel;
    }*/

    /*private void setUpMainPanel() {
        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new GridBagLayout());
    }*/

    /*private GridBagConstraints setCoordinatesForLayout(GridBagConstraints layoutConstraint, int xLocation, int yLocation) {
        layoutConstraint.gridx = xLocation;
        layoutConstraint.gridy = yLocation;
        return layoutConstraint;
    }*/

    public void openViewForProvince(Province province) {
        if(province == null) {
            return;
        }
        this.lastSelectedProvince = province;
        this.provinceDescriptionTable.setLastSelectedProvince(province);
        this.controlTable.setLastSelectedProvince(province);
        this.landFractionsTable.setLastSelectedProvince(province);
        this.playerActionButtons.setLastSelectedProvince(province);
        //updateAllDataHere();
        this.elementContentRefresh();
    }

    /*public void updateText() {
        if(this.lastSelectedProvince == null) {
            return;
        }
        updateAllDataHere();
    }*/

    private void updateAllDataHere() {
        this.provinceDescriptionTable.refreshElements();
        this.controlTable.refreshElements();
        this.landFractionsTable.refreshElements();
        this.playerActionButtons.refreshElements();
    }

    private Faction getPlayerFaction() {
        return this.factionPhoneBook.getPlayerFaction();
    }

    public void callSuperUpdateOnGameInterface() {
        this.mainWindowFactory.updateTexts();
    }

    /*private FactionActionInterfaceImp getFactionActionInterfaceAsImp() {
        return (FactionActionInterfaceImp) this.factionActionInterface;
    }*/

    @Override
    protected void elementContentRefresh() {
        if(this.lastSelectedProvince == null) {
            return;
        }
        updateAllDataHere();
    }
}
