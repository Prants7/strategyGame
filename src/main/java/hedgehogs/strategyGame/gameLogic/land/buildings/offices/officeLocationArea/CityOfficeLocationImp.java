package hedgehogs.strategyGame.gameLogic.land.buildings.offices.officeLocationArea;

import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.buildings.offices.base.Office;

import java.util.HashMap;
import java.util.Map;

public class CityOfficeLocationImp {
    private Map<Faction, Office> officeMap;
    private Faction provinceController;

    public CityOfficeLocationImp() {
        this.officeMap = new HashMap<>();
        this.provinceController = null;
    }

    public Map<Faction, Office> getAllOffices() {
        return new HashMap<>(this.officeMap);
    }

    public boolean factionHasOffice(Faction checkFaction) {
        return officeMap.containsKey(checkFaction);
    }

    public void  addNewOffice(Office newOffice) {
        Faction owner = newOffice.getOwnerFaction();
        if(this.officeMap.containsKey(owner)) {
            System.out.println("replacing faction: "+owner.getFactionName()+" office");
        }
        else {
            System.out.println("adding new office for faction: "+owner.getFactionName());
        }
        this.officeMap.put(owner, newOffice);
    }

    public boolean hasControllingFaction() {
        return this.provinceController != null;
    }

    public Faction getControllingFaction() {
        return this.provinceController;
    }

    public Office getControllingBuilding() {
        if(this.hasControllingFaction()) {
            return this.officeMap.get(this.provinceController);
        }
        else {
            return null;
        }
    }

    public String getControlSituation() {
        if(this.hasControllingFaction()) {
            return "Controller: "+this.provinceController.getFactionName();
        }
        return "Local control";
    }

    public boolean setControllingFaction(Faction faction) {
        if(!this.officeMap.containsKey(faction)) {
            return false;
        }
        this.provinceController = faction;
        return true;
    }
}
