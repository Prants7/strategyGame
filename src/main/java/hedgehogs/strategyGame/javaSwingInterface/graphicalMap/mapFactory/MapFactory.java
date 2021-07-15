package hedgehogs.strategyGame.javaSwingInterface.graphicalMap.mapFactory;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.FactionActionInput;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.gameLogicHub.GameLogicHub;
import hedgehogs.strategyGame.javaSwingInterface.generalBuildObjects.AbstractUIObjectFactory;
import hedgehogs.strategyGame.javaSwingInterface.graphicalMap.mapPainter.MapPainter;
import hedgehogs.strategyGame.javaSwingInterface.graphicalMap.mapPainter.VisualCityObject;
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
    private FactionAction targetAction;
    private FactionActionInput actionInput;

    @Override
    protected void makeAllMinorElements() {
        this.mapPainter = new MapPainter(this.gameLogicHub, mainWindowFactory, this);
        this.mapPainter.setSize(400, 400);
        this.addNewElementToPanel(mapPainter, 0, 0);
        this.mouseListener = new CanvasMouseListener(this.mapPainter);
    }

    @Override
    protected void elementContentRefresh() {
        this.mapPainter.repaint();
    }

    public boolean hasAction() {
        return this.targetAction != null;
    }

    public void clearAction() {
        this.targetAction = null;
        this.actionInput = null;
    }

    public void setAction(FactionAction targetAction, FactionActionInput targetInput) {
        this.targetAction = targetAction;
        this.actionInput = targetInput;
    }

    public void giveClickedElement(VisualCityObject clickedObject) {
        if(this.hasAction()) {
            this.actionInput.setOtherLocation(clickedObject.getLogicalObject());
            this.gameLogicHub.getFactionActionInterface().tryToPerformActionWithAgent(this.targetAction, this.actionInput);
            this.clearAction();
        }
        this.mainWindowFactory.openProvinceView(clickedObject.getLogicalObject());
        this.mainWindowFactory.updateTexts();

    }
}
