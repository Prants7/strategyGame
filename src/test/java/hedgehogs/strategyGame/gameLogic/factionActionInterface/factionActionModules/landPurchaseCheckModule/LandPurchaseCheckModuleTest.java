package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionModules.landPurchaseCheckModule;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.factions.FactionPhoneBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LandPurchaseCheckModuleTest {
    @Autowired
    private LandPurchaseCheckModule testableModule;
    @Autowired
    private FactionPhoneBook factionPhoneBook;

    private Faction getTestablePlayerFaction() {
        return this.factionPhoneBook.getPlayerFaction();
    }
}