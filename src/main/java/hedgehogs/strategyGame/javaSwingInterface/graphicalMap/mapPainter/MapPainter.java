package hedgehogs.strategyGame.javaSwingInterface.graphicalMap.mapPainter;

import com.sun.tools.javac.Main;
import hedgehogs.strategyGame.gameLogic.gameLogicHub.GameLogicHub;
import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.javaSwingInterface.graphicalMap.mapFactory.MapFactory;
import hedgehogs.strategyGame.javaSwingInterface.mainWindowBooter.MainWindowFactory;

import java.awt.*;
import java.util.ArrayList;

public class MapPainter extends Canvas {
    private GameLogicHub gameLogicHub;
    private MainWindowFactory mainWindowFactor;
    private java.util.List<VisualCityObject> settlementsToDraw;
    private MapFactory masterFactory;

    public MapPainter(GameLogicHub gameLogicHub, MainWindowFactory mainWindowFactory, MapFactory masterFactory) {
        this.mainWindowFactor = mainWindowFactory;
        this.gameLogicHub = gameLogicHub;
        this.masterFactory = masterFactory;
        this.bootUp();
    }

    private void bootUp() {
        this.settlementsToDraw = new ArrayList<>();
        this.makeVisualCityObjects();
    }

    private void makeVisualCityObjects() {
        this.getAllProvinces().forEach(oneProvince ->
                this.settlementsToDraw.add(this.makeOneVisualCityObject(oneProvince)));
    }

    private VisualCityObject makeOneVisualCityObject(Province targetProvince) {
        return new VisualCityObject(targetProvince);
    }

    public void paint(Graphics g) {
        //g.fillOval(100, 100, 200, 200);
        this.paintAllSettlements(g, this.settlementsToDraw);
    }

    private java.util.List<Province> getAllProvinces() {
        return this.gameLogicHub.getWorld().getAllProvinces();
    }

    private void paintAllSettlements(Graphics g, java.util.List<VisualCityObject> provinces ) {
        for(VisualCityObject oneCity : provinces) {
            this.paintOneSettlement(g, oneCity);
        }
    }

    private void paintOneSettlement(Graphics g, VisualCityObject oneCity) {
        if(oneCity.getLogicalObject().accessLocationOffices().hasControllingFaction()) {
            g.setColor(Color.GREEN);
        }
        g.fillOval(oneCity.getDrawX(), oneCity.getDrawY(), oneCity.getDrawLength(), oneCity.getDrawWidth());
        g.setColor(Color.BLACK);
        g.drawString(oneCity.getLogicalObject().getProvinceName(), oneCity.getDrawX(), oneCity.getDrawY());
    }

    public void performClickSearch(int clickX, int clickY) {
        for(VisualCityObject oneDrawable : this.settlementsToDraw) {
            if(oneDrawable.clickedOnThis(clickX, clickY)) {
                this.masterFactory.giveClickedElement(oneDrawable);
            }
        }
    }
}
