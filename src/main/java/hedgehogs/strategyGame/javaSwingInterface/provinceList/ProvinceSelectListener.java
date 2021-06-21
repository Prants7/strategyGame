package hedgehogs.strategyGame.javaSwingInterface.provinceList;

import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.javaSwingInterface.mainWindowBooter.MainWindowFactory;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProvinceSelectListener implements ListSelectionListener {
    private JList<Province> list;
    private MainWindowFactory mainWindowFactory;

    public ProvinceSelectListener(JList<Province> targetList, MainWindowFactory targetFactory) {
        this.list = targetList;
        this.mainWindowFactory = targetFactory;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        Province selectedProvince = this.list.getSelectedValue();
        if(selectedProvince == null) {
            return;
        }
        this.mainWindowFactory.openProvinceView(selectedProvince);
    }
}
