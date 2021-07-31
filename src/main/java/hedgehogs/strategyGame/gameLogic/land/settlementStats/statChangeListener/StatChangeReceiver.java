package hedgehogs.strategyGame.gameLogic.land.settlementStats.statChangeListener;

import hedgehogs.strategyGame.gameLogic.land.settlementStats.statBase.SettlementStat;
import hedgehogs.strategyGame.gameLogic.land.settlementStats.statBase.StatName;
import hedgehogs.strategyGame.gameLogic.land.settlementStats.statChangeData.StatChangeData;

public interface StatChangeReceiver {

    public boolean reactToStatChange(StatChangeData changedStat);

    public StatName giveStatsToListenerFor();
}
