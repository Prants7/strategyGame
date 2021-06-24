package hedgehogs.strategyGame.gameLogic.land;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.infuenceTable.InfluenceTable;
import hedgehogs.strategyGame.gameLogic.land.landFractction.LandFraction;
import hedgehogs.strategyGame.gameLogic.land.prosperity.Prosperity;

import java.util.List;
import java.util.Map;

public interface Province {

    public String getProvinceName();

    public int getAmountOfSettledLand();

    public int getAmountOfUnsettledLand();

    public void printOutFractionOwnerships();

    public LandFraction getFirstFractionNotInHandsOfFaction(Faction targetedFaction);

    public List<LandFraction> getAllLandFractions();

    public void settleAmountOfLand(int amount);

    public Map<Faction, Integer> getFractionOwnershipMap();

    public InfluenceTable getProvinceInfluenceTable();

    public Prosperity accessProsperity();
}
