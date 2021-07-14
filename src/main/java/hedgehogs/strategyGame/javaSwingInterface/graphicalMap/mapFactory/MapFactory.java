package hedgehogs.strategyGame.javaSwingInterface.graphicalMap.mapFactory;

import hedgehogs.strategyGame.gameLogic.gameLogicHub.GameLogicHub;
import hedgehogs.strategyGame.javaSwingInterface.generalBuildObjects.AbstractUIObjectFactory;
import hedgehogs.strategyGame.javaSwingInterface.graphicalMap.mapPainter.MapPainter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapFactory extends AbstractUIObjectFactory {
    @Autowired
    private GameLogicHub gameLogicHub;
    private MapPainter mapPainter;

    @Override
    protected void makeAllMinorElements() {
        this.mapPainter = new MapPainter(this.gameLogicHub);
        this.mapPainter.setSize(400, 400);
        this.addNewElementToPanel(mapPainter, 0, 0);
    }

    @Override
    protected void elementContentRefresh() {
        this.mapPainter.repaint();
    }
}
