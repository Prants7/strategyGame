package hedgehogs.strategyGame.gameLogic.land.buildings.offices.base;

import hedgehogs.strategyGame.gameLogic.factions.Faction;

public abstract class OfficeBase implements Office {
    protected String typeName;
    protected Faction ownerFaction;

    public OfficeBase(Faction ownerFaction) {
        this.ownerFaction = ownerFaction;
        this.typeName = this.bootGiveBuildingTypeName();
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


}
