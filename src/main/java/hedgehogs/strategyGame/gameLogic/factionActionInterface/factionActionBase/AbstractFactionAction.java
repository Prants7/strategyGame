package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase;


import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionActionCost.FACFlatCost;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionActionCost.FactionActionCost;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.ActionInputName;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.FactionActionInput;
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
import java.util.*;

public abstract class AbstractFactionAction implements FactionAction, TimedActionCommandInterface {
    private FactionResourceInterface factionResourceInterface;
    private TimedActionWaitList timedActionWaitList;
    private List<FactionActionCost> costs;
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
        if(!agentNotLockedInTaskCheck(input)) {
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

    private boolean agentNotLockedInTaskCheck(FactionActionInput input) {
        if(this.usedInputFields.contains(ActionInputName.AGENT)) {
            if(input.getAgent().isLockedInTask()) {
                return false;
            }
        }
        return true;
    }

    private boolean hasRequiredField(FactionActionInput input, ActionInputName oneInputField) {
        return input.checkInputByEnum(oneInputField);
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
        for(FactionActionCost oneCost : this.costs) {
            if(!this.factionResourceInterface.canRemoveResourcesFromFaction(
                    getResourceSettings(foundFaction, foundLocation, oneCost, input))) {
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

    private ResourceSettings getResourceSettings(Faction callerFaction, Province location,
                                                 FactionActionCost cost, FactionActionInput input) {
        return getDirectionNeutralResourceSettings(callerFaction, location, cost.getResourceType(), cost.getAmount(input));
    }

    private ResourceSettings getResourceSettings(Faction callerFaction, Province location,
                                                 FactionActionGainImp gain, FactionActionInput input) {
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

    protected abstract void addResourceCosts(List<FactionActionCost> addLocation);

    @Override
    public String getCostsString(FactionActionInput input) {
        if(this.costs.isEmpty()) {
            return "free";
        }
        else {
            String costList = "";
            for(FactionActionCost oneCost : this.costs) {
                if(!costList.equals("")) {
                    costList += ", ";
                }
                costList += oneCost.getResourceType().name()+": "+oneCost.getAmount(input);
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

    /*protected AgentSocket accessAgentSocketFromInput(FactionActionInput input) {
        return (AgentSocket) input.getInputValueByEnum(ActionInputName.AGENT);
    }*/

    protected Province getPrimaryLocationFromInput(FactionActionInput input) {
        if(input.checkInputByEnum(ActionInputName.AGENT)) {
            return input.getAgent().getLocation();
        }
        return null;
    }

    protected Faction getFactionFromInput(FactionActionInput input) {
        if(input.checkInputByEnum(ActionInputName.AGENT)) {
            return input.getAgent().getAlignmentFaction();
        }
        return null;
    }



    @Override
    public boolean doActionCosts(FactionActionInput input) {
        if(this.costs.isEmpty()) {
            return true;
        }
        Faction foundFaction = this.getFactionFromInput(input);
        Province foundLocation = this.getPrimaryLocationFromInput(input);
        this.doCosts(foundFaction, foundLocation, input);
        return true;
    }

    private boolean doCosts(Faction callerFaction, Province location, FactionActionInput input) {
        for(FactionActionCost oneCost : this.costs) {
            if(!this.factionResourceInterface.removeResourceFromFaction(
                    getResourceSettings(callerFaction, location, oneCost, input))) {
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
        this.doGains(foundFaction, foundLocation, input);
        return true;
    }

    private boolean doGains(Faction callerFaction, Province location, FactionActionInput input) {
        for(FactionActionGainImp oneGain : this.gains) {
            if(!this.factionResourceInterface.addResourceToFaction(
                    getResourceSettings(callerFaction, location, oneGain, input))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Set<ActionInputName> getRequiredInputs() {
        return new HashSet<>(this.usedInputFields);
    }

    public Map<ResourceType, Integer> getMapOfCostResources(FactionActionInput input) {
        Map<ResourceType, Integer> resourceMap = new HashMap<>();
        for(FactionActionCost oneCostElement: this.costs) {
            resourceMap.put(oneCostElement.getResourceType(), oneCostElement.getAmount(input));
        }
        return resourceMap;
    }
}
