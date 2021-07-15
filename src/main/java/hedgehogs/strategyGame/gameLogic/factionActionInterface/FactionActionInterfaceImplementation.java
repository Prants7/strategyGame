package hedgehogs.strategyGame.gameLogic.factionActionInterface;

import hedgehogs.strategyGame.gameLogic.agents.adminAgent.AdminAgent;
import hedgehogs.strategyGame.gameLogic.agents.agentFactory.AgentFactory;
import hedgehogs.strategyGame.gameLogic.agents.base.Agent;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.FactionActionInput;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.FactionActionInputImp;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.buildOfficeAction.BuildOfficeAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landClearAction.LandClearAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landPurchaseAction.LandPurchaseAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.moveAgentAction.MoveAgentAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.seizeControlFromLocalsAction.SeizeControlFromLocalsAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper.TimedActionWaitList;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper.TimedActionWrapper;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper.TimedActionWrapperImp;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.land.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FactionActionInterfaceImplementation implements FactionActionInterface {
    private AgentFactory agentFactory;
    private FactionAction landPurchaseAction;
    private FactionAction landClearAction;
    private TimedActionWaitList timedActionWaitList;
    private FactionAction buildOfficeAction;
    private FactionAction seizeControlAction;
    private List<FactionAction> actionList;
    private MoveAgentAction moveAgentAction;

    @Autowired
    public FactionActionInterfaceImplementation(AgentFactory agentFactory,
                                                LandPurchaseAction landPurchaseAction,
                                                LandClearAction landClearAction,
                                                TimedActionWaitList timedActionWaitList,
                                                BuildOfficeAction buildOfficeAction,
                                                SeizeControlFromLocalsAction seizeControlAction,
                                                MoveAgentAction moveAgentAction) {
        this.agentFactory = agentFactory;
        this.landPurchaseAction = landPurchaseAction;
        this.landClearAction = landClearAction;
        this.timedActionWaitList = timedActionWaitList;
        this.buildOfficeAction = buildOfficeAction;
        this.seizeControlAction = seizeControlAction;
        this.makeActionList();
        this.moveAgentAction = moveAgentAction;
    }

    private void makeActionList() {
        this.actionList = new ArrayList<>();
        this.actionList.add(this.landClearAction);
        this.actionList.add(this.landPurchaseAction);
        this.actionList.add(this.buildOfficeAction);
        this.actionList.add(this.seizeControlAction);
    }

    @Override
    public void performAdminLandAssign(Faction forFaction, Province targetProvince) {
        adminPerformAction(this.landPurchaseAction, forFaction, targetProvince);
    }

    protected void adminPerformAction(FactionAction action, Faction targetFaction, Province targetProvince) {
        FactionActionInput tempInput =
                new FactionActionInputImp().setAgent(this.getAdminAgent(targetFaction, targetProvince));
        action.forceDoAction(tempInput);
    }

    private Agent getAdminAgent(Faction targetFaction, Province targetProvince) {
        return agentFactory.makeNewAdminAgent(targetFaction, targetProvince);
    }

    @Override
    public void performAdminFamilyHallBuild(Faction callingFaction, Province targetProvince) {
        this.adminPerformAction(this.buildOfficeAction, callingFaction, targetProvince);
    }

    @Override
    public List<FactionAction> getListOfUsableFactionActions() {
        return new ArrayList<>(this.actionList);
    }

    @Override
    public MoveAgentAction getMoveAgentAction() {
        return this.moveAgentAction;
    }
}
