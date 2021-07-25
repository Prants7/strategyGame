package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput;

import hedgehogs.strategyGame.gameLogic.agents.base.Agent;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.inputSockets.*;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.gameLogic.land.buildings.cityBuildings.base.CityBuilding;

import java.util.HashMap;
import java.util.Map;

public class FactionActionInputImp implements FactionActionInput {
    private Map<ActionInputName, InputSocketImp<?>> inputFields;

    public FactionActionInputImp() {
        this.inputFields = new HashMap<>();
    }

    public FactionActionInput setNewValue(ActionInputName inputName, Object newValue) {
        InputSocketImp<?> newSocket = new InputSocketImp<>(newValue);
        this.inputFields.put(inputName, newSocket);
        return this;
    }

    public Object getValueWithKey(ActionInputName keyName) {
        if(this.inputFields.containsKey(keyName)) {
            return this.inputFields.get(keyName).getElement();
        }
        return null;
    }

    @Override
    public FactionActionInput setAgent(Agent agent) {
        return this.setNewValue(ActionInputName.AGENT, agent);
    }

    @Override
    public Agent getAgent() {
        return (Agent) this.getValueWithKey(ActionInputName.AGENT);
    }

    @Override
    public FactionActionInput setOtherLocation(Province otherLocation) {
        return this.setNewValue(ActionInputName.NEXT_LOCATION, otherLocation);
    }

    @Override
    public Province getOtherLocation() {
        return (Province) this.getValueWithKey(ActionInputName.NEXT_LOCATION);
    }

    @Override
    public FactionActionInput setCityBuilding(CityBuilding cityBuilding) {
        return this.setNewValue(ActionInputName.BUILDING, cityBuilding);
    }

    @Override
    public CityBuilding getCityBuilding() {
        return (CityBuilding) this.getValueWithKey(ActionInputName.BUILDING);
    }

    @Override
    public boolean checkInputByEnum(ActionInputName inputName) {
        if(this.inputFields.containsKey(inputName)) {
            return this.inputFields.get(inputName).hasElement();
        }
        return false;
    }
}
