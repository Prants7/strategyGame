package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landPurchaseAction;

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
public class LandPurchaseAction extends FactionActionBase {
    @Autowired
    private LandPurchaseCheckModule landPurchaseCheck;
    @Autowired
    private LandPurchaseModule landPurchaseModule;

    @Override
    protected boolean passesSystematicConstraints(Faction callerFaction, Province location, int amount) {
        return this.landPurchaseCheck.allowedToPerformLandPurchaseForFaction(callerFaction, location);
    }

    @Override
    protected void runActionScript(Faction callerFaction, Province location, int amount) {
        this.landPurchaseModule.doLandPurchase(callerFaction, location);
    }

    @Override
    protected void addResourceGains(List<FactionActionGainImp> addLocation) {

    }

    @Override
    protected void addResourceCosts(List<FactionActionCostImp> addLocation) {
        addLocation.add(new FactionActionCostImp(ResourceType.GOLD, 2));
    }
}
