package hedgehogs.strategyGame.gameLogic.land;

import hedgehogs.strategyGame.gameLogic.land.landFractction.LandFractionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProvinceFactory {
    @Autowired
    private LandFractionFactory fractionFactory;

    public Province getBasicProvince(String name, boolean developed, int xLocation, int yLocation) {
        return new ProvinceImp(name, developed, this.fractionFactory, xLocation, yLocation);
    }
}
