package hedgehogs.strategyGame.gameLogic.land.settlementStats.statBase;

import hedgehogs.strategyGame.gameLogic.land.settlementStats.statChangeData.StatChangeData;

public interface SettlementStat {

    public StatName getStatName();

    public int getCurrentValue();

    public boolean addStatChange(StatChangeData changeData);
}
