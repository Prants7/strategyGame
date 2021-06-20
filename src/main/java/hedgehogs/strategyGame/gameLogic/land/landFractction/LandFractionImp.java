package hedgehogs.strategyGame.gameLogic.land.landFractction;

import hedgehogs.strategyGame.gameLogic.factions.Faction;

public class LandFractionImp extends BaseLandFraction {
    private boolean managed;


    public LandFractionImp(Faction localOwnerFactionRef, boolean managed) {
        super(localOwnerFactionRef);
        this.managed = managed;
    }

    @Override
    protected boolean giveManagedValue() {
        return this.managed;
    }

    @Override
    protected void turnIntoManagedLand() {
        if(!this.managed) {
            this.managed = true;
            this.setDefaultOwner();
        }
    }

    @Override
    protected void turnIntoUnmanagedLand() {
        if(this.managed) {
            this.managed = false;
            this.setNullOwner();
        }
    }

    @Override
    protected void performOwnerChange(Faction newOwner) {
        if(this.owner.equals(newOwner)) {
            System.out.println("WARNING! changing owner to previous owner");
        }
        this.owner = newOwner;
    }

    private void setDefaultOwner() {
        this.owner = this.localFactionRef;
    }

    private void setNullOwner() {
        this.owner = null;
    }

}
