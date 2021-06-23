package hedgehogs.strategyGame.javaSwingInterface.provinceView;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.FactionActionInterface;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.factions.FactionPhoneBook;
import hedgehogs.strategyGame.gameLogic.land.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.Map;

@Component
public class ProvinceViewFactory {
    private Province lastSelectedProvince;
    private JLabel provinceNameLabel;
    private JLabel developedLand;
    private JLabel ownerShip;
    private JButton clearLand;
    private JButton buyLand;
    @Autowired
    private FactionActionInterface factionActionInterface;
    @Autowired
    private FactionPhoneBook factionPhoneBook;


    public JPanel giveProvinceView() {
        //this.lastSelectedProvince = targetProvince;
        JPanel panel=new JPanel();
        panel.add(this.getProvinceNameLabel());
        panel.add(this.getDevelopedLandLabel());
        panel.add(this.getOwnerShipLabel());
        panel.add(this.getClearLandButton());
        panel.add(this.getBuyLandButton());
        return panel;
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
        this.ownerShip = new JLabel("-");
        return this.ownerShip;
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
        addOwnerShipText();
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
        String infoText = "owners: ";
        for(Map.Entry<Faction, Integer> oneEntry : this.lastSelectedProvince.getFractionOwnershipMap().entrySet()) {
            if(oneEntry.getKey() != null) {
                infoText = infoText + oneEntry.getKey().getFactionName()+ ":"+oneEntry.getValue() + ". ";
            }
        }
        this.ownerShip.setText(infoText);
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
