import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MyPanel extends JPanel implements Observer {
    private static final Color ANT_COLOR = Color.RED;
    private byte[][] map;
    private AntSim antSim;
    private int cellSize = 5;

    public void init(AntSim antSim) {
        this.antSim = antSim;
        map = antSim.getMap();
        addMouseListener(new PanelMouseListener());
    }

    @Override
    public void update(AntSim antSim) {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        calcCellSize();
        if (antSim == null) {
            return;
        }
        for (int x = 0; x < antSim.getWidth(); x++) {
            for (int y = 0; y < antSim.getHeight(); y++) {
                if (map[x][y] == 1) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.GRAY);
                }
                g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
            }
        }
        for (Ant ant : antSim.getAnts()) {
            g.setColor(ANT_COLOR);
            g.fillRect(ant.getPos().getX() * cellSize, ant.getPos().getY() * cellSize, cellSize, cellSize);
        }

    }

    private void calcCellSize() {
        cellSize = getParent().getWidth() / antSim.getWidth();
    }


    private class PanelMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getX() / cellSize;
            int y = e.getY() / cellSize;
            antSim.addAnt(x, y);
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
    }
}
