package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landClearAction;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import org.springframework.stereotype.Component;

@Component
public class LandClearModuleImp implements LandClearModule {

    @Override
    public void doClearLandInProvince(Faction callerFaction, Province targetProvince, int clearAmount) {
        targetProvince.settleAmountOfLand(clearAmount);
    }
}
