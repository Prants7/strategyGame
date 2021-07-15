package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase;


import hedgehogs.strategyGame.gameLogic.agents.base.Agent;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.FactionActionInput;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.FactionActionInputImp;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper.TimedActionWrapper;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper.TimedActionWrapperImp;
import hedgehogs.strategyGame.gameLogic.factionReousrceInterface.FactionResourceInterface;
import hedgehogs.strategyGame.gameLogic.factionReousrceInterface.ResourceSettings;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFactionAction implements FactionAction {
    private FactionResourceInterface factionResourceInterface;
    private List<FactionActionCostImp> costs;
    private List<FactionActionGainImp> gains;
    private String actionName;
    private int standardFillTime;

    @Autowired
    public AbstractFactionAction(FactionResourceInterface factionResourceInterface) {
        this.factionResourceInterface = factionResourceInterface;
    }

    @PostConstruct
    private void bootUp() {
        this.costs = new ArrayList<>();
        this.gains = new ArrayList<>();
        this.addResourceCosts(this.costs);
        this.addResourceGains(this.gains);
        this.actionName = this.bootGiveActionName();
        this.standardFillTime = this.bootGiveStandardFillTime();
    }

    protected abstract int bootGiveStandardFillTime();

    @Override
    public boolean allowedToDoAction(FactionActionInput input) {
        if(!input.hasAgent()) {
            return false;
        }
        Agent agent = input.getAgent();
        if(agent.getLocation() == null) {
            return false;
        }
        if(!this.checkIfCanDoCosts(agent.getAlignmentFaction(), agent.getLocation())) {
            return false;
        }
        if(!this.passesSystematicConstraints(input)) {
            return false;
        }
        return true;
    }

    protected abstract boolean passesSystematicConstraints(FactionActionInput input);

    @Override
    public void doAction(FactionActionInput input) {
        if(!this.allowedToDoAction(input)) {
            return;
        }
        this.runActionScript(input);
        this.doGains(input.getAgent().getAlignmentFaction(), input.getAgent().getLocation());
    }

    @Override
    public void forceDoAction(Faction callerFaction, Province location) {
        FactionActionInput tempHackInput = new FactionActionInputImp().setFaction(callerFaction).setFirstLocation(location);
        if(!this.passesSystematicConstraints(tempHackInput)) {
            System.out.println("Failed to force through action cause of systematic constraints");
            return;
        }
        this.runActionScriptWithoutAgent(tempHackInput);
    }

    protected void runActionScript(FactionActionInput input) {
        this.runActionScriptWithoutAgent(input);
    }

    protected abstract void runActionScriptWithoutAgent(FactionActionInput input);

    protected boolean checkIfCanDoCosts(Faction callerFaction, Province location) {
        for(FactionActionCostImp oneCost : this.costs) {
            if(!this.factionResourceInterface.canRemoveResourcesFromFaction(
                    getResourceSettings(callerFaction, location, oneCost))) {
                return false;
            }
        }
        return true;
    }

    private ResourceSettings getResourceSettings(Faction callerFaction, Province location, FactionActionCostImp cost) {
        ResourceSettings newSettings = new ResourceSettings();
        newSettings.setFaction(callerFaction);
        newSettings.setProvince(location);
        newSettings.setResourceType(cost.getResourceType());
        newSettings.setAmount(cost.getAmount());
        return newSettings;
    }

    private ResourceSettings getResourceSettings(Faction callerFaction, Province location, FactionActionGainImp gain) {
        ResourceSettings newSettings = new ResourceSettings();
        newSettings.setFaction(callerFaction);
        newSettings.setProvince(location);
        newSettings.setResourceType(gain.getResourceType());
        newSettings.setAmount(gain.getAmount());
        return newSettings;
    }

    @Override
    public boolean callToDoCosts(Faction callerFaction, Province location) {
        return this.doCosts(callerFaction, location);
    }

    protected boolean doCosts(Faction callerFaction, Province location) {
        for(FactionActionCostImp oneCost : this.costs) {
            if(!this.factionResourceInterface.removeResourceFromFaction(
                    getResourceSettings(callerFaction, location, oneCost))) {
                return false;
            }
        }
        return true;
    }

    protected boolean doGains(Faction callerFaction, Province location) {
        for(FactionActionGainImp oneGain : this.gains) {
            if(!this.factionResourceInterface.addResourceToFaction(
                    getResourceSettings(callerFaction, location, oneGain))) {
                return false;
            }
        }
        return true;
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

    @Override
    public TimedActionWrapper getActionAsTimedElement(FactionActionInput input) {
        return this.makeTimedWrapperBasedOnThis(input);
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

    protected Agent getAgentFromInput(FactionActionInput input) {
        if(!input.hasAgent()) {
            System.out.println("ERROR: trying to retrieve a null agent from FactionActionInput");
        }
        return input.getAgent();
    }

    protected Province getPrimaryLocationFromInput(FactionActionInput input) {
        if(input.hasAgent()) {
            return input.getAgent().getLocation();
        }
        else {
            return input.getFirstLocation();
        }
    }

    protected Faction getFactionFromInput(FactionActionInput input) {
        if(input.hasAgent()) {
            return input.getAgent().getAlignmentFaction();
        }
        else {
            return input.getFaction();
        }
    }
}
