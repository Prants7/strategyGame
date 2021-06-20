package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionModules.landClearCheckModule;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import org.springframework.stereotype.Component;

@Component
public class LandClearCheckModuleImp implements LandClearCheckModule {

    @Override
    public boolean checkIfAllowedToClearLandInProvince(Faction callerFaction, Province targetProvince, int amount) {
        if(targetProvince.getAmountOfUnsettledLand() < amount) {
            return false;
        }
        return true;
    }
}
