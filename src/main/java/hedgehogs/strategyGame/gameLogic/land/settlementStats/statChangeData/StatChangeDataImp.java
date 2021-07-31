package hedgehogs.strategyGame.gameLogic.land.settlementStats.statChangeData;

import hedgehogs.strategyGame.gameLogic.land.settlementStats.statBase.StatName;

public class StatChangeDataImp implements StatChangeData {
    private StatName targetStat;
    private int increaseValue;

    public StatChangeDataImp(StatName targetStat, int increaseValue) {
        this.targetStat  = targetStat;
        this.increaseValue = increaseValue;
    }

    @Override
    public StatName getTargetStat() {
        return this.targetStat;
    }

    @Override
    public int getIncreaseAmount() {
        return this.increaseValue;
    }
}
