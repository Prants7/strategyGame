package hedgehogs.strategyGame.gameLogic.gameTime.calculationHubs.landOwnershipHub;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.factions.FactionPhoneBook;
import hedgehogs.strategyGame.gameLogic.gameTime.calculationHubs.CalculationHub;
import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.gameLogic.world.World;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("LandOwnership")
public class LandOwnershipHubImp implements CalculationHub {
    @Autowired
    private World gameWorld;
    @Autowired
    private FactionPhoneBook factionPhoneBook;

    @Override
    public void triggerCalculations() {
        for(Province oneProvince : this.gameWorld.getAllProvinces()) {
            this.getTaxesFromOneProvince(oneProvince);
        }
    }

    private void getTaxesFromOneProvince(Province targetProvince) {
        System.out.println("Getting land ownership tax, for province: "+targetProvince.getProvinceName());
        Map<Faction, Integer> oneProvinceOwnership = targetProvince.getFractionOwnershipMap();
        for(Map.Entry<Faction, Integer> oneEntry : oneProvinceOwnership.entrySet()) {
            if(oneEntry.getKey() != null) {
                int taxValue = this.provideIncomeValueFromAmountOfOwnedLand(oneEntry.getValue());
                this.giveGoldToFaction(oneEntry.getKey(), taxValue, targetProvince);
            }
        }
    }

    private int provideIncomeValueFromAmountOfOwnedLand(int amountOfLandFractions) {
        return amountOfLandFractions * 2;
    }

    private void giveGoldToFaction(Faction targetFaction, int amountOfGold, Province sourceOfGold) {
        System.out.println("Faction: "+targetFaction.getFactionName()+", gets "+amountOfGold+" gold");
        targetFaction.depositGoldToTreasury(amountOfGold);
        System.out.println(targetFaction.getFactionName()+" gold balance after deposit: "+targetFaction.getCurrentTreasuryGoldBalance());
    }
}
