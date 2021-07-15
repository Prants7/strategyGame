package hedgehogs.strategyGame.gameLogic.factionActionInterface;

import hedgehogs.strategyGame.gameLogic.agents.base.Agent;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.FactionActionInput;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.moveAgentAction.MoveAgentAction;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;

import java.util.List;

public interface FactionActionInterface {

    public void performLandPurchase(FactionActionInput input);

    public void performAdminLandAssign(Faction forFaction, Province targetProvince);

    public void performLandClearance(FactionActionInput input);

    public void performFamilyHallBuild(FactionActionInput input);

    public void performAdminFamilyHallBuild(Faction callingFaction, Province targetProvince);

    public void seizeControlInCity(FactionActionInput input);

    public List<FactionAction> getListOfUsableFactionActions();

    //public boolean tryToPerformActionWithAgent(FactionAction desiredAction, FactionActionInput input);

    public MoveAgentAction getMoveAgentAction();
}
