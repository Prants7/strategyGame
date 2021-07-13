package hedgehogs.strategyGame.gameLogic.coordinates;

public interface Coordinates {

    public int getX();

    public int getY();

    public int getDistanceFrom(Coordinates otherLocation);
}
