package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.seizeControlFromLocalsAction;

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
public class SeizeControlFromLocalsAction extends AbstractFactionAction {

    @Autowired
    public SeizeControlFromLocalsAction(FactionResourceInterface factionResourceInterface) {
        super(factionResourceInterface);
    }

    @Override
    protected int bootGiveStandardFillTime() {
        return 0;
    }

    @Override
    protected boolean passesSystematicConstraints(Faction callerFaction, Province location, int amount) {
        if(location.accessLocationOffices().hasControllingFaction()) {
            return false;
        }
        if(!location.accessLocationOffices().factionHasOffice(callerFaction)) {
            return false;
        }
        return true;
    }

    @Override
    protected void runActionScript(Faction callerFaction, Province location, int amount) {
        location.accessLocationOffices().setControllingFaction(callerFaction);
    }

    @Override
    protected void addResourceGains(List<FactionActionGainImp> addLocation) {

    }

    @Override
    protected void addResourceCosts(List<FactionActionCostImp> addLocation) {
        addLocation.add(new FactionActionCostImp(ResourceType.INFLUENCE, 10));
    }

    @Override
    protected String bootGiveActionName() {
        return "Declare yourself the ruler of this city";
    }
}
