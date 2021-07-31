package hedgehogs.strategyGame.gameLogic.land.settlementStats.statChangeData;

public class StatChangeDataImp implements StatChangeData {
    private int increaseValue;

    public StatChangeDataImp(int increaseValue) {
        this.increaseValue = increaseValue;
    }

    @Override
    public int getIncreaseAmount() {
        return this.increaseValue;
    }
}
