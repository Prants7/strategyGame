package hedgehogs.strategyGame.javaSwingInterface.provinceView;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.FactionActionInterface;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.factions.FactionPhoneBook;
import hedgehogs.strategyGame.gameLogic.land.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

@Component
public class ProvinceViewFactory {
    private Province lastSelectedProvince;
    private JPanel mainPanel;
    private JLabel provinceNameLabel;
    private JLabel developedLand;
    private JLabel ownerShip;
    private JLabel playerInfluence;
    private JList<String> ownershipList;
    private JButton clearLand;
    private JButton buyLand;
    @Autowired
    private FactionActionInterface factionActionInterface;
    @Autowired
    private FactionPhoneBook factionPhoneBook;


    public JPanel giveProvinceView() {
        //this.lastSelectedProvince = targetProvince;
        /*JPanel panel=new JPanel();
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);*/
        this.setUpMainPanel();
        GridBagConstraints layoutConstraint = new GridBagConstraints();

        layoutConstraint.fill = GridBagConstraints.HORIZONTAL;
        this.setCoordinatesForLayout(layoutConstraint, 0, 0);
        this.mainPanel.add(this.getProvinceNameLabel(), layoutConstraint);

        this.setCoordinatesForLayout(layoutConstraint, 0, 1);
        this.mainPanel.add(this.getDevelopedLandLabel(), layoutConstraint);

        this.setCoordinatesForLayout(layoutConstraint, 0, 2);
        this.mainPanel.add(this.getOwnerShipLabel(), layoutConstraint);

        this.setCoordinatesForLayout(layoutConstraint, 1, 2);
        this.mainPanel.add(this.getPlayerInfluenceLabel(), layoutConstraint);

        this.setCoordinatesForLayout(layoutConstraint, 0, 3);
        this.mainPanel.add(this.getOwnershipList(), layoutConstraint);

        this.setCoordinatesForLayout(layoutConstraint, 0, 4);
        this.mainPanel.add(this.getClearLandButton(), layoutConstraint);

        this.setCoordinatesForLayout(layoutConstraint, 1, 4);
        this.mainPanel.add(this.getBuyLandButton(), layoutConstraint);
        return this.mainPanel;
    }

    private void setUpMainPanel() {
        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new GridBagLayout());
    }

    private GridBagConstraints setCoordinatesForLayout(GridBagConstraints layoutConstraint, int xLocation, int yLocation) {
        layoutConstraint.gridx = xLocation;
        layoutConstraint.gridy = yLocation;
        return layoutConstraint;
    }

    private JLabel getProvinceNameLabel() {
        this.provinceNameLabel = new JLabel("-");
        return this.provinceNameLabel;
    }

    private JLabel getDevelopedLandLabel() {
        this.developedLand = new JLabel("-");
        return this.developedLand;
    }

    private JLabel getOwnerShipLabel() {
        //String infoText = "Land fraction ownership: ";
        this.ownerShip = new JLabel("-");
        return this.ownerShip;
    }

    private JLabel getPlayerInfluenceLabel() {
        this.playerInfluence = new JLabel("-");
        return this.playerInfluence;
    }

    private JList getOwnershipList() {
        this.ownershipList = new JList<>();
        return ownershipList;
    }

    private JButton getClearLandButton() {
        this.clearLand = new JButton("Clear land");
        this.clearLand.addActionListener(new ClearLandButtonActionListener(this));
        return this.clearLand;
    }

    private JButton getBuyLandButton() {
        this.buyLand = new JButton("Buy land");
        this.buyLand.addActionListener(new PurchaseLandButtonActionListener(this));
        return this.buyLand;
    }


    public void openViewForProvince(Province province) {
        this.updateViewForProvince(province);
    }

    private void updateViewForProvince(Province province) {
        if(province == null) {
            return;
        }
        this.lastSelectedProvince = province;
        this.addTextToProvinceNameLabel();
        this.addTextToDevelopedLand();
        this.addOwnerShipText();
        this.writeOwnershipTable();
        this.writePlayerInfluenceText();
    }

    private void addTextToProvinceNameLabel() {
        this.provinceNameLabel.setText(this.lastSelectedProvince.getProvinceName());
    }

    private void addTextToDevelopedLand() {
        this.developedLand.setText("Developed land: "+this.lastSelectedProvince.getAmountOfSettledLand()
                + ", undeveloped: "+this.lastSelectedProvince.getAmountOfUnsettledLand()
        +" total: "+this.lastSelectedProvince.getAllLandFractions().size());
    }

    private void addOwnerShipText() {
        String infoText = "Land fraction ownership: ";
        this.ownerShip.setText(infoText);
    }

    private void writePlayerInfluenceText() {
        String infoText = "Influence: "
                +this.lastSelectedProvince.getProvinceInfluenceTable().getFactionInfluenceHere(this.getPlayerFaction());
        this.playerInfluence.setText(infoText);
    }

    private void writeOwnershipTable() {
        //Map<Faction, Integer> ownershipMap = this.lastSelectedProvince.getFractionOwnershipMap();

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for(Map.Entry<Faction, Integer> oneEntry : this.lastSelectedProvince.getFractionOwnershipMap().entrySet()) {
            if(oneEntry.getKey() != null) {
                listModel.addElement(oneEntry.getKey().getFactionName()+": "+oneEntry.getValue());
                //infoText = infoText + oneEntry.getKey().getFactionName()+ ":"+oneEntry.getValue() + ". ";
            }
        }
        this.ownershipList.setModel(listModel);
        //this.ownershipList = new JList<>(listModel);
    }

    public void activateLandClearOnSelectedProvince() {
        if(this.lastSelectedProvince == null) {
            return;
        }
        this.activateLandClear(this.getPlayerFaction(), this.lastSelectedProvince, 1);
        this.updateViewForProvince(this.lastSelectedProvince);
    }

    private void activateLandClear(Faction playerFaction, Province targetProvince, int amount) {
        this.factionActionInterface.performLandClearance(playerFaction, targetProvince, amount);
    }

    private Faction getPlayerFaction() {
        return this.factionPhoneBook.getPlayerFaction();
    }

    public void activateLandPurchaseOnSelectedProvince() {
        if(this.lastSelectedProvince == null) {
            return;
        }
        activateLandPurchase(this.getPlayerFaction(), this.lastSelectedProvince, 1);
        this.updateViewForProvince(this.lastSelectedProvince);
    }

    private void activateLandPurchase(Faction playerFaction, Province targetProvince, int amount) {
        this.factionActionInterface.performLandPurchase(playerFaction, targetProvince);
    }

}
