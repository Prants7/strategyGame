package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landPurchaseAction;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.gameLogic.land.landFractction.LandFraction;
import org.springframework.stereotype.Component;

@Component
public class LandPurchaseModuleImp implements LandPurchaseModule {

    @Override
    public void doLandPurchase(Faction callerFaction, Province targetProvince) {
        LandFraction targetFractionToPurchase = targetProvince.getFirstFractionNotInHandsOfFaction(callerFaction);
        if(!targetFractionToPurchase.isManaged()) {
            System.out.println("Aborted land purchase in perform module as got unmanaged land");
            return;
        }
        System.out.println("Performing purchase of landFraction in " +targetProvince.getProvinceName() +
                " From faction: "+targetFractionToPurchase.getOwner().getFactionName() + " to faction: " + callerFaction.getFactionName());
        targetFractionToPurchase.changeOwner(callerFaction);
    }
}
