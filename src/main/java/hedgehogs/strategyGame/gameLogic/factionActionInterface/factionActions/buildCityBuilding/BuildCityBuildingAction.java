package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.buildCityBuilding;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.AbstractFactionAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionActionCostImp;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionActionGainImp;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.ActionInputName;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.FactionActionInput;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper.TimedActionWaitList;
import hedgehogs.strategyGame.gameLogic.factionReousrceInterface.FactionResourceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class BuildCityBuildingAction extends AbstractFactionAction {

    @Autowired
    public BuildCityBuildingAction(FactionResourceInterface factionResourceInterface,
                                   TimedActionWaitList timedActionWaitList) {
        super(factionResourceInterface, timedActionWaitList);
    }

    @Override
    protected int bootGiveStandardFillTime() {
        return 2;
    }

    /*@Override
    protected boolean checkIfInputHasRequiredFields(FactionActionInput input) {
        if(!input.hasAgent()) {
            return false;
        }
        if(!input.hasCityBuilding()) {
            return false;
        }
        return true;
    }*/

    @Override
    protected void bootAddRequiredInputFieldNames(Set<ActionInputName> saveLocation) {
        saveLocation.add(ActionInputName.AGENT);
        saveLocation.add(ActionInputName.BUILDING);
    }

    @Override
    protected boolean passesSystematicConstraints(FactionActionInput input) {
        if(input.getAgent().getLocation().accessBuildings().getAmountOfUnusedSlots() < 1) {
            return false;
        }
        return true;
    }

    @Override
    protected void runActionScript(FactionActionInput input) {
        input.getAgent().getLocation().accessBuildings().attemptToConstructBuildingHere(input.getCityBuilding());
    }

    @Override
    protected void addResourceGains(List<FactionActionGainImp> addLocation) {

    }

    @Override
    protected void addResourceCosts(List<FactionActionCostImp> addLocation) {

    }

    @Override
    protected String bootGiveActionName() {
        return "Build a new building";
    }
}
