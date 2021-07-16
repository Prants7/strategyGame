package hedgehogs.strategyGame.javaSwingInterface.provinceView.provinceDescriptionTable;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.javaSwingInterface.generalBuildObjects.MinorAbstractUIObjectFactory;

import javax.swing.*;

public class ProvinceDescriptionTable extends MinorAbstractUIObjectFactory {
    private Province lastSelectedProvince;
    private JLabel provinceNameLabel;
    private JLabel prosperity;
    private JLabel buildingSlots;

    public ProvinceDescriptionTable(Faction perspectiveFaction) {
        super(perspectiveFaction);
    }

    @Override
    protected void makeContentRefreshCall() {

    }

    @Override
    protected void makeAllMinorElements() {
        this.makeProvinceNameLabel();
        this.makeProsperityLabel();
        this.makeBuildingSlotsLabel();
    }

    private void makeProvinceNameLabel() {
        this.provinceNameLabel = new JLabel("");
        this.addNewElementToPanel(this.provinceNameLabel, 0, 0);
    }

    private void makeProsperityLabel() {
        this.prosperity = new JLabel("");
        this.addNewElementToPanel(this.prosperity, 0, 1);
    }

    private void makeBuildingSlotsLabel() {
        this.buildingSlots = new JLabel("");
        this.addNewElementToPanel(this.buildingSlots, 0, 2);
    }

    @Override
    protected void elementContentRefresh() {
        if(!this.hasSelectedProvince()) {
            return;
        }
        this.addTextToProvinceNameLabel();
        this.addTextToProsperity();
        addTextToBuildingSlots();
    }

    private void addTextToProvinceNameLabel() {
        this.provinceNameLabel.setText(this.lastSelectedProvince.getProvinceName());
    }

    private void addTextToProsperity() {
        this.prosperity.setText("Prosperity: "+this.lastSelectedProvince.accessProsperity().getCurrentValue());
    }

    private void addTextToBuildingSlots() {
        this.buildingSlots.setText("Building slots: "+
                this.lastSelectedProvince.accessBuildings().getAmountOfUsedSlots()+"/"+
                this.lastSelectedProvince.accessBuildings().getAmountOfSlots());
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
