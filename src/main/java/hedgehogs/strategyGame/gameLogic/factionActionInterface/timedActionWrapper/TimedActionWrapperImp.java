package hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper;

import hedgehogs.strategyGame.gameLogic.agents.base.Agent;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.AbstractFactionAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionAction;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;

public class TimedActionWrapperImp implements TimedActionWrapper {
    private Agent starterAgent;
    private FactionAction designatedAction;
    private Faction targetFaction;
    private Province location;
    private int time;
    private boolean finished;

    public TimedActionWrapperImp(FactionAction designatedAction, Agent agent,
                                 int time) {
        this.starterAgent = agent;
        this.designatedAction = designatedAction;
        this.targetFaction = agent.getAlignmentFaction();
        this.location = agent.getLocation();
        this.time = time;
        this.finished = false;
        this.fillActionCost();
        doFinishedCheck();
    }

    private void doFinishedCheck() {
        if(time < 1) {
            this.fillAction();
        }
    }

    private void fillActionCost() {
        this.designatedAction.callToDoCosts(this.targetFaction, this.location);
    }

    private void fillAction() {
        this.designatedAction.doAction(this.starterAgent);
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
        return designatedAction;
    }

    @Override
    public String getDescriptionString() {
        return this.targetFaction.getFactionName() + " " + this.designatedAction.getActionName() + " "
                + this.getTurnsTillComplete();
    }

    @Override
    public int getTurnsTillComplete() {
        return this.time;
    }
}
