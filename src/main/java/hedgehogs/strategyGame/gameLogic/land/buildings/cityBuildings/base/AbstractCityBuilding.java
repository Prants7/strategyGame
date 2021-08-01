package hedgehogs.strategyGame.gameLogic.land.buildings.cityBuildings.base;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionActionCost.FactionActionCost;
import hedgehogs.strategyGame.gameLogic.land.Province;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCityBuilding implements CityBuilding {
    private String name;
    private int cityBuildLimit;
    private List<FactionActionCost> costList;

    public AbstractCityBuilding() {
        this.bootUp();
    }

    protected void bootUp() {
        this.name = bootGiveName();
        this.cityBuildLimit = bootGiveBuildLimit();
        this.costList = new ArrayList<>();
        this.bootAddCosts(this.costList);
    }

    protected abstract String bootGiveName();

    protected abstract int bootGiveBuildLimit();

    protected abstract void bootAddCosts(List<FactionActionCost> addLocation);

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

    @Override
    public List<FactionActionCost> getBuildingSpecificCosts() {
        return new ArrayList<>(this.costList);
    }
}
