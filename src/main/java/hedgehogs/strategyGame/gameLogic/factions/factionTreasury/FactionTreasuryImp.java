package hedgehogs.strategyGame.gameLogic.factions.factionTreasury;

public class FactionTreasuryImp implements FactionTreasury {
    private int currentBalance;

    public FactionTreasuryImp() {
        this.currentBalance = 0;
    }

    @Override
    public void depositGold(int amount) {
        this.currentBalance = this.currentBalance + amount;
    }

    @Override
    public int getCurrentBalance() {
        return this.currentBalance;
    }

    @Override
    public boolean canWithdrawAmount(int amount) {
        return amount <= this.currentBalance;
    }

    @Override
    public int withdrawAmount(int amount) {
        if(this.canWithdrawAmount(amount)) {
            this.currentBalance = this.currentBalance - amount;
            return amount;
        }
        return 0;
    }
}
