package hedgehogs.strategyGame.gameLogic.land.settlementStats.statChangeListener;

import hedgehogs.strategyGame.gameLogic.land.settlementStats.statBase.SettlementStat;
import hedgehogs.strategyGame.gameLogic.land.settlementStats.statBase.StatName;
import hedgehogs.strategyGame.gameLogic.land.settlementStats.statChangeData.StatChangeData;

public class StatChangeListenerImp implements StatChangeListener {
    private StatChangeReceiver receiver;
    private StatName statToListenFor;

    public StatChangeListenerImp(StatChangeReceiver receiver) {
        this.receiver = receiver;
        this.statToListenFor = receiver.giveStatsToListenerFor();
    }

    @Override
    public void AnnounceStatChange(StatChangeData changedStat) {
        if(changedStat.getTargetStat()== this.statToListenFor) {
            this.receiver.reactToStatChange(changedStat);
        }
    }
}
