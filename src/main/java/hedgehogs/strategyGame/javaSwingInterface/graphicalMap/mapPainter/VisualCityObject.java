package hedgehogs.strategyGame.javaSwingInterface.graphicalMap.mapPainter;

import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.javaSwingInterface.mainWindowBooter.MainWindowFactory;

public class VisualCityObject {
    //private MainWindowFactory mainWindowFactory;
    private Province logicalObject;
    private int drawX;
    private int drawY;
    private int drawLength;
    private int drawWidth;

    public VisualCityObject(Province logicalObject) {
        //this.mainWindowFactory = mainWindowFactory;
        this.logicalObject = logicalObject;
        this.drawX = this.logicalObject.accessCoordinates().getX();
        this.drawY = this.logicalObject.accessCoordinates().getY();
        this.drawWidth = 20;
        this.drawLength = 20;
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

    public int getDrawWidth() {
        return drawWidth;
    }

    public boolean clickedOnThis(int clickX, int clickY) {
        if(!checkIfClickCoordinatesOnThis(clickX, clickY)) {
            return false;
        }
        //System.out.println("Clicked on settlement "+this.logicalObject.getProvinceName());
        //this.mainWindowFactory.openProvinceView(this.logicalObject);
        return true;
    }

    private boolean checkIfClickCoordinatesOnThis(int clickX, int clickY) {
        if(!this.isInsideTheXZone(clickX)) {
            return false;
        }
        if(!this.isInsideTheYZone(clickY)) {
            return false;
        }
        return true;
    }

    private boolean isInsideTheXZone(int clickX) {
        if(clickX < this.drawX) {
            return false;
        }
        if(clickX > this.drawX + this.drawLength) {
            return false;
        }
        return true;
    }

    private boolean isInsideTheYZone(int clickY) {
        if(clickY < this.drawY) {
            return false;
        }
        if(clickY > this.drawY + this.drawWidth) {
            return false;
        }
        return true;
    }
}
