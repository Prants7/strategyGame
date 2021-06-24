package hedgehogs.strategyGame.gameLogic.factions;

import hedgehogs.strategyGame.gameLogic.factions.uniqueFactions.LocalOwner;
import hedgehogs.strategyGame.gameLogic.factions.uniqueFactions.PlayerFaction;
import org.springframework.stereotype.Component;

@Component
public class FactionPhoneBook {
    private Faction defaultFaction;
    private Faction playerFaction;

    public FactionPhoneBook() {
        this.setUpDefaultFaction();
    }

    private void setUpDefaultFaction() {
        this.defaultFaction = new LocalOwner();
        this.playerFaction = new PlayerFaction();
    }

    public Faction getDefaultFaction() {
        return this.defaultFaction;
    }

    public Faction getPlayerFaction() {
        return this.playerFaction;
    }
}
