package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landPurchaseAction;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.AbstractFactionAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionActionCost.FACFlatCost;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionActionCost.FactionActionCost;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionActionGainImp;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.ActionInputName;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.FactionActionInput;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper.TimedActionWaitList;
import hedgehogs.strategyGame.gameLogic.factionReousrceInterface.FactionResourceInterface;
import hedgehogs.strategyGame.gameLogic.factionReousrceInterface.ResourceType;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.gameLogic.land.landFractction.LandFraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class LandPurchaseAction extends AbstractFactionAction {

    @Autowired
    public LandPurchaseAction(FactionResourceInterface factionResourceInterface,
                              TimedActionWaitList timedActionWaitList) {
        super(factionResourceInterface, timedActionWaitList);
    }

    @Override
    protected int bootGiveStandardFillTime() {
        return 1;
    }

    /*@Override
    protected boolean checkIfInputHasRequiredFields(FactionActionInput input) {
        if(!input.hasAgent()) {
            return false;
        }
        return true;
    }*/

    @Override
    protected void bootAddRequiredInputFieldNames(Set<ActionInputName> saveLocation) {
        saveLocation.add(ActionInputName.AGENT);
    }

    @Override
    protected boolean passesSystematicConstraints(FactionActionInput input) {
        Province location = this.getPrimaryLocationFromInput(input);
        Faction callerFaction = this.getFactionFromInput(input);
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
    protected void runActionScript(FactionActionInput input) {
        Province location = this.getPrimaryLocationFromInput(input);
        Faction callerFaction = this.getFactionFromInput(input);
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
    protected void addResourceCosts(List<FactionActionCost> addLocation) {
        addLocation.add(new FACFlatCost(ResourceType.GOLD, 2));
    }

    @Override
    protected String bootGiveActionName() {
        return "Land purchase action";
    }
}
