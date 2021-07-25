package hedgehogs.strategyGame.javaSwingInterface.actionInputBuilder;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.ActionInputName;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.FactionActionInput;
import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.javaSwingInterface.mainWindowBooter.MainWindowFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActionInputBuilder {
    @Autowired
    private MainWindowFactory mainWindowFactory;
    private FactionAction targetedAction;
    private FactionActionInput alreadyExistingInputs;

    public void startFillingNewAction(FactionAction targetedAction, FactionActionInput startingInputs) {
        this.targetedAction = targetedAction;
        this.alreadyExistingInputs = startingInputs;
        this.performInputCheck();
    }

    private void performInputCheck() {
        if(getNextMissingInput() == null) {
            this.targetedAction.startAction(this.alreadyExistingInputs);
            clearCurrentData();
        }
        else {
            this.acquireNextInputValue(this.getNextMissingInput());
        }
    }

    private ActionInputName getNextMissingInput() {
        for(ActionInputName oneName : this.targetedAction.getRequiredInputs()) {
            if(!alreadyExistingInputs.getInputValueByEnum(oneName).hasElement()) {
                return oneName;
            }
        }
        return null;
    }

    private void acquireNextInputValue(ActionInputName inputName) {
        if(inputName == ActionInputName.NEXT_LOCATION) {
            this.acquireNextLocation();
        }
        System.out.println("NEEDS INPUT THAT HAS NO SOLUTION: "+inputName);
    }

    private void acquireNextLocation() {
        this.mainWindowFactory.openMainMapForActionInput();
    }

    public void sendLocationInputData(Province selectedProvince) {
        this.alreadyExistingInputs.setOtherLocation(selectedProvince);
        this.performInputCheck();
    }

    private void clearCurrentData() {
        this.targetedAction = null;
        this.alreadyExistingInputs = null;
    }
}
