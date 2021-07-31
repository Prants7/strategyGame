package hedgehogs.strategyGame.gameLogic.land.settlementStats.statChangeData;

import hedgehogs.strategyGame.gameLogic.land.settlementStats.statBase.StatName;

public interface StatChangeData {

    public StatName getTargetStat();

    public int getIncreaseAmount();
}
