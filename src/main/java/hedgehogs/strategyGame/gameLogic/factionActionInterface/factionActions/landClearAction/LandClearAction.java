package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landClearAction;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionCostAndGains.FactionActionCostAndGainBase;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionCostAndGains.FactionActionGainImp;
import hedgehogs.strategyGame.gameLogic.factionReousrceInterface.ResourceType;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LandClearAction extends FactionActionCostAndGainBase {
    @Autowired
    private LandClearCheck landClearCheck;
    @Autowired
    private LandClearModule landClearModule;

    public boolean allowedToDoAction(Faction callerFaction, Province location, int amount) {
        if(!this.checkIfCanDoCosts(callerFaction, location)) {
            return false;
        }
        return this.landClearCheck.allowedToDoAction(callerFaction, location, amount);
    }

    public void doAction(Faction callerFaction, Province location, int amount) {
        if(!this.checkIfCanDoCosts(callerFaction, location)) {
            return;
        }
        if(this.allowedToDoAction(callerFaction, location, amount)) {
            this.doCosts(callerFaction, location);
            this.landClearModule.doClearLandInProvince(callerFaction, location, amount);
            this.doGains(callerFaction, location);

        }
    }

    @Override
    protected void addResourceGains() {
        this.addGain(new FactionActionGainImp(ResourceType.INFLUENCE, 10));
    }

    @Override
    protected void addResourceCosts() {

    }
}
