package hedgehogs.strategyGame.gameLogic.factionReousrceInterface;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;

public interface FactionResourceInterface {

    public boolean addResourceToFaction(ResourceSettings settings);

    public boolean removeResourceFromFaction(ResourceSettings settings);

    public boolean canRemoveResourcesFromFaction(ResourceSettings settings);

    public boolean addLandFractionIncomeToFaction(ResourceSettings settings);
}
