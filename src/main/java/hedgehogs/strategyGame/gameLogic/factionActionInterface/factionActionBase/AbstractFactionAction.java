package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase;


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
    }

    @Override
    public boolean allowedToDoAction(Faction callerFaction, Province location, int amount) {
        if(!this.checkIfCanDoCosts(callerFaction, location)) {
            return false;
        }
        if(!this.passesSystematicConstraints(callerFaction, location, amount)) {
            return false;
        }
        return true;
    }

    protected abstract boolean passesSystematicConstraints(Faction callerFaction, Province location, int amount);

    @Override
    public void doAction(Faction callerFaction, Province location, int amount) {
        if(!this.allowedToDoAction(callerFaction, location, amount)) {
            return;
        }
        this.runActionScript(callerFaction, location, amount);
        this.doGains(callerFaction, location);
    }

    @Override
    public void forceDoAction(Faction callerFaction, Province location, int amount) {
        if(!this.passesSystematicConstraints(callerFaction, location, amount)) {
            System.out.println("Failed to force through action cause of systematic constraints");
            return;
        }
        this.runActionScript(callerFaction, location, amount);
    }

    protected abstract void runActionScript(Faction callerFaction, Province location, int amount);

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
}
