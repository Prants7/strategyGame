package hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper;

import hedgehogs.strategyGame.gameLogic.agents.base.Agent;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.AbstractFactionAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.TimedActionCommandInterface;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.FactionActionInput;
import hedgehogs.strategyGame.gameLogic.factionReousrceInterface.ResourceType;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;

import java.util.HashMap;
import java.util.Map;

public class TimedActionWrapperImp implements TimedActionWrapper {
    private FactionActionInput actionInput;
    private TimedActionCommandInterface designatedAction;
    private Faction targetFaction;
    private Province location;
    private int time;
    private boolean finished;
    private Map<ResourceType, Integer> investedResources;

    public TimedActionWrapperImp(TimedActionCommandInterface designatedAction, FactionActionInput input,
                                 int time) {
        this.actionInput = input;
        this.designatedAction = designatedAction;
        this.targetFaction = input.getAgent().getAlignmentFaction();
        this.location = input.getAgent().getLocation();
        this.time = time;
        this.finished = false;
        this.investedResources = new HashMap<>();
        this.actionInput.getAgent().lockToATask(this);
        this.fillActionCost();
        this.doFinishedCheck();
    }

    private void doFinishedCheck() {
        if(time < 1) {
            this.fillAction();
        }
    }

    private void fillActionCost() {
        this.designatedAction.doActionCosts(actionInput);
        this.addSpentResourcesHere();
    }

    private void addSpentResourcesHere() {
        this.investedResources = this.designatedAction.getMapOfCostResources(actionInput);
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

    public void cancelAction() {
        this.giveBackResources();
        this.actionInput.getAgent().unlockFromTask();
        this.finished = true;
    }

    private void giveBackResources() {
        for(Map.Entry<ResourceType, Integer> oneEntry: this.investedResources.entrySet()) {
            if(oneEntry.getKey() == ResourceType.GOLD) {
                this.actionInput.getAgent().getAlignmentFaction().depositGoldToTreasury(oneEntry.getValue());
            }
            if(oneEntry.getKey() == ResourceType.INFLUENCE) {
                this.actionInput.getAgent().getLocation().getProvinceInfluenceTable().addInfluenceForFaction(
                        this.actionInput.getAgent().getAlignmentFaction(), oneEntry.getValue());
            }
        }
    }

}
