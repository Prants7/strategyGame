package hedgehogs.strategyGame.gameLogic.factionActionInterface;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;

public interface FactionActionInterface {

    public void performLandPurchase(Faction callingFaction, Province targetProvince);

    public void performLandClearance(Faction callingFaction, Province targetProvince, int amount);
}
