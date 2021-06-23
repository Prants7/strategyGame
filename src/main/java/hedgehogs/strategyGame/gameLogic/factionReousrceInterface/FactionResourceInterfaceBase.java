package hedgehogs.strategyGame.gameLogic.factionReousrceInterface;

public abstract class FactionResourceInterfaceBase implements FactionResourceInterface {

    @Override
    public boolean addResourceToFaction(ResourceSettings settings) {
        if(this.missingOneOfCriticalOptions(settings)) {
            System.out.println("Error: cant add resources cause missing a critical setting field");
            return false;
        }
        if(settings.getResourceType() != ResourceType.GOLD) {
            if(this.missingLocationInfo(settings)) {
                System.out.println("Error: missing location info on resource add");
                return false;
            }
        }
        return this.addResourceScript(settings);
    }

    private boolean missingOneOfCriticalOptions(ResourceSettings settings) {
        if(!settings.hasFaction()) {
            return true;
        }
        if(!settings.hasResourceType()) {
            return true;
        }
        if(!settings.hasAmount()) {
            return true;
        }
        return false;
    }

    private boolean missingLocationInfo(ResourceSettings settings) {
        if(!settings.hasProvince()) {
            return true;
        }
        return false;
    }

    protected abstract boolean addResourceScript(ResourceSettings settings);

    @Override
    public boolean removeResourceFromFaction(ResourceSettings settings) {
        if(this.missingOneOfCriticalOptions(settings)) {
            System.out.println("Error: cant withdraw resources cause missing a critical setting field");
            return false;
        }
        if(settings.getResourceType() != ResourceType.GOLD) {
            if(this.missingLocationInfo(settings)) {
                System.out.println("Error: missing location info on resource withdraw");
                return false;
            }
        }
        return this.withdrawResourceScript(settings);
    }

    protected abstract boolean withdrawResourceScript(ResourceSettings settings);

    @Override
    public boolean canRemoveResourcesFromFaction(ResourceSettings settings) {
        if(this.missingOneOfCriticalOptions(settings)) {
            System.out.println("Error: cant withdraw resources cause missing a critical setting field");
            return false;
        }
        if(settings.getResourceType() != ResourceType.GOLD) {
            if(this.missingLocationInfo(settings)) {
                System.out.println("Error: missing location info on resource withdraw");
                return false;
            }
        }
        return this.canWithdrawResourceScript(settings);
    }

    protected abstract boolean canWithdrawResourceScript(ResourceSettings settings);
}
