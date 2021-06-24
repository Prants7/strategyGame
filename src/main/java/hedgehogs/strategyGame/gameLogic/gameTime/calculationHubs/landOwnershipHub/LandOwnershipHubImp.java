package hedgehogs.strategyGame.gameLogic.gameTime.calculationHubs.landOwnershipHub;

import hedgehogs.strategyGame.gameLogic.factionReousrceInterface.FactionResourceInterface;
import hedgehogs.strategyGame.gameLogic.factionReousrceInterface.ResourceSettings;
import hedgehogs.strategyGame.gameLogic.factionReousrceInterface.ResourceType;
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
    @Autowired
    private FactionResourceInterface factionResourceInterface;

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
        return amountOfLandFractions * 4;
    }

    private void giveGoldToFaction(Faction targetFaction, int amountOfGold, Province sourceOfGold) {
        System.out.println("Faction: "+targetFaction.getFactionName()+", gets "+amountOfGold+" gold");
        //targetFaction.depositGoldToTreasury(amountOfGold);
        //this.factionResourceInterface.addResourceToFaction(this.getTaxSettings(targetFaction, sourceOfGold, amountOfGold));
        this.factionResourceInterface.addLandFractionIncomeToFaction(this.getTaxSettings(targetFaction, sourceOfGold, amountOfGold));
        System.out.println(targetFaction.getFactionName()+" gold balance after deposit: "+targetFaction.getCurrentTreasuryGoldBalance());
    }

    private ResourceSettings getTaxSettings(Faction faction, Province source, int amount) {
        ResourceSettings newSettings = new ResourceSettings();
        newSettings.setResourceType(ResourceType.GOLD);
        newSettings.setFaction(faction);
        newSettings.setProvince(source);
        newSettings.setAmount(amount);
        return newSettings;
    }

}
