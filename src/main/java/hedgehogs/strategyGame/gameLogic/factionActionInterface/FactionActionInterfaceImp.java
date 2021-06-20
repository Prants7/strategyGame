package hedgehogs.strategyGame.gameLogic.factionActionInterface;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionModules.landClearCheckModule.LandClearCheckModule;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionModules.landClearModule.LandClearModule;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionModules.landPurchaseCheckModule.LandPurchaseCheckModule;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionModules.landPurchaseModule.LandPurchaseModule;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FactionActionInterfaceImp extends BaseFactionActionInterface {
    @Autowired
    private LandPurchaseCheckModule landPurchaseCheckModule;
    @Autowired
    private LandPurchaseModule landPurchaseModule;
    @Autowired
    private LandClearCheckModule landClearCheckModule;
    @Autowired
    private LandClearModule landClearModule;

    @Override
    protected boolean checkIfAllowedToPurchaseLand(Faction targetFaction, Province targetProvince) {
        return this.landPurchaseCheckModule.allowedToPerformLandPurchaseForFaction(targetFaction, targetProvince);
    }

    @Override
    protected void doLandPurchaseForFaction(Faction targetFaction, Province targetProvince) {
        this.landPurchaseModule.doLandPurchase(targetFaction, targetProvince);
    }

    @Override
    protected boolean checkIfAllowedToClearLand(Faction callingFaction, Province targetProvince, int amount) {
        return this.landClearCheckModule.checkIfAllowedToClearLandInProvince(callingFaction, targetProvince, amount);
    }

    @Override
    protected void doClearLand(Faction callingFaction, Province targetProvince, int amount) {
        this.landClearModule.doClearLandInProvince(callingFaction, targetProvince, amount);

    }


}
