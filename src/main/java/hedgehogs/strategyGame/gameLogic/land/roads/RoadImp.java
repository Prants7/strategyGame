package hedgehogs.strategyGame.gameLogic.land.roads;

import hedgehogs.strategyGame.gameLogic.land.Province;

public class RoadImp implements Road {
    private Province firstProvince;
    private Province secondProvince;
    private int distance;

    public RoadImp(Province firstProvince, Province secondProvince) {
        this.firstProvince = firstProvince;
        this.secondProvince = secondProvince;
        this.distance = this.getDistanceBetweenSavedProvinces();
        firstProvince.addRoad(this);
        secondProvince.addRoad(this);
    }

    private int getDistanceBetweenSavedProvinces() {
        return this.firstProvince.accessCoordinates().getDistanceFrom(this.secondProvince.accessCoordinates());
    }

    @Override
    public Province getFirstProvince() {
        return this.firstProvince;
    }

    @Override
    public Province getSecondProvince() {
        return this.secondProvince;
    }

    @Override
    public int getDistance() {
        return this.distance;
    }

    @Override
    public Province getOtherEnd(Province oneSide) {
        if(oneSide == firstProvince) {
            return this.secondProvince;
        }
        if(oneSide == secondProvince) {
            return this.firstProvince;
        }
        return null;
    }
}
