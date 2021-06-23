package hedgehogs.strategyGame.javaSwingInterface.provinceView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearLandButtonActionListener implements ActionListener {
    private ProvinceViewFactory provinceViewFactory;

    public ClearLandButtonActionListener(ProvinceViewFactory connectedFactory) {
        this.provinceViewFactory = connectedFactory;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.provinceViewFactory.activateLandClearOnSelectedProvince();
    }
}
