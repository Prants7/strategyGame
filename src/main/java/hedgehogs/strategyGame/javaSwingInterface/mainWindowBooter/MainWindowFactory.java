package hedgehogs.strategyGame.javaSwingInterface.mainWindowBooter;

import hedgehogs.strategyGame.gameLogic.gameLogicHub.GameLogicHub;
import hedgehogs.strategyGame.javaSwingInterface.factionInfo.FactionInfoFactory;
import hedgehogs.strategyGame.javaSwingInterface.provinceList.ProvinceListFactory;
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

    public void makeMainWindow() {
        this.setUpGame();
        JFrame f=new JFrame();//creating instance of JFrame
        f.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        /*JButton b=new JButton("click");//creating instance of JButton
        b.setBounds(130,100,100, 40);//x axis, y axis, width, height*/

        //f.add(b);//adding button in JFrame
        f.add(this.provinceListFactory.giveProvinceList(this.gameLogic.getWorld().getAllProvinces()), BorderLayout.WEST);
        f.add(this.factionInfoFactory.getFactionInfoPanel(this.gameLogic.getFactionPhoneBook().getPlayerFaction()), BorderLayout.NORTH);
        f.add(this.turnChangeButtonFactory.giveTurnChangeButton(this.gameLogic.getTimeCenterSocket(), this), BorderLayout.SOUTH);

        f.setSize(400,500);//400 width and 500 height
        //f.setLayout(new BoxLayout(f, BoxLayout.X_AXIS));//using no layout managers
        f.setVisible(true);//making the frame visible
    }

    private void setUpGame() {
        this.gameLogic.bootUpGame();
    }

    public void updateTexts() {
        this.factionInfoFactory.updateData();
    }
}
