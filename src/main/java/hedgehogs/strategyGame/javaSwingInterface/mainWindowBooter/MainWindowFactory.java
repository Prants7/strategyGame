package hedgehogs.strategyGame.javaSwingInterface.mainWindowBooter;

import hedgehogs.strategyGame.gameLogic.agents.base.Agent;
import hedgehogs.strategyGame.gameLogic.gameLogicHub.GameLogicHub;
import hedgehogs.strategyGame.gameLogic.land.Province;
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
    private JFrame mainFrame;
    private ProvinceSelectListener provinceSelectListener;

    public void makeMainWindow() {
        this.setUpGame();
        this.mainFrame =new JFrame();//creating instance of JFrame
        mainFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        /*JButton b=new JButton("click");//creating instance of JButton
        b.setBounds(130,100,100, 40);//x axis, y axis, width, height*/

        //f.add(b);//adding button in JFrame
        /*JList<Province> provinceList = this.provinceListFactory.giveProvinceList(this.gameLogic.getWorld().getAllProvinces());
        provinceSelectListener = new ProvinceSelectListener(provinceList, this);
        provinceList.addListSelectionListener(provinceSelectListener);*/

        //mainFrame.add(provinceList, BorderLayout.WEST);
        JButton mapButton = new JButton("Open map");
        mapButton.addActionListener(event -> {
            this.openMainMap();
        });
        mainFrame.add(mapButton, BorderLayout.WEST);
        mainFrame.add(this.factionInfoFactory.getFactionInfoPanel(this.gameLogic.getFactionPhoneBook().getPlayerFaction()), BorderLayout.NORTH);
        mainFrame.add(this.turnChangeButtonFactory.giveTurnChangeButton(this.gameLogic.getTimeCenterSocket(), this), BorderLayout.SOUTH);

        mainFrame.add(this.tabViewerFactory.getPanelObject(),BorderLayout.CENTER);
        this.tabViewerFactory.changeScreenTo(this.mapFactory);
        //mainFrame.add(this.provinceViewFactory.getPanelObject(), BorderLayout.CENTER);

        mainFrame.add(this.timedActionListViewFactory.getPanelObject(), BorderLayout.EAST);

        mainFrame.setSize(800,600);//400 width and 500 height
        //f.setLayout(new BoxLayout(f, BoxLayout.X_AXIS));//using no layout managers
        mainFrame.setVisible(true);//making the frame visible
    }

    private void setUpGame() {
        this.gameLogic.bootUpGame();
    }

    public void updateTexts() {
        this.factionInfoFactory.updateData();
        this.provinceViewFactory.refreshElements();
        this.timedActionListViewFactory.refreshElements();
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
        this.tabViewerFactory.changeScreenTo(this.mapFactory);
    }
}
