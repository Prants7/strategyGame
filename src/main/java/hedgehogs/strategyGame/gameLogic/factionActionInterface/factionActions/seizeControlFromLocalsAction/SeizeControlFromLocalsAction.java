package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.seizeControlFromLocalsAction;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.AbstractFactionAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionActionCost.FactionActionCostFlatCost;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionActionGainImp;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.ActionInputName;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.FactionActionInput;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper.TimedActionWaitList;
import hedgehogs.strategyGame.gameLogic.factionReousrceInterface.FactionResourceInterface;
import hedgehogs.strategyGame.gameLogic.factionReousrceInterface.ResourceType;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class SeizeControlFromLocalsAction extends AbstractFactionAction {

    @Autowired
    public SeizeControlFromLocalsAction(FactionResourceInterface factionResourceInterface,
                                        TimedActionWaitList timedActionWaitList) {
        super(factionResourceInterface, timedActionWaitList);
    }

    @Override
    protected int bootGiveStandardFillTime() {
        return 0;
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
        if(location.accessLocationOffices().hasControllingFaction()) {
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
        location.accessLocationOffices().setControllingFaction(callerFaction);
    }

    @Override
    protected void addResourceGains(List<FactionActionGainImp> addLocation) {

    }

    @Override
    protected void addResourceCosts(List<FactionActionCostFlatCost> addLocation) {
        addLocation.add(new FactionActionCostFlatCost(ResourceType.INFLUENCE, 10));
    }

    @Override
    protected String bootGiveActionName() {
        return "Declare yourself the ruler of this city";
    }
}
