package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.buildOfficeAction;

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
import hedgehogs.strategyGame.gameLogic.land.buildings.offices.base.Office;
import hedgehogs.strategyGame.gameLogic.land.buildings.offices.instances.FamilyHall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class BuildOfficeAction extends AbstractFactionAction {

    @Autowired
    public BuildOfficeAction(FactionResourceInterface factionResourceInterface,
                             TimedActionWaitList timedActionWaitList) {
        super(factionResourceInterface,timedActionWaitList);
    }

    @Override
    protected int bootGiveStandardFillTime() {
        return 5;
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
        if(location.accessLocationOffices().factionHasOffice(callerFaction)) {
            return false;
        }
        return true;
    }

    @Override
    protected void runActionScript(FactionActionInput input) {
        Province location = this.getPrimaryLocationFromInput(input);
        Faction callerFaction = this.getFactionFromInput(input);
        this.tempBuildFamilyHall(callerFaction, location);
    }

    private void tempBuildFamilyHall(Faction callerFaction, Province location) {
        Office newFamilyHall = new FamilyHall(callerFaction);
        location.accessLocationOffices().addNewOffice(newFamilyHall);
    }

    @Override
    protected void addResourceGains(List<FactionActionGainImp> addLocation) {

    }

    @Override
    protected void addResourceCosts(List<FactionActionCost> addLocation) {
        addLocation.add(new FACFlatCost(ResourceType.GOLD, 20));
    }

    @Override
    protected String bootGiveActionName() {
        return "Building family hall";
    }
}
