package hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper;

import hedgehogs.strategyGame.gameLogic.agents.base.Agent;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.AbstractFactionAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.TimedActionCommandInterface;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.FactionActionInput;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;

public class TimedActionWrapperImp implements TimedActionWrapper {
    private FactionActionInput actionInput;
    private TimedActionCommandInterface designatedAction;
    private Faction targetFaction;
    private Province location;
    private int time;
    private boolean finished;

    public TimedActionWrapperImp(TimedActionCommandInterface designatedAction, FactionActionInput input,
                                 int time) {
        this.actionInput = input;
        this.designatedAction = designatedAction;
        this.targetFaction = input.getAgent().getAlignmentFaction();
        this.location = input.getAgent().getLocation();
        this.time = time;
        this.finished = false;
        this.actionInput.getAgent().lockToATask(this);
        this.fillActionCost();
        doFinishedCheck();
    }

    private void doFinishedCheck() {
        if(time < 1) {
            this.fillAction();
        }
    }

    private void fillActionCost() {
        this.designatedAction.doActionCosts(actionInput);
    }

    private void fillAction() {
        this.designatedAction.doActionFunctionality(this.actionInput);
        this.designatedAction.doGiveActionRewards(this.actionInput);
        this.actionInput.getAgent().unlockFromTask();
        this.finished = true;
    }

    @Override
    public void countDown() {
        this.time--;
        doFinishedCheck();
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    @Override
    public FactionAction getDesignatedAction() {
        return attemptToConvertAction(designatedAction);
    }

    private FactionAction attemptToConvertAction(TimedActionCommandInterface commandElement) {
        return (FactionAction) commandElement;
    }

    @Override
    public String getDescriptionString() {
        return this.targetFaction.getFactionName() + " " +
                attemptToConvertAction(this.designatedAction).getActionName() + " "
                + this.getTurnsTillComplete();
    }

    @Override
    public int getTurnsTillComplete() {
        return this.time;
    }
}
