package hedgehogs.strategyGame.javaSwingInterface.buildingsView.buildSlotMinorView;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.buildings.buildingSlots.BuildingSlot;
import hedgehogs.strategyGame.javaSwingInterface.generalBuildObjects.MinorAbstractUIObjectFactory;

import javax.swing.*;

public class BuildSlotMinorView extends MinorAbstractUIObjectFactory {
    private BuildingSlot displayedBuildingSlot;

    private JLabel buildingSlotContent;
    private JLabel buildingSlotOwner;

    public BuildSlotMinorView(Faction perspectiveFaction, BuildingSlot displayedBuildingSlot) {
        super(perspectiveFaction);
        this.displayedBuildingSlot = displayedBuildingSlot;
    }

    @Override
    protected void makeContentRefreshCall() {

    }

    @Override
    protected void makeAllMinorElements() {
        this.buildingSlotContent = new JLabel("Content: ");
        this.addNewElementToPanel(this.buildingSlotContent, 0, 0);

        this.buildingSlotOwner = new JLabel("Owner: ");
        this.addNewElementToPanel(this.buildingSlotOwner, 0, 1);
    }

    @Override
    protected void elementContentRefresh() {
        if(this.displayedBuildingSlot == null) {
            return;
        }
        this.buildingSlotContent.setText("Content: "+this.displayedBuildingSlot.getContentString());
        if(this.displayedBuildingSlot.getOwner() != null) {
            this.buildingSlotOwner.setText("Owner: "+this.displayedBuildingSlot.getOwner());
        }
        else {
            this.buildingSlotOwner.setText("No owner");
        }

    }
}
