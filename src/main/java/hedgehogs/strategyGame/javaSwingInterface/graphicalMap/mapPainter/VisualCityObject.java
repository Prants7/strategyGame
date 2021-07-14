package hedgehogs.strategyGame.javaSwingInterface.graphicalMap.mapPainter;

import hedgehogs.strategyGame.gameLogic.land.Province;

public class VisualCityObject {
    private Province logicalObject;
    private int drawX;
    private int drawY;
    private int drawLength;
    private int drawHeight;

    public VisualCityObject(Province logicalObject) {
        this.logicalObject = logicalObject;
        this.drawX = this.logicalObject.accessCoordinates().getX();
        this.drawY = this.logicalObject.accessCoordinates().getY();
        this.drawHeight = 10;
        this.drawLength = 10;
    }

    public Province getLogicalObject() {
        return logicalObject;
    }

    public int getDrawX() {
        return drawX;
    }

    public int getDrawY() {
        return drawY;
    }

    public int getDrawLength() {
        return drawLength;
    }

    public int getDrawHeight() {
        return drawHeight;
    }
}
