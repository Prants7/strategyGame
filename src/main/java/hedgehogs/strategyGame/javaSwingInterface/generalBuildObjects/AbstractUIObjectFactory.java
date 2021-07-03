package hedgehogs.strategyGame.javaSwingInterface.generalBuildObjects;

import hedgehogs.strategyGame.gameLogic.factions.Faction;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractUIObjectFactory implements UIObjectFactory {
    private JPanel mainPanel;
    private GridBagConstraints layout;

    private void bootUp() {
        this.mainPanel = new JPanel();
        this.layout = new GridBagConstraints();
        this.mainPanel.setLayout(new GridBagLayout());
        this.layout.fill = GridBagConstraints.HORIZONTAL;
        this.makeAllMinorElements();
    }

    @Override
    public JPanel getPanelObject() {
        if(this.mainPanel == null) {
            this.bootUp();
        }
        return this.mainPanel;
    }

    @Override
    public void refreshElements() {
        this.elementContentRefresh();
    }

    protected abstract void makeAllMinorElements();

    protected abstract void elementContentRefresh();

    protected boolean addNewElementToPanel(Component newComponent, int xLocation, int yLocation) {
        this.layout.gridx = xLocation;
        this.layout.gridy = yLocation;
        this.mainPanel.add(newComponent, this.layout);
        return true;
    }
}
