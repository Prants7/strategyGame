package hedgehogs.strategyGame.gameLogic.land.buildings.offices.base;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.buildings.officeBuildingSlot.OfficeBuildingManager;

public interface Office {

    public Faction getOwnerFaction();

    public String getBuildingTypeName();

    public OfficeBuildingManager accessOfficeBuildingManager();
}
