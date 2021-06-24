package hedgehogs.strategyGame.gameLogic.factions.FactionFractionTaxPolicy;

public class PlayerTaxPolicy extends FactionFractionTaxPolicyBase {

    @Override
    protected int returnPureIncomePercentage() {
        return 80;
    }
}
