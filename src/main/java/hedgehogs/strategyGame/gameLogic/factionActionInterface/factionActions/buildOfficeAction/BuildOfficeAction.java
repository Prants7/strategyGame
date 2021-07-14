package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.buildOfficeAction;

import hedgehogs.strategyGame.gameLogic.agents.base.Agent;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.AbstractFactionAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionActionCostImp;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionActionGainImp;
import hedgehogs.strategyGame.gameLogic.factionReousrceInterface.FactionResourceInterface;
import hedgehogs.strategyGame.gameLogic.factionReousrceInterface.ResourceType;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.gameLogic.land.buildings.offices.base.Office;
import hedgehogs.strategyGame.gameLogic.land.buildings.offices.instances.FamilyHall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuildOfficeAction extends AbstractFactionAction {

    @Autowired
    public BuildOfficeAction(FactionResourceInterface factionResourceInterface) {
        super(factionResourceInterface);
    }

    @Override
    protected int bootGiveStandardFillTime() {
        return 5;
    }

    @Override
    protected boolean passesSystematicConstraints(Faction callerFaction, Province location) {
        if(location.accessLocationOffices().factionHasOffice(callerFaction)) {
            return false;
        }
        return true;
    }

    @Override
    protected void runActionScriptWithoutAgent(Faction callerFaction, Province location) {
        this.tempBuildFamilyHall(callerFaction, location);
    }

    private void tempBuildFamilyHall(Faction callerFaction, Province location) {
        Office newFamilyHall = new FamilyHall(callerFaction);
        location.accessLocationOffices().addNewOffice(newFamilyHall);
    }

    @Override
    protected void addResourceGains(List<FactionActionGainImp> addLocation) {

    }

    @Override
    protected void addResourceCosts(List<FactionActionCostImp> addLocation) {
        addLocation.add(new FactionActionCostImp(ResourceType.GOLD, 20));
    }

    @Override
    protected String bootGiveActionName() {
        return "Building family hall";
    }
}
