package hedgehogs.strategyGame.javaSwingInterface.provinceView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PurchaseLandButtonActionListener implements ActionListener {
    private ProvinceViewFactory provinceViewFactory;

    public PurchaseLandButtonActionListener(ProvinceViewFactory connectedFactory) {
        this.provinceViewFactory = connectedFactory;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.provinceViewFactory.activateLandPurchaseOnSelectedProvince();
    }
}
