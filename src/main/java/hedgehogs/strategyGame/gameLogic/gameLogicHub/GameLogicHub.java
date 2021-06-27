package hedgehogs.strategyGame.gameLogic.gameLogicHub;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.FactionActionInterface;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.factions.FactionPhoneBook;
import hedgehogs.strategyGame.gameLogic.gameTime.TimeCenterSocket;
import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.gameLogic.world.World;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
public class GameLogicHub {
    @Autowired
    private World world;
    @Autowired
    private FactionPhoneBook factionPhoneBook;
    @Autowired
    private FactionActionInterface factionActionInterface;
    @Autowired
    private TimeCenterSocket timeCenterSocket;

    public void bootUpGame() {
        System.out.println("game starts");
        this.world.bootStart();
        this.timeCenterSocket.bootUpCounter();
        this.givePlayerSomeLand();
        System.out.println("world booted up");
    }

    private void givePlayerSomeLand() {
        System.out.println("Performing land purchase for player faction");
        Province targetProvince = this.world.getAllProvinces().get(0);
        Faction playerFaction = this.factionPhoneBook.getPlayerFaction();
        this.factionActionInterface.performAdminLandAssign(playerFaction, targetProvince);
        this.factionActionInterface.performAdminLandAssign(playerFaction,targetProvince);
        System.out.println("Finished with land purchase for player faction");
        this.givePlayerOfficeInStartLocation();
    }

    private void givePlayerOfficeInStartLocation() {
        Province targetProvince = this.world.getAllProvinces().get(0);
        Faction playerFaction = this.factionPhoneBook.getPlayerFaction();
        this.factionActionInterface.performFamilyHallBuild(playerFaction, targetProvince);
    }
}
