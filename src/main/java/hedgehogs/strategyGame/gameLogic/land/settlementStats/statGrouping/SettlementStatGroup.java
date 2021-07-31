package hedgehogs.strategyGame.gameLogic.land.settlementStats.statGrouping;

import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.gameLogic.land.settlementStats.customStats.landAmount.LandAmount;
import hedgehogs.strategyGame.gameLogic.land.settlementStats.statBase.SettlementStat;
import hedgehogs.strategyGame.gameLogic.land.settlementStats.statBase.StatName;
import hedgehogs.strategyGame.gameLogic.land.settlementStats.statChangeData.StatChangeData;
import hedgehogs.strategyGame.gameLogic.land.settlementStats.statChangeListener.StatChangeListener;

import java.util.ArrayList;
import java.util.List;

public class SettlementStatGroup {
    private Province targetProvince;
    private SettlementStat landAmount;
    private List<StatChangeListener> listenerList;

    public SettlementStatGroup(Province targetProvince) {
        this.targetProvince = targetProvince;
        this.makeAllStats();
        this.listenerList = new ArrayList<>();
    }

    private void makeAllStats() {
        this.landAmount = new LandAmount();
    }

    public int getStatValue(StatName statName) {
        if(statName == StatName.LAND_AMOUNT) {
            return this.landAmount.getCurrentValue();
        }
        return 0;
    }

    public boolean sendStatChange(StatChangeData changeData) {
        if(changeData.getTargetStat() == StatName.LAND_AMOUNT) {
            this.landAmount.addStatChange(changeData);
            announceToListeners(changeData);
            return true;
        }
        return false;
    }

    public void addNewListener(StatChangeListener newListener) {
        this.listenerList.add(newListener);
    }

    private void announceToListeners(StatChangeData changeData) {
        for(StatChangeListener oneListener: this.listenerList) {
            oneListener.AnnounceStatChange(changeData);
        }
    }

}
