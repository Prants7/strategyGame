package hedgehogs.strategyGame.gameLogic.land.buildings.offices.base;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.buildings.officeBuildingSlot.OfficeBuildingManager;
import hedgehogs.strategyGame.gameLogic.land.buildings.officeBuildingSlot.OfficeBuildingManagerImp;

public abstract class OfficeBase implements Office {
    protected String typeName;
    protected Faction ownerFaction;
    protected OfficeBuildingManager officeBuildingManager;

    public OfficeBase(Faction ownerFaction) {
        this.ownerFaction = ownerFaction;
        this.typeName = this.bootGiveBuildingTypeName();
        this.officeBuildingManager = new OfficeBuildingManagerImp(this);
    }

    @Override
    public Faction getOwnerFaction() {
        return this.ownerFaction;
    }

    @Override
    public String getBuildingTypeName() {
        return this.typeName;
    }

    protected abstract String bootGiveBuildingTypeName();

    @Override
    public String toString() {
        return this.ownerFaction.getFactionName() + ": " + this.getBuildingTypeName();
    }

    @Override
    public OfficeBuildingManager accessOfficeBuildingManager() {
        return this.officeBuildingManager;
    }
}
