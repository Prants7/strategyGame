package hedgehogs.strategyGame.gameLogic.factionReousrceInterface;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.factions.FactionPhoneBook;
import hedgehogs.strategyGame.gameLogic.land.Province;
import org.springframework.stereotype.Component;

@Component
public class FactionResourceInterfaceImp extends FactionResourceInterfaceBase {

    @Override
    protected boolean addResourceScript(ResourceSettings settings) {
        Faction faction = settings.getFaction();
        int amount = settings.getAmount();
        ResourceType resourceType = settings.getResourceType();
        Province location = settings .getProvince();

        if(resourceType == ResourceType.GOLD) {
            return addGoldToFaction(faction, amount);
        }
        if(settings.getResourceType() == ResourceType.INFLUENCE) {
            return addInfluenceToFaction(faction, location, amount);
        }
        return false;
    }

    private boolean addGoldToFaction(Faction targetFaction, int amount) {
        targetFaction.depositGoldToTreasury(amount);
        return true;
    }

    private boolean addInfluenceToFaction(Faction targetFaction, Province location, int amount) {
        location.getProvinceInfluenceTable().addInfluenceForFaction(targetFaction, amount);
        return true;
    }

    @Override
    protected boolean withdrawResourceScript(ResourceSettings settings) {
        Faction faction = settings.getFaction();
        int amount = settings.getAmount();
        ResourceType resourceType = settings.getResourceType();
        Province location = settings .getProvince();

        if(resourceType == ResourceType.GOLD) {
            return withdrawGold(faction, amount);
        }
        if(resourceType == ResourceType.INFLUENCE) {
            return withdrawInfluence(faction, location, amount);
        }
        return false;
    }

    private boolean withdrawGold(Faction faction, int amount) {
        if(faction.canWithdrawGoldAmount(amount)) {
            faction.withdrawGoldAmount(amount);
            return true;
        }
        return false;
    }

    private boolean withdrawInfluence(Faction faction, Province location, int amount) {
        if(location.getProvinceInfluenceTable().factionCanWithdrawAmountOfInfluence(faction, amount)) {
            location.getProvinceInfluenceTable().withdrawAmountOfInfluenceForFaction(faction,amount);
            return true;
        }
        return false;
    }
}
