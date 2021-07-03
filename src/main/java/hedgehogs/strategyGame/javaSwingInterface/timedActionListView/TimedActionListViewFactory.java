package hedgehogs.strategyGame.javaSwingInterface.timedActionListView;

import hedgehogs.strategyGame.gameLogic.factionActionInterface.timedActionWrapper.TimedActionWaitList;
import hedgehogs.strategyGame.gameLogic.factions.Faction;
import hedgehogs.strategyGame.javaSwingInterface.generalBuildObjects.AbstractUIObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.Map;

@Component
public class TimedActionListViewFactory extends AbstractUIObjectFactory {
    private TimedActionWaitList timedActionWaitList;

    private JLabel actionListLabel;
    private JList<String> timedActionList;

    @Autowired
    public TimedActionListViewFactory(TimedActionWaitList timedActionWaitList) {
        this.timedActionWaitList = timedActionWaitList;
    }

    /*public JPanel getMainElement() {
        this.mainPanel = new JPanel();
        this.setUpElementsOnMainPanel();
        return this.mainPanel;
    }*/

    /*private void setUpElementsOnMainPanel() {
        this.timedActionList = new JList<>();
        this.writeDataIntoList();
        this.mainPanel.add(timedActionList);
    }*/

    /*public void updateData(){
        this.writeDataIntoList();
    }*/

    @Override
    protected void makeAllMinorElements() {
        this.timedActionList = new JList<>();
        this.addNewElementToPanel(this.timedActionList, 0, 1);

        this.actionListLabel = new JLabel("Actions in process: ");
        this.addNewElementToPanel(this.actionListLabel, 0, 0);
    }

    @Override
    protected void elementContentRefresh() {
        this.writeDataIntoList();
    }

    private void writeDataIntoList() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        this.timedActionWaitList.getCopyOfActions().forEach(
                oneElement -> listModel.addElement(oneElement.getDescriptionString())
        );
        this.timedActionList.setModel(listModel);
    }
}
