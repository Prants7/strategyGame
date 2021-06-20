package hedgehogs.strategyGame.gameLogic.land.landFractction;

import hedgehogs.strategyGame.gameLogic.factions.Faction;

public abstract class BaseLandFraction implements LandFraction {
    protected Faction localFactionRef;
    protected Faction owner;

    public BaseLandFraction(Faction localOwnerFactionRef) {
        this.localFactionRef = localOwnerFactionRef;
    }

    public boolean isManaged() {
        return this.giveManagedValue();
    }

    protected abstract boolean giveManagedValue();

    public void setManagedStatus(boolean newValue) {
        if(newValue) {
            this.turnIntoManagedLand();
        }
        else {
            this.turnIntoUnmanagedLand();
        }
    }

    protected abstract void turnIntoManagedLand();

    protected abstract void turnIntoUnmanagedLand();

    /*public void addLocalOwnerFaction(Faction localOwner) {
        this.localFactionRef = localOwner;
    }*/

    public Faction getOwner() {
        return this.owner;
    }

    public LandFraction changeOwner(Faction newOwner) {
        this.performOwnerChange(newOwner);
        return this;
    }

    protected abstract void performOwnerChange(Faction newOwner);

}
