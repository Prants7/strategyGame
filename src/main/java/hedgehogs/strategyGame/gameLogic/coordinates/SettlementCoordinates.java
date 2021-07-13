package hedgehogs.strategyGame.gameLogic.coordinates;

public class SettlementCoordinates implements Coordinates {
    private int x;
    private int y;

    public SettlementCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public int getDistanceFrom(Coordinates otherLocation) {
        return this.calculateDistanceInInt(otherLocation);
    }

    private int calculateDistanceInInt(Coordinates otherLocation) {
        int xDistance = this.getXDistance(otherLocation.getX());
        int yDistance = this.getYDistance(otherLocation.getY());
        double distance = Math.sqrt((xDistance * xDistance) + (yDistance * yDistance));
        int rounded = (int) Math.round(distance);
        System.out.println("Rounded distance of " + distance + " to " + rounded);
        return rounded;
    }

    private int getXDistance(int otherX) {
        return this.getDistanceBetweenTwoPointsOnSameLine(this.x, otherX);
    }

    private int getYDistance(int otherY) {
        return this.getDistanceBetweenTwoPointsOnSameLine(this.y, otherY);
    }

    private int getDistanceBetweenTwoPointsOnSameLine(int firstPoint, int otherPoint) {
        int sum = firstPoint - otherPoint;
        if(sum < 0) {
            sum = sum * -1;
        }
        return sum;
    }
}
