package hedgehogs.strategyGame.gameLogic.land.settlementStats.customStats.landAmount;

import hedgehogs.strategyGame.gameLogic.land.settlementStats.statBase.AbstractSettlementStat;
import hedgehogs.strategyGame.gameLogic.land.settlementStats.statBase.StatName;

public class LandAmount extends AbstractSettlementStat {

    public LandAmount() {
        super();
    }

    @Override
    protected StatName bootGiveName() {
        return StatName.LAND_AMOUNT;
    }

    @Override
    protected int bootGiveDefaultValue() {
        return 10;
    }
}
