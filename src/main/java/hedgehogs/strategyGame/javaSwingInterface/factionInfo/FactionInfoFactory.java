package hedgehogs.strategyGame.javaSwingInterface.factionInfo;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class FactionInfoFactory {
    private JLabel treasuryBalance;
    private Faction factionValue;

    public JPanel getFactionInfoPanel(Faction faction) {
        this.setLocalFactionValue(faction);
        JPanel panel=new JPanel();
        panel.add(this.getFactionNameLabel(faction));
        panel.add(this.getFactionGoldBalanceLabel(faction));
        return panel;
    }

    private JLabel getTestLabel() {
        JLabel testLabel = new JLabel("faction info");
        return testLabel;
    }

    private JLabel getFactionNameLabel(Faction faction) {
        JLabel testLabel = new JLabel("Faction: "+faction.getFactionName());
        return testLabel;
    }

    private JLabel getFactionGoldBalanceLabel(Faction faction) {
        this.treasuryBalance = new JLabel("");
        this.writeTreasuryBalance(faction);
        return treasuryBalance;
    }

    private void setLocalFactionValue(Faction faction) {
        this.factionValue = faction;
    }

    private void writeTreasuryBalance(Faction faction) {
        this.treasuryBalance.setText("Gold: "+faction.getCurrentTreasuryGoldBalance());
    }

    public void updateData() {
        if(this.factionValue != null) {
            this.writeTreasuryBalance(this.factionValue);
        }
    }
}
