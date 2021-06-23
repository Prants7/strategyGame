package hedgehogs.strategyGame.gameLogic.land.infuenceTable;

import hedgehogs.strategyGame.gameLogic.factions.Faction;

import java.util.Map;

public abstract class BaseInfluenceTable implements InfluenceTable {
    protected int influenceCap;

    public BaseInfluenceTable() {
        this.influenceCap = 100;
    }

    @Override
    public void addNewInfluenceCap(int newCap) {
        this.influenceCap = newCap;
    }

    @Override
    public void addInfluenceForFaction(Faction targetFaction, int amountOfInfluence) {
        int currentInfluence = this.getFactionInfluenceHere(targetFaction);
        if(currentInfluence + amountOfInfluence < this.influenceCap) {
            this.addAmountOfInfluenceForAFaction(targetFaction, amountOfInfluence);
        }
        else {
            this.addAmountOfInfluenceForAFaction(targetFaction, influenceCap - currentInfluence);
        }
    }

    protected abstract void addAmountOfInfluenceForAFaction(Faction targetFaction, int amountOfInfluence);

    @Override
    public int getFactionInfluenceHere(Faction targetFaction) {
        return findFactionInfluence(targetFaction);
    }

    protected abstract int findFactionInfluence(Faction targetFaction);

    @Override
    public boolean factionCanWithdrawAmountOfInfluence(Faction targetFaction, int amountOfInfluence) {
        return this.checkIfFactionCanWithdrawInfluence(targetFaction, amountOfInfluence);
    }

    protected abstract boolean checkIfFactionCanWithdrawInfluence(Faction targetFaction, int amountOfInfluence);

    @Override
    public int withdrawAmountOfInfluenceForFaction(Faction targetFaction, int amountOfInfluence) {
        if(!this.factionCanWithdrawAmountOfInfluence(targetFaction, amountOfInfluence)) {
            return 0;
        }
        this.performWithdrawOfInfluenceForFaction(targetFaction, amountOfInfluence);
        return amountOfInfluence;
    }

    protected abstract void performWithdrawOfInfluenceForFaction(Faction targetFaction, int amountOfInfluence);

    @Override
    public Map<Faction, Integer> getFullInfluenceMapHere() {
        return this.makeCopyOfFullInfluenceMap();
    }

    protected abstract Map<Faction, Integer> makeCopyOfFullInfluenceMap();
}
