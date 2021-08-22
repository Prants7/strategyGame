package hedgehogs.strategyGame.gameLogic.land.prosperity;

import java.util.ArrayList;
import java.util.List;

public class ProsperityImp implements Prosperity {
    private double currentProsperity;
    // private List<ProsperityListener> listeners;
    // private double reachGoal;

    public ProsperityImp() {
        this.currentProsperity = 0;
        // this.listeners = new ArrayList<>();
    }

    @Override
    public double getCurrentValue() {
        return this.currentProsperity;
    }

    @Override
    public void increaseByValue(int value) {
        currentProsperity += value;
        // this.performGoalCheck();
    }

    @Override
    public void decreaseByValue(int amount) {
        currentProsperity -= amount;
        if(currentProsperity < 0) {
            currentProsperity = 0;
        }
    }

    /* @Override
    public void setReachGoal(Double targetGoal) {
        this.reachGoal = targetGoal;
    }*/

    /* @Override
    public void addNewListener(ProsperityListener newListener) {
        this.listeners.add(newListener);
    }*/

    /* private void performGoalCheck() {
        if(this.reachGoal == 0) {
            return;
        }
        if(this.currentProsperity >= reachGoal) {
            double reachedGoal = this.reachGoal;
            this.reachGoal = 0;
            this.announceGoalReachingToListeners(reachedGoal);
        }
    }*/

    /* private void announceGoalReachingToListeners(double reachedGoal) {
        this.listeners.stream().forEach(oneListener -> oneListener.announceProsperityGoalReaching(reachedGoal));
    }*/


}
