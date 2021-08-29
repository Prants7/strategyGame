package hedgehogs.strategyGame.javaSwingInterface.officeView;

import hedgehogs.strategyGame.gameLogic.land.buildings.officeBuildingSlot.OfficeBuildingSlot;
import hedgehogs.strategyGame.gameLogic.land.buildings.offices.base.Office;
import hedgehogs.strategyGame.javaSwingInterface.generalBuildObjects.AbstractUIObjectFactory;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class OfficeViewFactory extends AbstractUIObjectFactory {
    private Office lastSelectedOffice;
    private JLabel owner;
    private JLabel officeName;


    @Override
    protected void makeAllMinorElements() {
        this.owner = new JLabel("Owner:");
        this.addNewElementToPanel(owner, 0, 0);

        this.officeName = new JLabel("Office: ");
        this.addNewElementToPanel(officeName, 0, 1);
    }

    @Override
    protected void elementContentRefresh() {
        if(lastSelectedOffice == null) {
            return;
        }
        this.updateAllDataHere();
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

    private void updateAllDataHere() {
        this.owner.setText("Owner: " + this.lastSelectedOffice.getOwnerFaction().getFactionName());
        this.officeName.setText("Office: " + this.lastSelectedOffice.getBuildingTypeName());
    }
}
