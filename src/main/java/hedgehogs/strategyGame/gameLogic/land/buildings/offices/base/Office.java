package hedgehogs.strategyGame.gameLogic.land.buildings.offices.base;

import hedgehogs.strategyGame.gameLogic.factions.Faction;

public interface Office {

    public Faction getOwnerFaction();

    public String getBuildingTypeName();
}
