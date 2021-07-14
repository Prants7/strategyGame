package hedgehogs.strategyGame.javaSwingInterface.graphicalMap.mouseListener;

import hedgehogs.strategyGame.javaSwingInterface.graphicalMap.mapPainter.MapPainter;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CanvasMouseListener implements MouseListener {
    private MapPainter mapPainter;

    public CanvasMouseListener(MapPainter mapPainter) {
        this.mapPainter = mapPainter;
        mapPainter.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.performClickSearch(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void performClickSearch(int clickX, int clickY) {
        this.mapPainter.performClickSearch(clickX, clickY);
    }
}
