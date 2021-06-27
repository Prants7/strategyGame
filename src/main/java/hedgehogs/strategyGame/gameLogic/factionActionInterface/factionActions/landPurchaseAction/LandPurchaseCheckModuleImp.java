package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landPurchaseAction;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import org.springframework.stereotype.Component;

@Component
public class LandPurchaseCheckModuleImp implements LandPurchaseCheckModule{

    @Override
    public boolean allowedToPerformLandPurchaseForFaction(Faction targetFaction, Province targetProvince) {
        int amountOfSettledLand = targetProvince.getAmountOfSettledLand();
        int amountOfTargetFactionLand = 0;
        if(targetProvince.getFractionOwnershipMap().containsKey(targetFaction)) {
            amountOfTargetFactionLand = targetProvince.getFractionOwnershipMap().get(targetFaction);
        }
        if(amountOfSettledLand <= amountOfTargetFactionLand) {
            return false;
        }
        if(!targetProvince.accessLocationOffices().factionHasOffice(targetFaction)) {
            return false;
        }
        return true;
    }
}
