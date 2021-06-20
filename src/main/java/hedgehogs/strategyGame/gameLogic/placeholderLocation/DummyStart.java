package hedgehogs.strategyGame.gameLogic.placeholderLocation;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.FactionActionInterface;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.factions.FactionPhoneBook;
import hedgehogs.strategyGame.gameLogic.gameTime.TimeCenterSocket;
import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.gameLogic.world.World;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DummyStart {
    @Autowired
    private World world;
    @Autowired
    private FactionPhoneBook factionPhoneBook;
    @Autowired
    private FactionActionInterface factionActionInterface;
    @Autowired
    private TimeCenterSocket timeCenterSocket;

    public void dummyStart() {
        System.out.println("game starts");
        this.world.bootStart();
        this.timeCenterSocket.bootUpCounter();
        System.out.println(this.world.getWorldName());
        this.getWorldProvinces();
        this.performingTrialLandPurchaseForPlayerFaction();
        this.performingTrialLandClearByPlayerFaction();
        this.getWorldProvinces();
        doLandOwnershipIncomeCollection();
        doLandOwnershipIncomeCollection();
    }

    private void getWorldProvinces() {
        System.out.println("Getting all provinces");
        for(Province oneProvince : this.world.getAllProvinces()) {
            System.out.println(oneProvince.getProvinceName());
            System.out.println("Amount of settled land: " + oneProvince.getAmountOfSettledLand());
            oneProvince.printOutFractionOwnerships();
            System.out.println("Amount of unsettled land: " + oneProvince.getAmountOfUnsettledLand());
        }
        System.out.println("---------------");
    }

    private void performingTrialLandPurchaseForPlayerFaction() {
        System.out.println("Performing land purchase for player faction");
        Province targetProvince = this.world.getAllProvinces().get(0);
        Faction playerFaction = this.factionPhoneBook.getPlayerFaction();
        this.factionActionInterface.performLandPurchase(playerFaction, targetProvince);
        this.factionActionInterface.performLandPurchase(playerFaction,targetProvince);
        System.out.println("Finished with land purchase for player faction");
    }

    private void performingTrialLandClearByPlayerFaction() {
        System.out.println("Performing land clear by player faction");
        Province targetProvince = this.world.getAllProvinces().get(0);
        Faction playerFaction = this.factionPhoneBook.getPlayerFaction();
        this.factionActionInterface.performLandClearance(playerFaction, targetProvince, 3);
        System.out.println("Finished with land clear by player faction");
    }

    private void doLandOwnershipIncomeCollection() {
        System.out.println("Doing land ownership income collection");
        this.timeCenterSocket.advanceTime();
        System.out.println(this.timeCenterSocket.getCurrentTime());
    }


}
