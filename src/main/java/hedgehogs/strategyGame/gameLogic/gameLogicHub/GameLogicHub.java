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

import javax.annotation.PostConstruct;

@Component
@Data
public class GameLogicHub {
    private World world;
    private FactionPhoneBook factionPhoneBook;
    private FactionActionInterface factionActionInterface;
    private TimeCenterSocket timeCenterSocket;

    @Autowired
    public GameLogicHub(World world,
                        FactionPhoneBook factionPhoneBook,
                        FactionActionInterface factionActionInterface,
                        TimeCenterSocket timeCenterSocket) {
        this.world = world;
        this.factionPhoneBook = factionPhoneBook;
        this.factionActionInterface = factionActionInterface;
        this.timeCenterSocket = timeCenterSocket;
    }

    public void bootUpGame() {
        System.out.println("game starts");
        this.world.bootStart();
        this.timeCenterSocket.bootUpCounter();
        this.setUpStandardStarLocationForPlayer();
        System.out.println("world booted up");
    }

    private void setUpStandardStarLocationForPlayer() {
        Province targetProvince = this.world.getAllProvinces().get(0);
        Faction playerFaction = this.factionPhoneBook.getPlayerFaction();
        this.setUpStartLocationForFaction(playerFaction, targetProvince);
    }

    private void setUpStartLocationForFaction(Faction targetFaction, Province targetProvince) {
        this.factionActionInterface.performAdminFamilyHallBuild(targetFaction, targetProvince);
        this.factionActionInterface.performAdminLandAssign(targetFaction, targetProvince);
        this.factionActionInterface.performAdminLandAssign(targetFaction,targetProvince);
    }

}
