package hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.AbstractFactionAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionAction;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;

public class TimedActionWrapper {
    private FactionAction designatedAction;
    private Faction targetFaction;
    private Province location;
    private int amount;
    private int time;
    private boolean finished;

    public TimedActionWrapper(FactionAction actionBase, Faction targetFaction, Province location, int amount) {
        this.designatedAction = actionBase;
        this.targetFaction = targetFaction;
        this.location = location;
        this.amount = amount;
        this.time = 1;
        this.finished = false;
        this.fillActionCost();
    }

    private void fillActionCost() {
        this.designatedAction.callToDoCosts(this.targetFaction, this.location);
    }

    private void fillAction() {
        this.designatedAction.doAction(targetFaction, location, amount);
        this.finished = true;
    }

    public void countDown() {
        this.time--;
        if(time < 1){
            this.fillAction();
        }
    }

    public boolean isFinished() {
        return finished;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public FactionAction getDesignatedAction() {
        return designatedAction;
    }

    public String getDescriptionString() {
        return this.targetFaction.getFactionName() + " " + this.designatedAction.getActionName() + " "
                + this.getTurnsTillComplete();
    }

    public int getTurnsTillComplete() {
        return this.time;
    }
}
