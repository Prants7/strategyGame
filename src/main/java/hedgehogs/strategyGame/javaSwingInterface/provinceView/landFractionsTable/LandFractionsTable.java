package hedgehogs.strategyGame.javaSwingInterface.provinceView.landFractionsTable;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.javaSwingInterface.generalBuildObjects.MinorAbstractUIObjectFactory;

import javax.swing.*;
import java.util.Map;

public class LandFractionsTable extends MinorAbstractUIObjectFactory  {
    private Province lastSelectedProvince;
    private JLabel developedLand;
    private JLabel undevelopedLand;
    private JLabel ownershipLabel;
    private JList<String> ownershipList;

    public LandFractionsTable(Faction perspectiveFaction) {
        super(perspectiveFaction);
    }

    @Override
    protected void makeContentRefreshCall() {

    }

    @Override
    protected void makeAllMinorElements() {
        this.makeDevelopedLandLabel();
        this.makeUndevelopedLandLabel();
        this.makeOwnerShipLabel();
        this.makeOwnershipList();
    }

    private void makeDevelopedLandLabel() {
        this.developedLand = new JLabel("");
        this.addNewElementToPanel(this.developedLand, 0, 0);
    }

    private void makeUndevelopedLandLabel() {
        this.undevelopedLand = new JLabel("");
        this.addNewElementToPanel(this.undevelopedLand, 0, 1);
    }

    private void makeOwnerShipLabel() {
        this.ownershipLabel = new JLabel("Land fraction ownership: ");
        this.addNewElementToPanel(this.ownershipLabel, 0, 2);
    }

    private void makeOwnershipList() {
        this.ownershipList = new JList<>();
        this.addNewElementToPanel(this.ownershipList, 0, 3);
    }

    @Override
    protected void elementContentRefresh() {
        if(!this.hasSelectedProvince()) {
            return;
        }
        addTextToDevelopedLand();
        addTextToUndevelopedLand();
        this.writeOwnershipTable();
    }

    private boolean hasSelectedProvince() {
        return lastSelectedProvince != null;
    }

    private void addTextToDevelopedLand() {
        this.developedLand.setText("Developed land: "+this.lastSelectedProvince.getAmountOfSettledLand());
    }

    private void addTextToUndevelopedLand() {
        this.undevelopedLand.setText("Undeveloped land: "+this.lastSelectedProvince.getAmountOfUnsettledLand());
    }

    private void writeOwnershipTable() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for(Map.Entry<Faction, Integer> oneEntry : this.lastSelectedProvince.getFractionOwnershipMap().entrySet()) {
            if(oneEntry.getKey() != null) {
                listModel.addElement(oneEntry.getKey().getFactionName()+": "+oneEntry.getValue());
            }
        }
        this.ownershipList.setModel(listModel);
    }

    public Province getLastSelectedProvince() {
        return lastSelectedProvince;
    }

    public void setLastSelectedProvince(Province lastSelectedProvince) {
        this.lastSelectedProvince = lastSelectedProvince;
    }
}
