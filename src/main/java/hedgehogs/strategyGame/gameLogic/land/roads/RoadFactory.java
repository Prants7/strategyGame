package hedgehogs.strategyGame.gameLogic.land.roads;

import hedgehogs.strategyGame.gameLogic.land.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class RoadFactory {
    private List<Road> allRoads;

    @PostConstruct
    public void bootUp() {
        this.allRoads = new ArrayList<>();
    }

    public List<Road> getAllRoads() {
        return new ArrayList<>(this.allRoads);
    }

    public boolean makeNewRoadBetweenProvinces(Province firstProvince, Province secondProvince) {
        if(!this.checkIfAllowedToMakeNewRoad(firstProvince, secondProvince)) {
            System.out.println("ERROR: Not allowed to make road between "+firstProvince.getProvinceName() +
                    " and "+secondProvince.getProvinceName());
            return false;
        }
        Road newRoad = new RoadImp(firstProvince, secondProvince);
        this.allRoads.add(newRoad);
        return true;
    }

    private boolean checkIfAllowedToMakeNewRoad(Province firstProvince, Province secondProvince) {
        if(firstProvince.hasDirectAccessTo(secondProvince)) {
            return false;
        }
        return true;
    }
}
