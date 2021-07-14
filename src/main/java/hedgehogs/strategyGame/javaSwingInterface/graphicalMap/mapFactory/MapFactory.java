package hedgehogs.strategyGame.javaSwingInterface.graphicalMap.mapFactory;

import hedgehogs.strategyGame.gameLogic.gameLogicHub.GameLogicHub;
import hedgehogs.strategyGame.javaSwingInterface.generalBuildObjects.AbstractUIObjectFactory;
import hedgehogs.strategyGame.javaSwingInterface.graphicalMap.mapPainter.MapPainter;
import hedgehogs.strategyGame.javaSwingInterface.graphicalMap.mouseListener.CanvasMouseListener;
import hedgehogs.strategyGame.javaSwingInterface.mainWindowBooter.MainWindowFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapFactory extends AbstractUIObjectFactory {
    @Autowired
    private GameLogicHub gameLogicHub;
    @Autowired
    private MainWindowFactory mainWindowFactory;
    private MapPainter mapPainter;
    private CanvasMouseListener mouseListener;

    @Override
    protected void makeAllMinorElements() {
        this.mapPainter = new MapPainter(this.gameLogicHub, mainWindowFactory);
        this.mapPainter.setSize(400, 400);
        this.addNewElementToPanel(mapPainter, 0, 0);
        this.mouseListener = new CanvasMouseListener(this.mapPainter);
    }

    @Override
    protected void elementContentRefresh() {
        this.mapPainter.repaint();
    }
}