package hedgehogs.strategyGame.gameLogic.land.prosperity;

public interface Prosperity {

    public double getCurrentValue();

    public void increaseByValue(int value);

    public void decreaseByValue(int amount);

    // public void setReachGoal(Double targetGoal);

    // public void addNewListener(ProsperityListener newListener);
}
