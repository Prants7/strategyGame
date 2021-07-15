package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput;

import hedgehogs.strategyGame.gameLogic.agents.base.Agent;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;

public class FactionActionInputImp implements FactionActionInput {
    private Agent agent;
    private Province otherLocation;
    private Faction designatedFaction;
    private Province firstLocation;

    public FactionActionInputImp() {}

    @Override
    public FactionActionInput setAgent(Agent agent) {
        this.agent = agent;
        return this;
    }

    @Override
    public boolean hasAgent() {
        return this.agent != null;
    }

    @Override
    public Agent getAgent() {
        return this.agent;
    }

    @Override
    public FactionActionInput setFaction(Faction targetFaction) {
        this.designatedFaction = targetFaction;
        return this;
    }

    @Override
    public boolean hasFaction() {
        return this.designatedFaction != null;
    }

    @Override
    public Faction getFaction() {
        return this.designatedFaction;
    }

    @Override
    public FactionActionInput setFirstLocation(Province firstLocation) {
        this.firstLocation = firstLocation;
        return this;
    }

    @Override
    public boolean hasFirstLocation() {
        return this.firstLocation != null;
    }

    @Override
    public Province getFirstLocation() {
        return this.firstLocation;
    }

    @Override
    public FactionActionInput setOtherLocation(Province otherLocation) {
        this.otherLocation = otherLocation;
        return this;
    }

    @Override
    public boolean hasOtherLocation() {
        return this.otherLocation != null;
    }

    @Override
    public Province getOtherLocation() {
        return this.otherLocation;
    }
}
