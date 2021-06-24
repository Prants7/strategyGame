package hedgehogs.strategyGame.gameLogic.land.prosperity;

public class ProsperityImp implements Prosperity {
    private int currentProsperity;

    public ProsperityImp() {
        this.currentProsperity = 0;
    }

    @Override
    public int getCurrentValue() {
        return this.currentProsperity;
    }

    @Override
    public void increaseByValue(int value) {
        currentProsperity += value;
    }

    @Override
    public void decreaseByValue(int amount) {
        currentProsperity -= amount;
        if(currentProsperity < 0) {
            currentProsperity = 0;
        }
    }
}
