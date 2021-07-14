package hedgehogs.strategyGame.javaSwingInterface.graphicalMap.mapPainter;

import hedgehogs.strategyGame.gameLogic.gameLogicHub.GameLogicHub;
import hedgehogs.strategyGame.gameLogic.land.Province;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.util.ArrayList;

public class MapPainter extends Canvas {
    private GameLogicHub gameLogicHub;
    private java.util.List<VisualCityObject> settlementsToDraw;

    public MapPainter(GameLogicHub gameLogicHub) {
        this.gameLogicHub = gameLogicHub;
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
        g.fillOval(oneCity.getDrawX(), oneCity.getDrawY(), oneCity.getDrawLength(), oneCity.getDrawHeight());
        g.drawString(oneCity.getLogicalObject().getProvinceName(), oneCity.getDrawX(), oneCity.getDrawY());
    }
}
