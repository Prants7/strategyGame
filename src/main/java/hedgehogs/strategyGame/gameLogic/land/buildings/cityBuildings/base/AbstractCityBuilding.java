package hedgehogs.strategyGame.gameLogic.land.buildings.cityBuildings.base;

import hedgehogs.strategyGame.gameLogic.land.Province;

import javax.annotation.PostConstruct;

public abstract class AbstractCityBuilding implements CityBuilding {
    private String name;

    public AbstractCityBuilding() {
        this.bootUp();
    }

    protected void bootUp() {
        this.name = bootGiveName();
    }

    protected abstract String bootGiveName();

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean finished(Province location) {
        this.addFinishStats(location);
        return true;
    }

    protected abstract void addFinishStats(Province location);


}
