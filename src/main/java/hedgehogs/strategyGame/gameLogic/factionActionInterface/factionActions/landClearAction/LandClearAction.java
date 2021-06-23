package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landClearAction;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LandClearAction {
    @Autowired
    private LandClearCheck landClearCheck;
    @Autowired
    private LandClearModule landClearModule;

    public boolean allowedToDoAction(Faction callerFaction, Province location, int amount) {
        return this.landClearCheck.allowedToDoAction(callerFaction, location, amount);
    }

    public void doAction(Faction callerFaction, Province location, int amount) {
        if(this.allowedToDoAction(callerFaction, location, amount)) {
            this.landClearModule.doClearLandInProvince(callerFaction, location, amount);
        }
    }
}
