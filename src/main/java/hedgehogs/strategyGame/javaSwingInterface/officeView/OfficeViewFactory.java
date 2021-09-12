package hedgehogs.strategyGame.javaSwingInterface.officeView;

import hedgehogs.strategyGame.gameLogic.land.buildings.buildingSlots.BuildingSlot;
import hedgehogs.strategyGame.gameLogic.land.buildings.officeBuildingSlot.OfficeBuildingSlot;
import hedgehogs.strategyGame.gameLogic.land.buildings.offices.base.Office;
import hedgehogs.strategyGame.javaSwingInterface.buildingsView.buildSlotMinorView.BuildSlotMinorView;
import hedgehogs.strategyGame.javaSwingInterface.generalBuildObjects.AbstractUIObjectFactory;
import hedgehogs.strategyGame.javaSwingInterface.officeView.officeExtenstionSlotView.OfficeExtensionMinorView;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class OfficeViewFactory extends AbstractUIObjectFactory {
    private Office lastSelectedOffice;
    private JLabel owner;
    private JLabel officeName;
    // private List<OfficeExtensionMinorView> buildSlots;


    @Override
    protected void makeAllMinorElements() {
        this.makeOwnerLabel();
        this.makeOfficeNameLabel();
        //this.buildSlots = new ArrayList<>();
    }

    private void makeOwnerLabel() {
        this.owner = new JLabel("Owner:");
        this.addNewElementToPanel(owner, 0, 0);
    }

    private void makeOfficeNameLabel() {
        this.officeName = new JLabel("Office: ");
        this.addNewElementToPanel(officeName, 0, 1);
    }

    @Override
    protected void elementContentRefresh() {
        if(lastSelectedOffice == null) {
            return;
        }
        this.getPanelObject().removeAll();
        this.makeOwnerLabel();
        this.makeOfficeNameLabel();
        this.writeLabelContents();
        this.addAllBuildingSlotViews();
    }

    public void openViewForHeadquarters(Office targetOffice) {
        if(targetOffice == null) {
            return;
        }
        this.lastSelectedOffice = targetOffice;
        this.elementContentRefresh();
        System.out.println("Printing out office slot data");
        System.out.println("Office has: " + targetOffice.accessOfficeBuildingManager().getAmountOfSlots() + " slots");
        for(OfficeBuildingSlot oneSlot : targetOffice.accessOfficeBuildingManager().getAllSlots()) {
            System.out.println("One slot contents: " + oneSlot.getContentString());
        }

    }

    private void writeLabelContents() {
        this.owner.setText("Owner: " + this.lastSelectedOffice.getOwnerFaction().getFactionName());
        this.officeName.setText("Office: " + this.lastSelectedOffice.getBuildingTypeName());
    }

    private void addAllBuildingSlotViews() {
        int y = 2;
        for(OfficeBuildingSlot oneSlot : this.lastSelectedOffice.accessOfficeBuildingManager().getAllSlots()) {
            y++;
            this.addAnotherSlot(oneSlot, y);
        }
    }

    private void addAnotherSlot(OfficeBuildingSlot targetSlot, int yCoordinate) {
        OfficeExtensionMinorView newBuildingSlotView = new OfficeExtensionMinorView(this.lastSelectedOffice.getOwnerFaction(), targetSlot);
        this.addNewElementToPanel(newBuildingSlotView.getPanelObject(), 0, yCoordinate);
        newBuildingSlotView.refreshElements();
    }
}
