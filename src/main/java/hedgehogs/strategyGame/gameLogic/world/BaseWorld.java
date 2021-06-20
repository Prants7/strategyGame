package hedgehogs.strategyGame.gameLogic.world;

import hedgehogs.strategyGame.gameLogic.land.Province;

import java.util.List;

public abstract class BaseWorld implements World {

    public List<Province> getAllProvinces() {
        return this.provideAllProvinces();
    }

    protected abstract List<Province> provideAllProvinces();

    public String getWorldName() {
        return this.provideWorldName();
    }

    protected abstract String provideWorldName();

    public void bootStart() {
        this.bootCommands();
    }

    protected abstract void bootCommands();
}
