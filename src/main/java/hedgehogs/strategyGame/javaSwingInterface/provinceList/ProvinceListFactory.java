package hedgehogs.strategyGame.javaSwingInterface.provinceList;

import hedgehogs.strategyGame.gameLogic.land.Province;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.List;

@Component
public class ProvinceListFactory {

    public JList<String> giveTestList() {
        DefaultListModel<String> l1 = new DefaultListModel<>();
        l1.addElement("Item1");
        l1.addElement("Item2");
        l1.addElement("Item3");
        l1.addElement("Item4");
        JList<String> list = new JList<>(l1);
        list.setBounds(100,100, 75,75);
        return list;
    }

    public JList<Province> giveProvinceList(List<Province> provinces) {
        DefaultListModel<Province> l1 = new DefaultListModel<>();
        for(Province oneProvince: provinces) {
            l1.addElement(oneProvince);
        }
        JList<Province> list = new JList<>(l1);
        return list;
    }
}
