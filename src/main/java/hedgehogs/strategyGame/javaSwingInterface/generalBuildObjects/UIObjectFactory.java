package hedgehogs.strategyGame.javaSwingInterface.generalBuildObjects;

import hedgehogs.strategyGame.gameLogic.factions.Faction;

import javax.swing.*;

public interface UIObjectFactory {

    public JPanel getPanelObject();

    public void refreshElements();
}
