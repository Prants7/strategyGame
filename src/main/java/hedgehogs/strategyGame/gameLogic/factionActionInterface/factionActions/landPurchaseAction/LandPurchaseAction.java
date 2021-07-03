package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landPurchaseAction;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.AbstractFactionAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionActionCostImp;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionActionGainImp;
import hedgehogs.strategyGame.gameLogic.factionReousrceInterface.FactionResourceInterface;
import hedgehogs.strategyGame.gameLogic.factionReousrceInterface.ResourceType;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.gameLogic.land.landFractction.LandFraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LandPurchaseAction extends AbstractFactionAction {

    @Autowired
    public LandPurchaseAction(FactionResourceInterface factionResourceInterface) {
        super(factionResourceInterface);
    }

    @Override
    protected boolean passesSystematicConstraints(Faction callerFaction, Province location, int amount) {
        int amountOfSettledLand = location.getAmountOfSettledLand();
        int amountOfTargetFactionLand = 0;
        if(location.getFractionOwnershipMap().containsKey(location)) {
            amountOfTargetFactionLand = location.getFractionOwnershipMap().get(location);
        }
        if(amountOfSettledLand <= amountOfTargetFactionLand) {
            return false;
        }
        if(!location.accessLocationOffices().factionHasOffice(callerFaction)) {
            return false;
        }
        return true;
    }

    @Override
    protected void runActionScript(Faction callerFaction, Province location, int amount) {
        LandFraction targetFractionToPurchase = location.getFirstFractionNotInHandsOfFaction(callerFaction);
        if(!targetFractionToPurchase.isManaged()) {
            System.out.println("Aborted land purchase in perform module as got unmanaged land");
            return;
        }
        targetFractionToPurchase.changeOwner(callerFaction);
    }

    @Override
    protected void addResourceGains(List<FactionActionGainImp> addLocation) {

    }

    @Override
    protected void addResourceCosts(List<FactionActionCostImp> addLocation) {
        addLocation.add(new FactionActionCostImp(ResourceType.GOLD, 2));
    }

    @Override
    protected String bootGiveActionName() {
        return "Land purchase action";
    }
}
