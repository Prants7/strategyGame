package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.moveAgentAction;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.AbstractFactionAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionActionCostImp;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionActionGainImp;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.FactionActionInput;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper.TimedActionWaitList;
import hedgehogs.strategyGame.gameLogic.factionReousrceInterface.FactionResourceInterface;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MoveAgentAction extends AbstractFactionAction {

    public MoveAgentAction(FactionResourceInterface factionResourceInterface,
                           TimedActionWaitList timedActionWaitList) {
        super(factionResourceInterface, timedActionWaitList);
    }

    @Override
    protected int bootGiveStandardFillTime() {
        return 2;
    }

    @Override
    protected boolean checkIfInputHasRequiredFields(FactionActionInput input) {
        if(!input.hasAgent()) {
            return false;
        }
        if(!input.hasOtherLocation()) {
            return false;
        }
        return true;
    }

    @Override
    protected boolean passesSystematicConstraints(FactionActionInput input) {
        if(input.getAgent().getLocation() == input.getOtherLocation()) {
            return false;
        }
        if(!input.getAgent().getLocation().hasDirectAccessTo(input.getOtherLocation())) {
            return false;
        }
        return true;
    }

    @Override
    protected void runActionScript(FactionActionInput input) {
        input.getAgent().moveAgent(input.getOtherLocation());

    }

    @Override
    protected void addResourceGains(List<FactionActionGainImp> addLocation) {

    }

    @Override
    protected void addResourceCosts(List<FactionActionCostImp> addLocation) {

    }

    @Override
    protected String bootGiveActionName() {
        return "Move agent";
    }
}
