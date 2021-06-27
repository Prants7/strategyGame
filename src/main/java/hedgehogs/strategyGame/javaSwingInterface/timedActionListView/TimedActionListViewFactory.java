package hedgehogs.strategyGame.javaSwingInterface.timedActionListView;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper.TimedActionWaitList;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.Map;

@Component
public class TimedActionListViewFactory {
    @Autowired
    private TimedActionWaitList timedActionWaitList;
    private JPanel mainPanel;
    private JList<String> timedActionList;

    public JPanel getMainElement() {
        this.mainPanel = new JPanel();
        this.setUpElementsOnMainPanel();
        return this.mainPanel;
    }

    private void setUpElementsOnMainPanel() {
        this.timedActionList = new JList<>();
        this.writeDataIntoList();
        this.mainPanel.add(timedActionList);
    }

    private void writeDataIntoList() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        this.timedActionWaitList.getCopyOfActions().forEach(
                oneElement -> listModel.addElement(oneElement.getDescriptionString())
        );
        this.timedActionList.setModel(listModel);
    }

    public void updateData(){
        this.writeDataIntoList();
    }

}
