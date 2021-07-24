package hedgehogs.strategyGame.javaSwingInterface.buildingsView;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.factions.FactionPhoneBook;
import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.gameLogic.land.buildings.buildingSlots.BuildingSlot;
import hedgehogs.strategyGame.javaSwingInterface.buildingsView.buildSlotMinorView.BuildSlotMinorView;
import hedgehogs.strategyGame.javaSwingInterface.generalBuildObjects.AbstractUIObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class BuildingViewFactory extends AbstractUIObjectFactory {
    private Province lastSelectedProvince;
    private JLabel provinceNameLabel;
    private List<BuildSlotMinorView> buildSlots;
    private FactionPhoneBook factionPhoneBook;

    @Autowired
    public BuildingViewFactory(FactionPhoneBook factionPhoneBook) {
        this.factionPhoneBook = factionPhoneBook;
        this.buildSlots = new ArrayList<>();
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
        this.getPanelObject().removeAll();
        this.makeNameLabel();
        this.writeNameLabel();
        this.addAllBuildingSlotViews();
    }

    private void addAllBuildingSlotViews() {
        int y = 0;
        for(BuildingSlot oneSlot : this.lastSelectedProvince.accessBuildings().getAllSlots()) {
            y++;
            this.addAnotherSlot(oneSlot, y);
        }
    }

    private void addAnotherSlot(BuildingSlot targetSlot, int yCoordinate) {
        BuildSlotMinorView newBuildingSlotView = new BuildSlotMinorView(this.factionPhoneBook.getPlayerFaction(), targetSlot);
        this.addNewElementToPanel(newBuildingSlotView.getPanelObject(), 0, yCoordinate);
        newBuildingSlotView.refreshElements();
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
