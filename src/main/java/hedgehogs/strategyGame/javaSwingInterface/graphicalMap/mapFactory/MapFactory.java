package hedgehogs.strategyGame.javaSwingInterface.graphicalMap.mapFactory;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.FactionActionInput;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.gameLogic.gameLogicHub.GameLogicHub;
import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.javaSwingInterface.actionInputBuilder.ActionInputBuilder;
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
    @Autowired
    private ActionInputBuilder actionInputBuilder;
    private MapPainter mapPainter;
    private CanvasMouseListener mouseListener;
    private boolean lookingForInput = false;

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
        return this.lookingForInput;
    }

    public void clearAction() {
        this.lookingForInput = false;
    }

    public void askForLocationData() {
        this.lookingForInput = true;
    }

    public void giveClickedElement(VisualCityObject clickedObject) {
        if(this.hasAction()) {
            this.sendLocationData(clickedObject.getLogicalObject());
            this.clearAction();
        }
        this.mainWindowFactory.openProvinceView(clickedObject.getLogicalObject());
        this.mainWindowFactory.updateTexts();

    }

    private void sendLocationData(Province selectedProvince) {
        this.actionInputBuilder.sendLocationInputData(selectedProvince);
    }
}
