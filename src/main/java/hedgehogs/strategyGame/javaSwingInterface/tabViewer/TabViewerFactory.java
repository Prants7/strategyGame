package hedgehogs.strategyGame.javaSwingInterface.tabViewer;

import hedgehogs.strategyGame.javaSwingInterface.generalBuildObjects.AbstractUIObjectFactory;
import hedgehogs.strategyGame.javaSwingInterface.generalBuildObjects.UIObjectFactory;
import org.springframework.stereotype.Component;

@Component
public class TabViewerFactory extends AbstractUIObjectFactory {
    private UIObjectFactory currentElement;

    @Override
    protected void makeAllMinorElements() {
        
    }

    @Override
    protected void elementContentRefresh() {
        if(this.currentElement != null) {
            this.currentElement.refreshElements();
        }
    }

    public void changeScreenTo(UIObjectFactory newElement) {
        if(this.currentElement != null) {
            this.getPanelObject().removeAll();
            this.getPanelObject().repaint();
        }
        this.addNewElementToPanel(newElement.getPanelObject(), 0, 0);
        this.currentElement = newElement;
    }


}
