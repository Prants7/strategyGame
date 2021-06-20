package hedgehogs.strategyGame.gameLogic.factions;

public class LocalOwner extends BaseFaction {

    @Override
    protected String provideFactionName() {
        return "Locals";
    }

    @Override
    protected void sendGoldToTreasury(int goldAmount) {
        return;
    }

    @Override
    protected int calculateCurrentTreasuryGoldBalance() {
        return 0;
    }

    @Override
    protected boolean calculateCheckIfCanWithdrawGold(int amount) {
        return false;
    }

    @Override
    protected int doWithdrawGoldAmount(int amount) {
        return 0;
    }
}
