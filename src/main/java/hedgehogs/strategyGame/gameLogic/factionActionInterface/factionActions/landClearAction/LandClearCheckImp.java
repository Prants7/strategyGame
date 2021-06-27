package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landClearAction;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import org.springframework.stereotype.Component;

@Component
public class LandClearCheckImp implements LandClearCheck {

    /*@Override
    public boolean checkIfAllowedToClearLandInProvince(Faction callerFaction, Province targetProvince, int amount) {
        if(targetProvince.getAmountOfUnsettledLand() < amount) {
            return false;
        }
        return true;
    }*/

    /*@Override
    public boolean allowedToDoAction(FactionActionModifiers modifiersContainer) {
        if(modifiersContainer.getTargetLocation().getAmountOfUnsettledLand() < modifiersContainer.getAmount()) {
            return false;
        }
        return true;
    }*/

    @Override
    public boolean allowedToDoAction(Faction callerFaction, Province location, int amount) {
        if(location.getAmountOfUnsettledLand() < amount) {
            return false;
        }
        if(!location.accessLocationOffices().factionHasOffice(callerFaction)) {
            return false;
        }
        return true;
    }
}
