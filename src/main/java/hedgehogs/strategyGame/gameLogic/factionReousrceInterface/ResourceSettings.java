package hedgehogs.strategyGame.gameLogic.factionReousrceInterface;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import lombok.Data;

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

    public Faction getFaction() {
        return faction;
    }

    public ResourceSettings setFaction(Faction faction) {
        this.faction = faction;
        return this;
    }

    public Province getProvince() {
        return province;
    }

    public ResourceSettings setProvince(Province province) {
        this.province = province;
        return this;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public ResourceSettings setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    public ResourceSettings setAmount(int amount) {
        this.amount = amount;
        return this;
    }
}
