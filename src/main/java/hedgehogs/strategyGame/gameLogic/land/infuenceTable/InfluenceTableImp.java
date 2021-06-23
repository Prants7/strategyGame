package hedgehogs.strategyGame.gameLogic.land.infuenceTable;

import hedgehogs.strategyGame.gameLogic.factions.Faction;

import java.util.HashMap;
import java.util.Map;

public class InfluenceTableImp extends BaseInfluenceTable {
    private Map<Faction, Integer> influenceMap;

    public InfluenceTableImp() {
        this.influenceMap = new HashMap<>();
    }

    @Override
    protected void addAmountOfInfluenceForAFaction(Faction targetFaction, int amountOfInfluence) {
        int newValue = amountOfInfluence;
        if(influenceMap.containsKey(targetFaction)) {
            newValue += influenceMap.get(targetFaction);
        }
        System.out.println("Here new influence of faction: "+targetFaction.getFactionName()+" is "+newValue);
        influenceMap.put(targetFaction, newValue);
    }

    @Override
    protected int findFactionInfluence(Faction targetFaction) {
        if(influenceMap.containsKey(targetFaction)) {
            return influenceMap.get(targetFaction);
        }
        return 0;
    }

    @Override
    protected boolean checkIfFactionCanWithdrawInfluence(Faction targetFaction, int amountOfInfluence) {
        if(!influenceMap.containsKey(targetFaction)) {
            return false;
        }
        if(influenceMap.get(targetFaction) < amountOfInfluence) {
            return false;
        }
        return true;
    }

    @Override
    protected void performWithdrawOfInfluenceForFaction(Faction targetFaction, int amountOfInfluence) {
        int newValue = this.influenceMap.get(targetFaction) - amountOfInfluence;
        influenceMap.put(targetFaction, newValue);
    }

    @Override
    protected Map<Faction, Integer> makeCopyOfFullInfluenceMap() {
        return new HashMap<>(this.influenceMap);
    }
}
