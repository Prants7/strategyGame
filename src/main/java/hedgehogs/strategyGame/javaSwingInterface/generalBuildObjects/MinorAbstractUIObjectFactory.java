package hedgehogs.strategyGame.javaSwingInterface.generalBuildObjects;

import hedgehogs.strategyGame.gameLogic.factions.Faction;

public abstract class MinorAbstractUIObjectFactory extends AbstractUIObjectFactory {
    protected Faction perspectiveFaction;

    public MinorAbstractUIObjectFactory(Faction perspectiveFaction) {
        this.perspectiveFaction = perspectiveFaction;
    }

    protected Faction getPerspectiveFaction() {
        return this.perspectiveFaction;
    }
}
