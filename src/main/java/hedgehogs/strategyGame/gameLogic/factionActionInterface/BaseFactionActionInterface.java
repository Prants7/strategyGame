package hedgehogs.strategyGame.gameLogic.factionActionInterface;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;

public abstract class BaseFactionActionInterface implements FactionActionInterface {

    @Override
    public void performAdminLandAssign(Faction forFaction, Province targetProvince) {
        if(!this.checkIfAllowedToAssignLand(forFaction, targetProvince)) {
            return;
        }
        this.doLandAssignForFaction(forFaction, targetProvince);
    }

    protected abstract boolean checkIfAllowedToAssignLand(Faction targetFaction, Province targetProvince);

    protected abstract void doLandAssignForFaction(Faction targetFaction, Province targetProvince);

    @Override
    public void performLandPurchase(Faction callingFaction, Province targetProvince) {
        if(!this.checkIfAllowedToPurchaseLand(callingFaction, targetProvince)){
            return;
        }
        this.doLandPurchaseForFaction(callingFaction, targetProvince);
    }

    protected abstract boolean checkIfAllowedToPurchaseLand(Faction targetFaction, Province targetProvince);

    protected abstract void doLandPurchaseForFaction(Faction targetFaction, Province targetProvince);

    @Override
    public void performLandClearance(Faction callingFaction, Province targetProvince, int amount) {
        if(!this.checkIfAllowedToClearLand(callingFaction, targetProvince, amount)) {
            return;
        }
        this.doClearLand(callingFaction, targetProvince, amount);

    }

    protected abstract boolean checkIfAllowedToClearLand(Faction callingFaction, Province targetProvince, int amount);

    protected abstract void doClearLand(Faction callingFaction, Province targetProvince, int amount);
}
