package hedgehogs.strategyGame.javaSwingInterface.mainWindowBooter;

import hedgehogs.strategyGame.gameLogic.agents.base.Agent;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionBase.FactionAction;
import hedgehogs.strategyGame.gameLogic.factionActionInterface.factionActionInput.FactionActionInput;
import hedgehogs.strategyGame.gameLogic.gameLogicHub.GameLogicHub;
import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.javaSwingInterface.buildingsView.BuildingViewFactory;
import hedgehogs.strategyGame.javaSwingInterface.factionInfo.FactionInfoFactory;
import hedgehogs.strategyGame.javaSwingInterface.graphicalMap.mapFactory.MapFactory;
import hedgehogs.strategyGame.javaSwingInterface.oneAgentView.OneAgentViewFactory;
import hedgehogs.strategyGame.javaSwingInterface.provinceList.ProvinceListFactory;
import hedgehogs.strategyGame.javaSwingInterface.provinceList.ProvinceSelectListener;
import hedgehogs.strategyGame.javaSwingInterface.provinceView.ProvinceViewFactory;
import hedgehogs.strategyGame.javaSwingInterface.tabViewer.TabViewerFactory;
import hedgehogs.strategyGame.javaSwingInterface.timedActionListView.TimedActionListViewFactory;
import hedgehogs.strategyGame.javaSwingInterface.turnChangeButton.TurnChangeButtonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class MainWindowFactory {
    @Autowired
    private GameLogicHub gameLogic;
    @Autowired
    private ProvinceListFactory provinceListFactory;
    @Autowired
    private FactionInfoFactory factionInfoFactory;
    @Autowired
    private TurnChangeButtonFactory turnChangeButtonFactory;
    @Autowired
    private ProvinceViewFactory provinceViewFactory;
    @Autowired
    private TimedActionListViewFactory timedActionListViewFactory;
    @Autowired
    private TabViewerFactory tabViewerFactory;
    @Autowired
    private OneAgentViewFactory agentViewFactory;
    @Autowired
    private MapFactory mapFactory;
    @Autowired
    private BuildingViewFactory buildingViewFactory;
    private JFrame mainFrame;
    private ProvinceSelectListener provinceSelectListener;

    public void makeMainWindow() {
        this.setUpGame();
        this.mainFrame =new JFrame();//creating instance of JFrame
        mainFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JButton mapButton = new JButton("Open map");
        mapButton.addActionListener(event -> {
            this.openMainMap();
        });
        mainFrame.add(mapButton, BorderLayout.WEST);
        mainFrame.add(this.factionInfoFactory.getFactionInfoPanel(this.gameLogic.getFactionPhoneBook().getPlayerFaction()), BorderLayout.NORTH);
        mainFrame.add(this.turnChangeButtonFactory.giveTurnChangeButton(this.gameLogic.getTimeCenterSocket(), this), BorderLayout.SOUTH);

        mainFrame.add(this.tabViewerFactory.getPanelObject(),BorderLayout.CENTER);
        this.tabViewerFactory.changeScreenTo(this.mapFactory);

        mainFrame.add(this.timedActionListViewFactory.getPanelObject(), BorderLayout.EAST);

        mainFrame.setSize(800,600);
        mainFrame.setVisible(true);
    }

    private void setUpGame() {
        this.gameLogic.bootUpGame();
    }

    public void updateTexts() {
        this.factionInfoFactory.updateData();
        this.provinceViewFactory.refreshElements();
        this.timedActionListViewFactory.refreshElements();
        this.tabViewerFactory.refreshElements();
    }

    public void openProvinceView(Province selectedProvince) {
        this.tabViewerFactory.changeScreenTo(this.provinceViewFactory);
        this.provinceViewFactory.openViewForProvince(selectedProvince);
    }

    public void openAgentView(Agent selectedAgent) {
        this.tabViewerFactory.changeScreenTo(this.agentViewFactory);
        this.agentViewFactory.selectAgent(selectedAgent);
    }

    public void openMainMap() {
        this.mapFactory.clearAction();
        this.tabViewerFactory.changeScreenTo(this.mapFactory);
    }

    public void openMainMapForActionInput(FactionAction targetAction, FactionActionInput input) {
        this.mapFactory.setAction(targetAction, input);
        this.tabViewerFactory.changeScreenTo(this.mapFactory);
    }

    public void openBuildingViewForProvince(Province targetProvince) {
        this.tabViewerFactory.changeScreenTo(this.buildingViewFactory);
        this.buildingViewFactory.openViewForProvince(targetProvince);
    }
}
