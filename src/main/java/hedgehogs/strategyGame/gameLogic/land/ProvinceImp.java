package hedgehogs.strategyGame.gameLogic.land;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.landFractction.LandFraction;
import hedgehogs.strategyGame.gameLogic.land.landFractction.LandFractionFactory;
import hedgehogs.strategyGame.gameLogic.land.settlementStats.statBase.StatName;
import hedgehogs.strategyGame.gameLogic.land.settlementStats.statChangeData.StatChangeData;
import hedgehogs.strategyGame.gameLogic.land.settlementStats.statChangeListener.StatChangeListenerImp;
import hedgehogs.strategyGame.gameLogic.land.settlementStats.statChangeListener.StatChangeReceiver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProvinceImp extends BaseProvince implements StatChangeReceiver {
    private String name;
    private List<LandFraction> landFractions;
    private LandFractionFactory fractionFactory;

    public ProvinceImp(String provinceName, boolean settled, LandFractionFactory fractionFactory,
                       int xLocation, int yLocation) {
        super(xLocation, yLocation);
        this.name = provinceName;
        this.fractionFactory = fractionFactory;
        this.setUpLandFractions(fractionFactory);
        if(settled) {
            this.makeIntoSettledLand();
        }
        this.accessStats().addNewListener(new StatChangeListenerImp(this));
    }

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
        for(int newFractionNr = 0; newFractionNr < this.getAmountDesiredFractions() ; newFractionNr++) {
            this.landFractions.add(factory.getNewLandFraction());
        }
    }

    private int getAmountDesiredFractions() {
        return this.accessStats().getStatValue(StatName.LAND_AMOUNT);
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

    @Override
    public boolean reactToStatChange(StatChangeData changedStat) {
        correctLandFractionAmount();
        return true;
    }

    private void correctLandFractionAmount() {
        if(this.accessStats().getStatValue(StatName.LAND_AMOUNT) > this.landFractions.size()) {
            increaseAmountOfLand();
        }
    }

    private void increaseAmountOfLand() {
        while (this.accessStats().getStatValue(StatName.LAND_AMOUNT) > this.landFractions.size()) {
            this.landFractions.add(this.fractionFactory.getNewLandFraction());
        }
    }

    @Override
    public StatName giveStatsToListenerFor() {
        return StatName.LAND_AMOUNT;
    }
}
