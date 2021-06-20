package hedgehogs.strategyGame.gameLogic.world;

import hedgehogs.strategyGame.gameLogic.land.Province;

import java.util.List;

public interface World {

    public List<Province> getAllProvinces();

    public String getWorldName();

    public void bootStart();
}
