package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionModules.landPurchaseCheckModule;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import org.springframework.stereotype.Component;

@Component
public class LandPurchaseCheckModuleImp implements LandPurchaseCheckModule {
    @Override
    public boolean allowedToPerformLandPurchaseForFaction(Faction targetFaction, Province targetProvince) {
        System.out.println("Allowing all land purchase, this time its "+targetFaction.getFactionName());
        return true;
    }
}
