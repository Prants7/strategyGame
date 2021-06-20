package hedgehogs.strategyGame.gameLogic.factions;

import hedgehogs.strategyGame.gameLogic.factions.factionTreasury.FactionTreasury;
import hedgehogs.strategyGame.gameLogic.factions.factionTreasury.FactionTreasuryImp;

public abstract class OrganisedFaction extends BaseFaction {
    private FactionTreasury factionTreasury;

    public OrganisedFaction() {
        this.factionTreasury = this.bootMakeFactionTreasury();
    }

    private FactionTreasury bootMakeFactionTreasury() {
        return new FactionTreasuryImp();
    }

    @Override
    protected void sendGoldToTreasury(int goldAmount) {
        this.factionTreasury.depositGold(goldAmount);
    }

    @Override
    protected int calculateCurrentTreasuryGoldBalance() {
        return this.factionTreasury.getCurrentBalance();
    }

    @Override
    protected boolean calculateCheckIfCanWithdrawGold(int amount) {
        return this.factionTreasury.canWithdrawAmount(amount);
    }

    @Override
    protected int doWithdrawGoldAmount(int amount) {
        return this.factionTreasury.withdrawAmount(amount);
    }
}
