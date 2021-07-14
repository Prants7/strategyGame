package hedgehogs.strategyGame.gameLogic.factionActionInterface;

import hedgehogs.strategyGame.gameLogic.agents.base.Agent;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;

public interface FactionActionInterface {

    public void performLandPurchase(Agent agent);

    public void performAdminLandAssign(Faction forFaction, Province targetProvince);

    public void performLandClearance(Agent agent);

    public void performFamilyHallBuild(Agent agent);

    public void performAdminFamilyHallBuild(Faction callingFaction, Province targetProvince);

    public void seizeControlInCity(Agent agent);
}
