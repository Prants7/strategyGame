package hedgehogs.strategyGame.gameLogic.land.roads;

import hedgehogs.strategyGame.gameLogic.land.Province;

public interface Road {

    public Province getFirstProvince();

    public Province getSecondProvince();

    public int getDistance();

    public Province getOtherEnd(Province oneSide);
}
