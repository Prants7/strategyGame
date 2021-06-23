package hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionModules.landPurchaseCheckModule;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.factions.FactionPhoneBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LandPurchaseCheckModuleTest {
    @Autowired
    private FactionPhoneBook factionPhoneBook;

    private Faction getTestablePlayerFaction() {
        return this.factionPhoneBook.getPlayerFaction();
    }

    /*EXAMPLE OF TEST CLASS
    @SpringBootTest
class LandPurchaseModuleTest {
    @Autowired
    private LandPurchaseModule landPurchaseModule;
    @Autowired
    private FactionPhoneBook factionPhoneBook;
    @Autowired
    private ProvinceFactory provinceFactory;

    private Faction getPlayerFaction() {
        return this.factionPhoneBook.getPlayerFaction();
    }

    private Province getTestProvince() {
        return this.provinceFactory.getBasicProvince("testProvince", false);
    }

    private Province getDevelopedTestProvince() {
        return this.provinceFactory.getBasicProvince("testDevelopedProvince", true);
    }

    @Test
    public void testIfCanPurchaseLand() {
        Province testProvince = this.getDevelopedTestProvince();
        Faction testFaction = this.getPlayerFaction();
        assertFalse(testProvince.getAllLandFractions().get(0).getOwner().equals(testFaction));
        landPurchaseModule.doLandPurchase(testFaction, testProvince);
        assertTrue(testProvince.getAllLandFractions().get(0).getOwner().equals(testFaction));
    }

    @Test
    public void testDoesnNotPurchaseUnsettledLand() {
        Province testProvince = this.getTestProvince();
        Faction testFaction = this.getPlayerFaction();
        assertFalse(testProvince.getAllLandFractions().get(0).isManaged());
        landPurchaseModule.doLandPurchase(testFaction, testProvince);
        assertTrue(testProvince.getAllLandFractions().get(0).getOwner() == null);
    }

}*/
}