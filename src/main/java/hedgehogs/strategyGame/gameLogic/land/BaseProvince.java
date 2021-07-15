package hedgehogs.strategyGame.gameLogic.land;

import hedgehogs.strategyGame.gameLogic.coordinates.Coordinates;
import hedgehogs.strategyGame.gameLogic.coordinates.SettlementCoordinates;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.buildings.offices.officeLocationArea.CityOfficeLocationImp;
import hedgehogs.strategyGame.gameLogic.land.infuenceTable.InfluenceTable;
import hedgehogs.strategyGame.gameLogic.land.infuenceTable.InfluenceTableImp;
import hedgehogs.strategyGame.gameLogic.land.landFractction.LandFraction;
import hedgehogs.strategyGame.gameLogic.land.prosperity.Prosperity;
import hedgehogs.strategyGame.gameLogic.land.prosperity.ProsperityImp;
import hedgehogs.strategyGame.gameLogic.land.roads.Road;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BaseProvince implements Province {
    private InfluenceTable influenceTable;
    private Prosperity prosperity;
    private CityOfficeLocationImp officeInterface;
    private Coordinates settlementCoordinates;
    private List<Road> connectedRoads;

    public BaseProvince(int xLocation, int yLocation) {
        this.influenceTable = new InfluenceTableImp();
        this.prosperity = new ProsperityImp();
        this.officeInterface = new CityOfficeLocationImp();
        this.settlementCoordinates = new SettlementCoordinates(xLocation, yLocation);
        this.connectedRoads = new ArrayList<>();
    }

    public String getProvinceName() {
        return this.provideProvinceName();
    }

    protected abstract String provideProvinceName();

    public int getAmountOfSettledLand() {
        return this.countAmountOfSettledLand();
    }

    protected abstract int countAmountOfSettledLand();

    public int getAmountOfUnsettledLand() {
        return this.countAmountOfUnsettledLand();
    }

    protected abstract int countAmountOfUnsettledLand();

    public void printOutFractionOwnerships() {
        this.printOutFractionOwnershipInherited();
    }

    protected abstract void printOutFractionOwnershipInherited();

    @Override
    public LandFraction getFirstFractionNotInHandsOfFaction(Faction targetedFaction) {
        return this.findFirstFractionNotInHandsOfFaction(targetedFaction);
    }

    protected abstract LandFraction findFirstFractionNotInHandsOfFaction(Faction targetFaction);

    @Override
    public List<LandFraction> getAllLandFractions() {
        return this.giveCopyListOfLandFractions();
    }

    protected abstract List<LandFraction> giveCopyListOfLandFractions();

    @Override
    public void settleAmountOfLand(int amount) {
        if(this.getAmountOfUnsettledLand() < amount) {
            System.out.println("Error: trying to settle more land then possible");
            return;
        }
        else {
            for(int settleCounter = 0; settleCounter < amount; settleCounter++) {
                this.settleNewFraction();
            }
        }
    }

    protected abstract void settleNewFraction();

    @Override
    public Map<Faction, Integer> getFractionOwnershipMap() {
        return this.provideFractionOwnershipMap();
    }

    protected abstract Map<Faction, Integer> provideFractionOwnershipMap();

    public InfluenceTable getProvinceInfluenceTable() {
        return this.influenceTable;
    }

    @Override
    public Prosperity accessProsperity() {
        return this.prosperity;
    }

    @Override
    public CityOfficeLocationImp accessLocationOffices() {
        return this.officeInterface;
    }

    @Override
    public Coordinates accessCoordinates() {
        return this.settlementCoordinates;
    }

    @Override
    public boolean addRoad(Road newRoad) {
        return this.connectedRoads.add(newRoad);
    }

    @Override
    public boolean hasDirectAccessTo(Province targetProvince) {
        for(Road oneRoad : this.connectedRoads) {
            if(oneRoad.getOtherEnd(this) == targetProvince) {
                return true;
            }
        }
        return false;
    }
}
