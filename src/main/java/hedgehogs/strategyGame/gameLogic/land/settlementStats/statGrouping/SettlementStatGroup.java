package hedgehogs.strategyGame.gameLogic.land.settlementStats.statGrouping;

import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.gameLogic.land.settlementStats.customStats.landAmount.LandAmount;
import hedgehogs.strategyGame.gameLogic.land.settlementStats.statBase.SettlementStat;

public class SettlementStatGroup {
    private Province targetProvince;
    private SettlementStat landAmount;

    public SettlementStatGroup(Province targetProvince) {
        this.targetProvince = targetProvince;
        this.makeAllStats();
    }

    private void makeAllStats() {
        this.landAmount = new LandAmount();
    }

    public SettlementStat getLandAmount() {
        return landAmount;
    }
}
