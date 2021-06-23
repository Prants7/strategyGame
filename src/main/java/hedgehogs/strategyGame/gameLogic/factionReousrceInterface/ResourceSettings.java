package hedgehogs.strategyGame.gameLogic.factionReousrceInterface;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import lombok.Data;

@Data
public class ResourceSettings {
    private Faction faction;
    private Province province;
    private ResourceType resourceType;
    private int amount;

    public boolean hasFaction() {
        return this.faction != null;
    }

    public boolean hasProvince() {
        return this.province != null;
    }

    public boolean hasResourceType() {
        return this.resourceType != null;
    }

    public boolean hasAmount() {
        return this.amount > 0;
    }
}
