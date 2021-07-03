package hedgehogs.strategyGame.gameLogic.factionActionInterface;

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
                                     TimedActionWaitList timedActionWaitList,
                                     BuildOfficeAction buildOfficeAction,
                                     SeizeControlFromLocalsAction seizeControlAction) {
        super(landPurchaseAction,
                landClearAction,
                timedActionWaitList,
                buildOfficeAction,
                seizeControlAction);
    }
}
