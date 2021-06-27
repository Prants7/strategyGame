package hedgehogs.strategyGame.gameLogic.land.buildings.offices.instances;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.buildings.offices.base.OfficeBase;

public class FamilyHall extends OfficeBase {

    public FamilyHall(Faction ownerFaction) {
        super(ownerFaction);
    }

    @Override
    protected String bootGiveBuildingTypeName() {
        return "Family hall";
    }
}
