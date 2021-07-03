package hedgehogs.strategyGame.gameLogic.factionActionInterface;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.adminLandAssign.AdminLandAssignAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.buildOfficeAction.BuildOfficeAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landClearAction.LandClearAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.landPurchaseAction.LandPurchaseAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActions.seizeControlFromLocalsAction.SeizeControlFromLocalsAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper.TimedActionWaitList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FactionActionInterfaceImp extends AbstractFactionActionInterface {

    @Autowired
    public FactionActionInterfaceImp(LandPurchaseAction landPurchaseAction,
                                     LandClearAction landClearAction,
                                     AdminLandAssignAction adminLandAssignAction,
                                     TimedActionWaitList timedActionWaitList,
                                     BuildOfficeAction buildOfficeAction,
                                     SeizeControlFromLocalsAction seizeControlAction) {
        super(landPurchaseAction,
                landClearAction,
                adminLandAssignAction,
                timedActionWaitList,
                buildOfficeAction,
                seizeControlAction);
    }
}
