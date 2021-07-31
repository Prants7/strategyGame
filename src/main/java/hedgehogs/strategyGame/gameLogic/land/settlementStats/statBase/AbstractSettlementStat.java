package hedgehogs.strategyGame.gameLogic.land.settlementStats.statBase;

import hedgehogs.strategyGame.gameLogic.land.settlementStats.statChangeData.StatChangeData;

public abstract class AbstractSettlementStat implements SettlementStat {
    private StatName name;
    private int currentValue;

    public AbstractSettlementStat() {
        this.name = this.bootGiveName();
        this.currentValue = this.bootGiveDefaultValue();
    }

    @Override
    public StatName getStatName() {
        return this.name;
    }

    protected abstract StatName bootGiveName();

    @Override
    public int getCurrentValue() {
        return this.currentValue;
    }

    protected abstract int bootGiveDefaultValue();

    @Override
    public boolean addStatChange(StatChangeData changeData) {
        this.placeholderIncrease(changeData.getIncreaseAmount());
        return true;
    }

    private void placeholderIncrease(int increaseValue) {
        this.currentValue += increaseValue;
    }
}
