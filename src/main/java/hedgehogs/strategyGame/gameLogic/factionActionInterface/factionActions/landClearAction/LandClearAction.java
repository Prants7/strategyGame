package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landClearAction;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionActionBase;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionActionCostImp;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionActionGainImp;
import hedgehogs.strategyGame.gameLogic.factionReousrceInterface.ResourceType;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LandClearAction extends FactionActionBase {
    @Autowired
    private LandClearCheck landClearCheck;
    @Autowired
    private LandClearModule landClearModule;

    @Override
    protected boolean passesSystematicConstraints(Faction callerFaction, Province location, int amount) {
        return this.landClearCheck.allowedToDoAction(callerFaction, location, amount);
    }

    @Override
    protected void runActionScript(Faction callerFaction, Province location, int amount) {
        this.landClearModule.doClearLandInProvince(callerFaction, location, amount);
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
