package hedgehogs.strategyGame.javaSwingInterface.mainWindowBooter;

import hedgehogs.strategyGame.gameLogic.gameLogicHub.GameLogicHub;
import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.javaSwingInterface.factionInfo.FactionInfoFactory;
import hedgehogs.strategyGame.javaSwingInterface.provinceList.ProvinceListFactory;
import hedgehogs.strategyGame.javaSwingInterface.provinceList.ProvinceSelectListener;
import hedgehogs.strategyGame.javaSwingInterface.provinceView.ProvinceViewFactory;
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
    private JFrame mainFrame;
    private ProvinceSelectListener provinceSelectListener;

    public void makeMainWindow() {
        this.setUpGame();
        this.mainFrame =new JFrame();//creating instance of JFrame
        mainFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        /*JButton b=new JButton("click");//creating instance of JButton
        b.setBounds(130,100,100, 40);//x axis, y axis, width, height*/

        //f.add(b);//adding button in JFrame
        JList<Province> provinceList = this.provinceListFactory.giveProvinceList(this.gameLogic.getWorld().getAllProvinces());
        provinceSelectListener = new ProvinceSelectListener(provinceList, this);
        provinceList.addListSelectionListener(provinceSelectListener);
        mainFrame.add(provinceList, BorderLayout.WEST);


        mainFrame.add(this.factionInfoFactory.getFactionInfoPanel(this.gameLogic.getFactionPhoneBook().getPlayerFaction()), BorderLayout.NORTH);
        mainFrame.add(this.turnChangeButtonFactory.giveTurnChangeButton(this.gameLogic.getTimeCenterSocket(), this), BorderLayout.SOUTH);
        mainFrame.add(this.provinceViewFactory.giveProvinceView(), BorderLayout.CENTER);

        mainFrame.setSize(800,600);//400 width and 500 height
        //f.setLayout(new BoxLayout(f, BoxLayout.X_AXIS));//using no layout managers
        mainFrame.setVisible(true);//making the frame visible
    }

    private void setUpGame() {
        this.gameLogic.bootUpGame();
    }

    public void updateTexts() {
        this.factionInfoFactory.updateData();
    }

    public void openProvinceView(Province selectedProvince) {
        this.provinceViewFactory.openViewForProvince(selectedProvince);
    }
}
