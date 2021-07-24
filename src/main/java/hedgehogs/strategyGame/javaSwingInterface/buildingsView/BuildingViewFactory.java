package hedgehogs.strategyGame.javaSwingInterface.buildingsView;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.factions.FactionPhoneBook;
import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.javaSwingInterface.buildingsView.buildSlotMinorView.BuildSlotMinorView;
import hedgehogs.strategyGame.javaSwingInterface.generalBuildObjects.AbstractUIObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class BuildingViewFactory extends AbstractUIObjectFactory {
    private Province lastSelectedProvince;
    private JLabel provinceNameLabel;
    private BuildSlotMinorView trialSlot;
    private FactionPhoneBook factionPhoneBook;

    @Autowired
    public BuildingViewFactory(FactionPhoneBook factionPhoneBook) {
        this.factionPhoneBook = factionPhoneBook;
    }

    @Override
    protected void makeAllMinorElements() {
        this.makeNameLabel();
    }

    private void makeNameLabel() {
        this.provinceNameLabel = new JLabel("Province: ");
        this.addNewElementToPanel(this.provinceNameLabel, 0, 0);
    }

    @Override
    protected void elementContentRefresh() {
        this.writeNameLabel();
        this.trialSlot = new BuildSlotMinorView(
                this.factionPhoneBook.getPlayerFaction(),
                this.lastSelectedProvince.accessBuildings().getAllSlots().get(0));
        this.addNewElementToPanel(this.trialSlot.getPanelObject(), 0, 1);
        this.trialSlot.refreshElements();
    }

    private void writeNameLabel() {
        this.provinceNameLabel.setText("Province: "+this.lastSelectedProvince.getProvinceName());
    }

    public void openViewForProvince(Province province) {
        if(province == null) {
            return;
        }
        this.lastSelectedProvince = province;
        this.elementContentRefresh();
    }
}
