package hedgehogs.strategyGame.gameLogic.factions.factionTreasury;

public interface FactionTreasury {

    public void depositGold(int amount);

    public int getCurrentBalance();

    public boolean canWithdrawAmount(int amount);

    public int withdrawAmount(int amount);
}
