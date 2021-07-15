package hedgehogs.strategyGame.gameLogic.gameLogicHub;

import hedgehogs.strategyGame.gameLogic.agents.agentFactory.AgentFactory;
import hedgehogs.strategyGame.gameLogic.agents.agentPhoneBook.AgentPhoneBook;
import hedgehogs.strategyGame.gameLogic.agents.base.Agent;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.FactionActionInterface;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.factions.FactionPhoneBook;
import hedgehogs.strategyGame.gameLogic.gameTime.TimeCenterSocket;
import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.gameLogic.land.roads.Road;
import hedgehogs.strategyGame.gameLogic.land.roads.RoadFactory;
import hedgehogs.strategyGame.gameLogic.world.World;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@Data
public class GameLogicHub {
    private World world;
    private FactionPhoneBook factionPhoneBook;
    private FactionActionInterface factionActionInterface;
    private TimeCenterSocket timeCenterSocket;
    private AgentFactory agentFactory;
    private AgentPhoneBook agentPhoneBook;
    private RoadFactory roadFactory;

    @Autowired
    public GameLogicHub(World world,
                        FactionPhoneBook factionPhoneBook,
                        FactionActionInterface factionActionInterface,
                        TimeCenterSocket timeCenterSocket,
                        AgentFactory agentFactory,
                        AgentPhoneBook agentPhoneBook,
                        RoadFactory roadFactory) {
        this.world = world;
        this.factionPhoneBook = factionPhoneBook;
        this.factionActionInterface = factionActionInterface;
        this.timeCenterSocket = timeCenterSocket;
        this.agentFactory = agentFactory;
        this.agentPhoneBook = agentPhoneBook;
        this.roadFactory = roadFactory;
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
        //printOutFactionAgents(playerFaction);
        this.moveAgentElseWhere(this.agentPhoneBook.getFactionAgents(playerFaction).get(1),
                this.world.getAllProvinces().get(1));
    }

    private void setUpStartLocationForFaction(Faction targetFaction, Province targetProvince) {
        this.factionActionInterface.performAdminFamilyHallBuild(targetFaction, targetProvince);
        this.factionActionInterface.performAdminLandAssign(targetFaction, targetProvince);
        this.factionActionInterface.performAdminLandAssign(targetFaction,targetProvince);
        this.setUpStartAgentsForFaction(targetFaction, targetProvince);
    }

    private void setUpStartAgentsForFaction(Faction targetFaction, Province targetProvince) {
        this.agentFactory.makeNewEnvoyForFaction("Billy", targetFaction, targetProvince);
        this.agentFactory.makeNewEnvoyForFaction("Jimmy", targetFaction, targetProvince);
        this.agentFactory.makeNewEnvoyForFaction("Michael", targetFaction, targetProvince);
    }

    private void printOutFactionAgents(Faction targetFaction) {
        System.out.println("Printing out agents for faction: "+targetFaction.getFactionName());
        for(Agent oneAgent: this.agentPhoneBook.getFactionAgents(targetFaction)) {
            System.out.println(oneAgent.getRole()+": "+oneAgent.getName());
        }
        System.out.println("-----------------");
    }

    private void moveAgentElseWhere(Agent targetAgent, Province newLocation) {
        System.out.println("moving " + targetAgent.getName()+ " from: "+targetAgent.getLocation().getProvinceName()
                + " to "+ newLocation.getProvinceName());
        targetAgent.moveAgent(newLocation);
    }

    public List<Road> getAllRoads() {
        return this.roadFactory.getAllRoads();
    }

}
