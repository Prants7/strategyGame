package hedgehogs.strategyGame.gameLogic.factions;

public abstract class BaseFaction implements Faction {
    protected String name;

    public BaseFaction() {
        this.bootSetName(this.provideFactionName());
    }

    private void bootSetName(String name) {
        this.name = name;
    }

    protected abstract String provideFactionName();

    public String getFactionName() {
        return this.name;
    }

    @Override
    public void depositGoldToTreasury(int goldAmount) {
        this.sendGoldToTreasury(goldAmount);
    }

    protected abstract void sendGoldToTreasury(int goldAmount);

    @Override
    public int getCurrentTreasuryGoldBalance() {
        return this.calculateCurrentTreasuryGoldBalance();
    }

    protected abstract int calculateCurrentTreasuryGoldBalance();

    @Override
    public boolean canWithdrawGoldAmount(int amount) {
        return this.calculateCheckIfCanWithdrawGold(amount);
    }

    protected abstract boolean calculateCheckIfCanWithdrawGold(int amount);

    @Override
    public int withdrawGoldAmount(int amount) {
        return doWithdrawGoldAmount(amount);
    }

    protected abstract int doWithdrawGoldAmount(int amount);
}
