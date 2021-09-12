package hedgehogs.strategyGame.javaSwingInterface.officeView.officeExtenstionSlotView;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.buildings.buildingSlots.BuildingSlot;
import hedgehogs.strategyGame.gameLogic.land.buildings.officeBuildingSlot.OfficeBuildingSlot;
import hedgehogs.strategyGame.javaSwingInterface.generalBuildObjects.MinorAbstractUIObjectFactory;

import javax.swing.*;

public class OfficeExtensionMinorView extends MinorAbstractUIObjectFactory {
    private OfficeBuildingSlot displayedBuildingSlot;

    private JLabel buildingSlotContent;

    public OfficeExtensionMinorView(Faction perspectiveFaction, OfficeBuildingSlot displayedBuildingSlot) {
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
    }

    @Override
    protected void elementContentRefresh() {
        if(this.displayedBuildingSlot == null) {
            return;
        }
        this.buildingSlotContent.setText("Content: "+this.displayedBuildingSlot.getContentString());
    }
}
