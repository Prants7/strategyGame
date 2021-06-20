package hedgehogs.strategyGame.gameLogic.land.landFractction;

import hedgehogs.strategyGame.gameLogic.factions.Faction;

public interface LandFraction {

    //public void addLocalOwnerFaction(Faction localOwner);

    public boolean isManaged();

    public void setManagedStatus(boolean newValue);

    public Faction getOwner();

    public LandFraction changeOwner(Faction newOwner);
}
