package hedgehogs.strategyGame.gameLogic.land;

import hedgehogs.strategyGame.gameLogic.factions.FactionPhoneBook;
import hedgehogs.strategyGame.gameLogic.land.buildings.citySlotTable.CitySlotTable;
import hedgehogs.strategyGame.gameLogic.land.landFractction.LandFractionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProvinceFactory {
    @Autowired
    private LandFractionFactory fractionFactory;
    @Autowired
    private CitySlotTable citySlotTable;
    @Autowired
    private FactionPhoneBook factionPhoneBook;

    public Province getBasicProvince(String name, boolean developed, int xLocation, int yLocation) {
        return new ProvinceImp(name, developed, this.fractionFactory, xLocation, yLocation, citySlotTable, factionPhoneBook);
    }
}
