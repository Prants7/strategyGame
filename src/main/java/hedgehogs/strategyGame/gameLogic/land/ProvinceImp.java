package hedgehogs.strategyGame.gameLogic.land;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.landFractction.LandFraction;
import hedgehogs.strategyGame.gameLogic.land.landFractction.LandFractionFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProvinceImp extends BaseProvince {
    private String name;
    private List<LandFraction> landFractions;

    public ProvinceImp(String provinceName, boolean settled, LandFractionFactory fractionFactory,
                       int xLocation, int yLocation) {
        super(xLocation, yLocation);
        this.name = provinceName;
        this.setUpLandFractions(fractionFactory);
        if(settled) {
            this.makeIntoSettledLand();
        }
    }

    /*public void setName(String newName) {
        this.name = newName;
    }*/

    /*public void callBootSettle() {
        this.makeIntoSettledLand();
    }*/

    @Override
    protected String provideProvinceName() {
        return this.name;
    }

    @Override
    protected int countAmountOfSettledLand() {
        return this.streamCountOfSettledLand();
    }

    @Override
    protected int countAmountOfUnsettledLand() {
        return this.landFractions.size() - this.streamCountOfSettledLand();
    }

    @Override
    protected void printOutFractionOwnershipInherited() {
        this.printOutOwnershipMapValues(this.makeMapOfFractionOwnerships());
    }

    @Override
    protected Map<Faction, Integer> provideFractionOwnershipMap() {
        return this.makeMapOfFractionOwnerships();
    }

    private Map<Faction, Integer> makeMapOfFractionOwnerships() {
        Map<Faction, Integer> ownerships = new HashMap<>();
        for(LandFraction oneFraction : this.landFractions) {
            if(!ownerships.containsKey(oneFraction.getOwner())) {
                ownerships.put(oneFraction.getOwner(), 1);
            }
            else {
                int newValue = ownerships.get(oneFraction.getOwner()) + 1;
                ownerships.put(oneFraction.getOwner(), newValue);
            }
        }
        return ownerships;
    }

    private void printOutOwnershipMapValues(Map<Faction, Integer> mapValues) {
        System.out.println("Fraction ownerships:");
        for(Map.Entry<Faction, Integer> oneEntry : mapValues.entrySet()) {
            if(oneEntry.getKey() != null) {
                System.out.println("Faction: "+oneEntry.getKey().getFactionName() + " owns: " + oneEntry.getValue());
            }
        }
        System.out.println("---");
    }

    /*private int slowCountOfSettledLand() {
        int countResult = 0;
        for(LandFraction oneFraction : this.landFractions) {
            if(oneFraction.isManaged()) {
                countResult++;
            }
        }
        return countResult;
    }*/

    private int streamCountOfSettledLand() {
        long countResult = this.landFractions.stream().filter(oneFraction -> oneFraction.isManaged()).count();
        return (int) countResult;
    }

    private void setUpLandFractions(LandFractionFactory factory) {
        this.initLandFractionList();
        this.makeBasicLandFractions(factory);
    }

    private void initLandFractionList() {
        this.landFractions = new ArrayList<>();
    }

    private void makeBasicLandFractions(LandFractionFactory factory) {
        for(int newFractionNr = 0; newFractionNr < 10 ; newFractionNr++) {
            this.landFractions.add(factory.getNewLandFraction());
        }
    }

    private void makeIntoSettledLand() {
        int totalAmountOfLand = this.landFractions.size();
        int half = totalAmountOfLand / 2;
        for(int countedFractionNr = 0; countedFractionNr < half; countedFractionNr++) {
            this.landFractions.get(countedFractionNr).setManagedStatus(true);
        }
    }

    @Override
    protected LandFraction findFirstFractionNotInHandsOfFaction(Faction targetFaction) {
        for(LandFraction oneFraction : this.landFractions) {
            if(oneFraction.isManaged()) {
                if(!oneFraction.getOwner().equals(targetFaction)) {
                    return oneFraction;
                }
            }
        }
        return this.landFractions.get(0);
    }

    @Override
    protected List<LandFraction> giveCopyListOfLandFractions() {
        return new ArrayList<>(this.landFractions);
    }

    @Override
    protected void settleNewFraction() {
        List<LandFraction> unsettledLands = this.getNotSettledFractions();
        unsettledLands.get(0).setManagedStatus(true);
    }

    private List<LandFraction> getNotSettledFractions() {
        List<LandFraction> unsettledLands = this.landFractions.stream()
                .filter(oneFraction -> oneFraction.isManaged() == false).collect(Collectors.toList());
        return unsettledLands;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
