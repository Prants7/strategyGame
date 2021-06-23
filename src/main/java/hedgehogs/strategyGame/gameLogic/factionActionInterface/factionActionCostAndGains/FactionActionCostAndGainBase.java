package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionCostAndGains;


import hedgehogs.strategyGame.gameLogic.factionReousrceInterface.FactionResourceInterface;
import hedgehogs.strategyGame.gameLogic.factionReousrceInterface.ResourceSettings;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

public abstract class FactionActionCostAndGainBase {
    @Autowired
    private FactionResourceInterface factionResourceInterface;
    private List<FactionActionCostImp> costs;
    private List<FactionActionGainImp> gains;

    @PostConstruct
    private void bootUp() {
        this.costs = new ArrayList<>();
        this.gains = new ArrayList<>();
        this.addResourceCosts();
        this.addResourceGains();
    }

    protected void addGain(FactionActionGainImp newGain) {
        this.gains.add(newGain);
    }

    protected void addCost(FactionActionCostImp newCost) {
        this.costs.add(newCost);
    }

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

    protected abstract void addResourceGains();

    protected abstract void addResourceCosts();
}
