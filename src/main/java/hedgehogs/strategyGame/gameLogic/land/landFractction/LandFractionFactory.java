package hedgehogs.strategyGame.gameLogic.land.landFractction;

import hedgehogs.strategyGame.gameLogic.factions.FactionPhoneBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LandFractionFactory {
    @Autowired
    private FactionPhoneBook factionPhoneBook;

    public LandFraction getNewLandFraction() {
        LandFraction newFraction = new LandFractionImp(this.factionPhoneBook.getDefaultFaction(),false);
        return newFraction;
    }


}
