package hedgehogs.strategyGame.gameLogic.factions.FactionFractionTaxPolicy;

public abstract class FactionFractionTaxPolicyBase implements FactionFractionTaxPolicy {

    @Override
    public int returnValueForLocalProsperity(int totalAmount) {
        return totalAmount - this.returnValueForPureIncome(totalAmount);
    }

    @Override
    public int returnValueForPureIncome(int totalAmount) {
        int partAmount = totalAmount * this.returnPureIncomePercentage();
        int finalCalcAmount = partAmount / 100;
        if(finalCalcAmount == totalAmount) {
            finalCalcAmount =- 1;
        }
        System.out.println("Pure income is "+finalCalcAmount+" out of "+totalAmount+ " with percentage: "+this.returnPureIncomePercentage());
        return finalCalcAmount;
    }

    protected abstract int returnPureIncomePercentage();
}
