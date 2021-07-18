package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput;

import hedgehogs.strategyGame.gameLogic.agents.base.Agent;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.gameLogic.land.buildings.cityBuildings.base.CityBuilding;

public interface FactionActionInput {

    public FactionActionInput setAgent(Agent agent);

    public boolean hasAgent();

    public Agent getAgent();

    public FactionActionInput setOtherLocation(Province otherLocation);

    public boolean hasOtherLocation();

    public Province getOtherLocation();

    public FactionActionInput setCityBuilding(CityBuilding cityBuilding);

    public boolean hasCityBuilding();

    public CityBuilding getCityBuilding();
}
