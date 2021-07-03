package hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TimedActionWaitList {
    private List<TimedActionWrapper> waitingList;

    @PostConstruct
    private void bootUp() {
        this.waitingList = new ArrayList<>();
    }

    public void addNewTimedAction(TimedActionWrapper newAction) {
        if(!newAction.isFinished()) {
            this.waitingList.add(newAction);
        }
        //printOutAllActionsInList();
    }

    public void advanceTime() {
        this.countUpActions();
        this.performActionCleanUp();
        printOutAllActionsInList();
    }

    private void countUpActions() {
        this.waitingList.forEach(oneAction -> oneAction.countDown());
    }

    private void performActionCleanUp() {
        this.removeFinishedActions(this.getFinishedActions());
    }

    private List<TimedActionWrapper> getFinishedActions() {
        List<TimedActionWrapper> finishedActions = this.waitingList.stream()
                .filter(oneAction -> oneAction.isFinished() == true).collect(Collectors.toList());
        return finishedActions;
    }

    private void removeFinishedActions(List<TimedActionWrapper> finishedActions) {
        this.waitingList.removeAll(finishedActions);
    }

    private void printOutAllActionsInList() {
        System.out.println("printing out waiting actions");
        for(TimedActionWrapper oneAction: this.waitingList) {
            System.out.println("action waiting: "+oneAction.getDesignatedAction().getClass());
        }
        System.out.println("----------");
    }

    public List<TimedActionWrapper> getCopyOfActions() {
        return new ArrayList<>(this.waitingList);
    }
}
