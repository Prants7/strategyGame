package hedgehogs.strategyGame.gameLogic.land.buildings.cityBuildings.base;

import javax.annotation.PostConstruct;

public abstract class AbstractCityBuilding implements CityBuilding {
    private String name;

    @PostConstruct
    protected void bootUp() {
        this.name = bootGiveName();
    }

    protected abstract String bootGiveName();

    @Override
    public String getName() {
        return name;
    }
}