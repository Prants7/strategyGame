package hedgehogs.strategyGame.gameLogic.land.prosperity;

public interface Prosperity {

    public int getCurrentValue();

    public void increaseByValue(int value);

    public void decreaseByValue(int amount);
}
