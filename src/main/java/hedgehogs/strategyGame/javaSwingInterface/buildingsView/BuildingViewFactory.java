package hedgehogs.strategyGame.javaSwingInterface.buildingsView;

import hedgehogs.strategyGame.gameLogic.land.Province;
import hedgehogs.strategyGame.javaSwingInterface.generalBuildObjects.AbstractUIObjectFactory;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class BuildingViewFactory extends AbstractUIObjectFactory {
    private Province lastSelectedProvince;
    private JLabel provinceNameLabel;

    @Override
    protected void makeAllMinorElements() {
        this.makeNameLabel();
    }

    private void makeNameLabel() {
        this.provinceNameLabel = new JLabel("Province: ");
        this.addNewElementToPanel(this.provinceNameLabel, 0, 0);
    }

    @Override
    protected void elementContentRefresh() {
        this.writeNameLabel();
    }

    private void writeNameLabel() {
        this.provinceNameLabel.setText("Province: "+this.lastSelectedProvince.getProvinceName());
    }

    public void openViewForProvince(Province province) {
        if(province == null) {
            return;
        }
        this.lastSelectedProvince = province;
        this.elementContentRefresh();
    }
}
