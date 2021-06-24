package hedgehogs.strategyGame.gameLogic.factions;

import hedgehogs.strategyGame.gameLogic.factions.FactionFractionTaxPolicy.FactionFractionTaxPolicy;
import hedgehogs.strategyGame.gameLogic.factions.FactionFractionTaxPolicy.PlayerTaxPolicy;

public class PlayerFaction extends OrganisedFaction {

    @Override
    protected String provideFactionName() {
        return "Player Faction";
    }

    @Override
    protected FactionFractionTaxPolicy provideTaxPolicy() {
        return new PlayerTaxPolicy();
    }


}
