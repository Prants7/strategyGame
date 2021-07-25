package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase;


import hedgehogs.strategyGame.gameLogic.agents.base.Agent;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.ActionInputName;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.FactionActionInput;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.inputSockets.AgentSocket;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.inputSockets.InputSocket;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper.TimedActionWaitList;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper.TimedActionWrapper;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper.TimedActionWrapperImp;
import hedgehogs.strategyGame.gameLogic.factionReousrceInterface.FactionResourceInterface;
import hedgehogs.strategyGame.gameLogic.factionReousrceInterface.ResourceSettings;
import hedgehogs.strategyGame.gameLogic.factionReousrceInterface.ResourceType;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractFactionAction implements FactionAction, TimedActionCommandInterface {
    private FactionResourceInterface factionResourceInterface;
    private TimedActionWaitList timedActionWaitList;
    private List<FactionActionCostImp> costs;
    private List<FactionActionGainImp> gains;
    private Set<ActionInputName> usedInputFields;
    private String actionName;
    private int standardFillTime;

    @Autowired
    public AbstractFactionAction(FactionResourceInterface factionResourceInterface, TimedActionWaitList timedActionWaitList) {
        this.factionResourceInterface = factionResourceInterface;
        this.timedActionWaitList = timedActionWaitList;
    }

    @PostConstruct
    private void bootUp() {
        this.costs = new ArrayList<>();
        this.gains = new ArrayList<>();
        this.addResourceCosts(this.costs);
        this.addResourceGains(this.gains);
        this.actionName = this.bootGiveActionName();
        this.standardFillTime = this.bootGiveStandardFillTime();
        this.usedInputFields = new HashSet<>();
        this.bootAddRequiredInputFieldNames(this.usedInputFields);
    }

    protected abstract int bootGiveStandardFillTime();

    @Override
    public boolean allowedToDoAction(FactionActionInput input) {
        if(!checkIfInputHasRequiredFields(input)) {
            System.out.println("failed if has required fields check");
            return false;
        }
        if(!input.hasAgent()) {
            System.out.println("failed has agent check");
            return false;
        }
        if(input.getAgent().isLockedInTask()) {
            System.out.println("failed lock in task check");
            return false;
        }
        if(!this.checkIfInputFieldsAllowCosts(input)) {
            System.out.println("failed the allow costs check");
            return false;
        }
        if(!this.passesSystematicConstraints(input)) {
            System.out.println("fails systematic constraints");
            return false;
        }
        System.out.println("passes allowed do do action");
        return true;
    }

    private boolean checkIfInputHasRequiredFields(FactionActionInput input) {
        return this.checkExistenceOfAllFields(input, this.usedInputFields);
    }

    protected abstract void bootAddRequiredInputFieldNames(Set<ActionInputName> saveLocation);

    private boolean checkExistenceOfAllFields(FactionActionInput input, Set<ActionInputName> requiredInputs) {
        for(ActionInputName oneName : requiredInputs) {
            if(!hasRequiredField(input, oneName)) {
                return false;
            }
        }
        return true;
    }

    private boolean hasRequiredField(FactionActionInput input, ActionInputName oneInputField) {
        InputSocket foundSocket = input.getInputValueByEnum(oneInputField);
        if(foundSocket == null) {
            return false;
        }
        if(!foundSocket.hasElement()) {
            return false;
        }
        return true;
    }

    protected abstract boolean passesSystematicConstraints(FactionActionInput input);

    private boolean checkIfInputFieldsAllowCosts(FactionActionInput input) {
        if(this.costs.isEmpty()) {
            return true;
        }
        if(!this.checkIfCanDoCosts(input)) {
            return false;
        }
        return true;
    }

    private boolean checkIfCanDoCosts(FactionActionInput input) {
        Faction foundFaction = this.getFactionFromInput(input);
        Province foundLocation = this.getPrimaryLocationFromInput(input);
        for(FactionActionCostImp oneCost : this.costs) {
            if(!this.factionResourceInterface.canRemoveResourcesFromFaction(
                    getResourceSettings(foundFaction, foundLocation, oneCost))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void startAction(FactionActionInput input) {
        if(!this.allowedToDoAction(input)) {
            return;
        }
        TimedActionWrapper newTimedAction = this.makeTimedWrapperBasedOnThis(input);
        this.timedActionWaitList.addNewTimedAction(newTimedAction);
    }

    @Override
    public void forceDoAction(FactionActionInput input) {
        if(!this.passesSystematicConstraints(input)) {
            System.out.println("Failed to force through action cause of systematic constraints");
            return;
        }
        this.runActionScript(input);
    }

    protected abstract void runActionScript(FactionActionInput input);

    private ResourceSettings getResourceSettings(Faction callerFaction, Province location, FactionActionCostImp cost) {
        return getDirectionNeutralResourceSettings(callerFaction, location, cost.getResourceType(), cost.getAmount());
    }

    private ResourceSettings getResourceSettings(Faction callerFaction, Province location, FactionActionGainImp gain) {
        return getDirectionNeutralResourceSettings(callerFaction, location, gain.getResourceType(), gain.getAmount());
    }

    private ResourceSettings getDirectionNeutralResourceSettings(
            Faction callerFaction,
            Province location,
            ResourceType type,
            int amount) {
        return new ResourceSettings()
                .setFaction(callerFaction)
                .setProvince(location)
                .setResourceType(type)
                .setAmount(amount);
    }

    protected abstract void addResourceGains(List<FactionActionGainImp> addLocation);

    protected abstract void addResourceCosts(List<FactionActionCostImp> addLocation);

    @Override
    public String getCostsString() {
        if(this.costs.isEmpty()) {
            return "free";
        }
        else {
            String costList = "";
            for(FactionActionCostImp oneCost : this.costs) {
                if(!costList.equals("")) {
                    costList += ", ";
                }
                costList += oneCost.getResourceType().name()+": "+oneCost.getAmount();
            }
            return costList;
        }
    }

    protected abstract String bootGiveActionName();

    @Override
    public String getActionName() {
        return this.actionName;
    }

    private TimedActionWrapper makeTimedWrapperBasedOnThis(FactionActionInput input) {
        int calculatedActionTime = this.calculateActionTimeForFactionOnLocation(input);
        TimedActionWrapperImp newWrapper =
                new TimedActionWrapperImp(this, input, calculatedActionTime);
        return newWrapper;
    }

    private int calculateActionTimeForFactionOnLocation(FactionActionInput input) {
        return this.standardFillTime;
    }

    protected AgentSocket accessAgentSocketFromInput(FactionActionInput input) {
        return (AgentSocket) input.getInputValueByEnum(ActionInputName.AGENT);
    }

    protected Province getPrimaryLocationFromInput(FactionActionInput input) {
        /*if(input.hasAgent()) {
            return input.getAgent().getLocation();
        }
        return null*/
        return this.accessAgentSocketFromInput(input).getElement().getLocation();
    }

    protected Faction getFactionFromInput(FactionActionInput input) {
        /*if(input.hasAgent()) {
            return input.getAgent().getAlignmentFaction();
        }
        return null;*/
        return this.accessAgentSocketFromInput(input).getElement().getAlignmentFaction();
    }



    @Override
    public boolean doActionCosts(FactionActionInput input) {
        if(this.costs.isEmpty()) {
            return true;
        }
        Faction foundFaction = this.getFactionFromInput(input);
        Province foundLocation = this.getPrimaryLocationFromInput(input);
        this.doCosts(foundFaction, foundLocation);
        return true;
    }

    private boolean doCosts(Faction callerFaction, Province location) {
        for(FactionActionCostImp oneCost : this.costs) {
            if(!this.factionResourceInterface.removeResourceFromFaction(
                    getResourceSettings(callerFaction, location, oneCost))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean doActionFunctionality(FactionActionInput input) {
        if(!this.passesSystematicConstraints(input)) {
            return false;
        }
        this.runActionScript(input);
        return true;
    }

    @Override
    public boolean doGiveActionRewards(FactionActionInput input) {
        Faction foundFaction = this.getFactionFromInput(input);
        Province foundLocation = this.getPrimaryLocationFromInput(input);
        this.doGains(foundFaction, foundLocation);
        return true;
    }

    private boolean doGains(Faction callerFaction, Province location) {
        for(FactionActionGainImp oneGain : this.gains) {
            if(!this.factionResourceInterface.addResourceToFaction(
                    getResourceSettings(callerFaction, location, oneGain))) {
                return false;
            }
        }
        return true;
    }

}
