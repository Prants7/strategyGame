package hedgehogs.strategyGame.gameLogic.factions;

public interface Faction {

    public String getFactionName();

    public void depositGoldToTreasury(int goldAmount);

    public int getCurrentTreasuryGoldBalance();

    public boolean canWithdrawGoldAmount(int amount);

    public int withdrawGoldAmount(int amount);
}
