package hedgehogs.strategyGame.javaSwingInterface.provinceView.controlTable;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.gameLogic.land.buildings.offices.base.Office;
import hedgehogs.strategyGame.javaSwingInterface.generalBuildObjects.AbstractUIObjectFactory;
import hedgehogs.strategyGame.javaSwingInterface.generalBuildObjects.MinorAbstractUIObjectFactory;

import javax.swing.*;
import java.util.Map;

public class ControlTable extends MinorAbstractUIObjectFactory {
    private Province lastSelectedProvince;
    private JLabel controllingFaction;
    private JLabel playerInfluence;
    private JLabel officeListLabel;
    private JList<String> officeList;

    public ControlTable(Faction perspectiveFaction) {
        super(perspectiveFaction);
    }

    @Override
    protected void makeContentRefreshCall() {

    }

    @Override
    protected void makeAllMinorElements() {
        this.makeControllingFactionLabel();
        this.makePlayerInfluenceLabel();
        this.makeOfficeListLabel();
        this.makeOfficeList();
    }

    private void makeControllingFactionLabel() {
        this.controllingFaction = new JLabel("");
        this.addNewElementToPanel(this.controllingFaction, 0, 0);
    }

    private void makePlayerInfluenceLabel() {
        this.playerInfluence = new JLabel("");
        this.addNewElementToPanel(this.playerInfluence, 0, 1);
    }

    private void makeOfficeListLabel() {
        this.officeListLabel = new JLabel("Faction offices:");
        this.addNewElementToPanel(this.officeListLabel, 0, 2);
    }

    private void makeOfficeList() {
        this.officeList = new JList<>();
        this.addNewElementToPanel(this.officeList, 0, 3);
    }

    @Override
    protected void elementContentRefresh() {
        if(!this.hasSelectedProvince()) {
            return;
        }
        this.writeControllingFactionString();
        this.writePlayerInfluenceText();
        this.writeOfficeTable();
    }

    private void writeControllingFactionString() {
        this.controllingFaction.setText(this.lastSelectedProvince.accessLocationOffices().getControlSituation());
    }

    private void writePlayerInfluenceText() {
        String infoText = "Influence: "
                +this.lastSelectedProvince.getProvinceInfluenceTable().getFactionInfluenceHere(this.getPerspectiveFaction());
        this.playerInfluence.setText(infoText);
    }

    private void writeOfficeTable() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for(Map.Entry<Faction, Office> oneEntry : this.lastSelectedProvince.accessLocationOffices().getAllOffices().entrySet()) {
            if(oneEntry.getKey() != null) {
                listModel.addElement(oneEntry.getKey().getFactionName()+": "+oneEntry.getValue().getBuildingTypeName());
            }
        }
        this.officeList.setModel(listModel);
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
