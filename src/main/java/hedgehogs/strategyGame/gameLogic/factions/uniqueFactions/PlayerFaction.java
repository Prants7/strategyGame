package hedgehogs.strategyGame.gameLogic.factions.uniqueFactions;

import hedgehogs.strategyGame.gameLogic.factions.FactionFractionTaxPolicy.FactionFractionTaxPolicy;
import hedgehogs.strategyGame.gameLogic.factions.FactionFractionTaxPolicy.PlayerTaxPolicy;
import hedgehogs.strategyGame.gameLogic.factions.OrganisedFaction;

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
