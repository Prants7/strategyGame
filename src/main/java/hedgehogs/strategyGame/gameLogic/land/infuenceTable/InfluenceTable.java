package hedgehogs.strategyGame.gameLogic.land.infuenceTable;

import hedgehogs.strategyGame.gameLogic.factions.Faction;

import java.util.Map;

public interface InfluenceTable {

    public void addNewInfluenceCap(int newCap);

    public void addInfluenceForFaction(Faction targetFaction, int amountOfInfluence);

    public int getFactionInfluenceHere(Faction targetFaction);

    public boolean factionCanWithdrawAmountOfInfluence(Faction targetFaction, int amountOfInfluence);

    public int withdrawAmountOfInfluenceForFaction(Faction targetFaction, int amountOfInfluence);

    public Map<Faction, Integer> getFullInfluenceMapHere();
}
