package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landClearAction;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.AbstractFactionAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionActionCostImp;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionActionGainImp;
import hedgehogs.strategyGame.gameLogic.factionReousrceInterface.FactionResourceInterface;
import hedgehogs.strategyGame.gameLogic.factionReousrceInterface.ResourceType;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LandClearAction extends AbstractFactionAction {

    @Autowired
    public LandClearAction(FactionResourceInterface factionResourceInterface) {
        super(factionResourceInterface);
    }

    @Override
    protected boolean passesSystematicConstraints(Faction callerFaction, Province location, int amount) {
        if(location.getAmountOfUnsettledLand() < amount) {
            return false;
        }
        if(!location.accessLocationOffices().factionHasOffice(callerFaction)) {
            return false;
        }
        return true;
    }

    @Override
    protected void runActionScript(Faction callerFaction, Province location, int amount) {
        location.settleAmountOfLand(amount);
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
