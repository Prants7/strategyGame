package hedgehogs.strategyGame.gameLogic.land.buildings.cityBuildings.base;

import hedgehogs.strategyGame.gameLogic.land.Province;

import javax.annotation.PostConstruct;

public abstract class AbstractCityBuilding implements CityBuilding {
    private String name;
    private int cityBuildLimit;

    public AbstractCityBuilding() {
        this.bootUp();
    }

    protected void bootUp() {
        this.name = bootGiveName();
        this.cityBuildLimit = bootGiveBuildLimit();
    }

    protected abstract String bootGiveName();

    protected abstract int bootGiveBuildLimit();

    private boolean hasLocalBuildLimit() {
        if(this.cityBuildLimit > 0) {
            return true;
        }
        return false;
    }

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

    @Override
    public boolean allowedToBuildInProvince(Province targetLocation) {
        if(!this.passesLocalBuildLimit(targetLocation)) {
            return false;
        }
        return true;
    }

    private boolean passesLocalBuildLimit(Province targetLocation) {
        if(!this.hasLocalBuildLimit()) {
            return true;
        }
        if(targetLocation.accessBuildings().countBuilding(this) < this.cityBuildLimit) {
            return true;
        }
        return false;
    }
}
