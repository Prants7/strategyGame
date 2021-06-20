package hedgehogs.strategyGame.javaSwingInterface.turnChangeButton;

import hedgehogs.strategyGame.gameLogic.gameTime.TimeCenterSocket;
import hedgehogs.strategyGame.javaSwingInterface.mainWindowBooter.MainWindowFactory;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class TurnChangeButtonFactory {

    public JButton giveTurnChangeButton(TimeCenterSocket timeCenterSocket, MainWindowFactory mainWindowFactory) {
        JButton b = new JButton("Next turn");
        b.addActionListener(new TurnChangeButtonListener(timeCenterSocket, mainWindowFactory));

        return b;
    }
}
