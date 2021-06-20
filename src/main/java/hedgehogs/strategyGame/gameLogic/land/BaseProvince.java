package hedgehogs.strategyGame.gameLogic.land;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.landFractction.LandFraction;

import java.util.List;
import java.util.Map;

public abstract class BaseProvince implements Province {

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
}
