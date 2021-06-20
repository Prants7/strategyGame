package hedgehogs.strategyGame.javaSwingInterface.turnChangeButton;

import hedgehogs.strategyGame.gameLogic.gameTime.TimeCenterSocket;
import hedgehogs.strategyGame.javaSwingInterface.mainWindowBooter.MainWindowFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TurnChangeButtonListener implements ActionListener {
    private TimeCenterSocket timeCenterSocket;
    private MainWindowFactory mainWindowFactory;

    public TurnChangeButtonListener(TimeCenterSocket timeCenterSocket, MainWindowFactory mainWindowFactory) {
        this.timeCenterSocket = timeCenterSocket;
        this.mainWindowFactory = mainWindowFactory;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.timeCenterSocket.advanceTime();
        this.mainWindowFactory.updateTexts();
        System.out.println("Clicked next turn button");
    }
}
