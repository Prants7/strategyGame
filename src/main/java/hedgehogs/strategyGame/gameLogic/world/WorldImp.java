package hedgehogs.strategyGame.gameLogic.world;

import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.gameLogic.land.ProvinceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WorldImp extends BaseWorld {
    @Autowired
    private ProvinceFactory provinceFactory;
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
