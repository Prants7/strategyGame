package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landClearAction;

import hedgehogs.strategyGame.gameLogic.agents.base.Agent;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.AbstractFactionAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionActionCostImp;
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
public class LandClearAction extends AbstractFactionAction {

    @Autowired
    public LandClearAction(FactionResourceInterface factionResourceInterface,
                           TimedActionWaitList timedActionWaitList) {
        super(factionResourceInterface, timedActionWaitList);
    }

    @Override
    protected int bootGiveStandardFillTime() {
        return 2;
    }

    /*@Override
    protected boolean checkIfInputHasRequiredFields(FactionActionInput input) {
        if(!this.accessAgentSocketFromInput(input).hasElement()) {
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
        if(location.getAmountOfUnsettledLand() < 1) {
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
        location.settleAmountOfLand(1);
    }

    @Override
    protected void addResourceGains(List<FactionActionGainImp> addLocation) {
        addLocation.add(new FactionActionGainImp(ResourceType.INFLUENCE, 10));
    }

    @Override
    protected void addResourceCosts(List<FactionActionCostImp> addLocation) {
        addLocation.add(new FactionActionCostImp(ResourceType.GOLD, 2));
    }

    @Override
    protected String bootGiveActionName() {
        return "Land clear action";
    }

}
