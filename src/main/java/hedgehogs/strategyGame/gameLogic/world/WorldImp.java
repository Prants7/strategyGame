package hedgehogs.strategyGame.gameLogic.world;

import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.gameLogic.land.ProvinceFactory;
import hedgehogs.strategyGame.gameLogic.land.roads.RoadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WorldImp extends BaseWorld {
    @Autowired
    private ProvinceFactory provinceFactory;
    @Autowired
    private RoadFactory roadFactory;
    private List<Province> provinces;

    public WorldImp() {
    }

    @Override
    protected void bootCommands() {
        this.setUpWorldProvinces();
    }

    private void setUpWorldProvinces() {
        this.initProvinceList();
        this.makeFirstTimeProvinces();
    }

    private void initProvinceList() {
        this.provinces = new ArrayList<>();
    }

    private void makeFirstTimeProvinces() {
        this.provinces.add( this.provinceFactory.getBasicProvince("firstProvince", true, 5, 50));
        this.provinces.add( this.provinceFactory.getBasicProvince("secondProvince", false, 24, 270));
        this.provinces.add( this.provinceFactory.getBasicProvince("thirdProvince", false, 175, 100));
        this.makeRoadsForFirstTimeProvinces();
    }

    private void makeRoadsForFirstTimeProvinces() {
        this.roadFactory.makeNewRoadBetweenProvinces(this.provinces.get(0), this.provinces.get(1));
        //this.roadFactory.makeNewRoadBetweenProvinces(this.provinces.get(0), this.provinces.get(2));
        this.roadFactory.makeNewRoadBetweenProvinces(this.provinces.get(2), this.provinces.get(1));
    }

    @Override
    protected List<Province> provideAllProvinces() {
        return this.provinces;
    }

    @Override
    protected String provideWorldName() {
        return "WorldImp type of world";
    }

}
