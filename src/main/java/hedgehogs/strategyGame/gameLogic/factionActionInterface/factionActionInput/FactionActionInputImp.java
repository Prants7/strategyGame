package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput;

import hedgehogs.strategyGame.gameLogic.agents.base.Agent;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.inputSockets.AgentSocket;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.inputSockets.BuildingSocket;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.inputSockets.InputSocket;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.inputSockets.LocationSocket;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.gameLogic.land.buildings.cityBuildings.base.CityBuilding;

public class FactionActionInputImp implements FactionActionInput {
    //private Agent agent;
    private AgentSocket agentSocket;
    //private Province otherLocation;
    private LocationSocket locationSocket;
    //private CityBuilding cityBuilding;
    private BuildingSocket buildingSocket;

    public FactionActionInputImp() {
        this.agentSocket = new AgentSocket();
        this.locationSocket = new LocationSocket();
        this.buildingSocket = new BuildingSocket();
    }

    @Override
    public FactionActionInput setAgent(Agent agent) {
        //this.agent = agent;
        this.agentSocket.setElement(agent);
        return this;
    }

    @Override
    public boolean hasAgent() {
        return this.agentSocket.hasElement();
    }

    @Override
    public Agent getAgent() {
        return this.agentSocket.getElement();
    }

    @Override
    public FactionActionInput setOtherLocation(Province otherLocation) {
        //this.otherLocation = otherLocation;
        this.locationSocket.setElement(otherLocation);
        return this;
    }

    @Override
    public boolean hasOtherLocation() {
        return this.locationSocket.hasElement();
    }

    @Override
    public Province getOtherLocation() {
        return this.locationSocket.getElement();
    }

    @Override
    public FactionActionInput setCityBuilding(CityBuilding cityBuilding) {
        //this.cityBuilding = cityBuilding;
        this.buildingSocket.setElement(cityBuilding);
        return this;
    }

    @Override
    public boolean hasCityBuilding() {
        return this.buildingSocket.hasElement();
    }

    @Override
    public CityBuilding getCityBuilding() {
        return this.buildingSocket.getElement();
    }

    @Override
    public InputSocket<?> getInputValueByEnum(ActionInputName inputName) {
        if(inputName == ActionInputName.AGENT) {
            return this.agentSocket;
        }
        if(inputName == ActionInputName.NEXT_LOCATION) {
            return this.locationSocket;
        }
        if(inputName == ActionInputName.BUILDING) {
            return this.buildingSocket;
        }
        return null;
    }
}
