import model.AntSim;
import view.MyPanel;

import javax.swing.*;


public class Launcher {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        JFrame frame = new JFrame();
        MyPanel panel = new MyPanel();
        AntSim antSim = new AntSim();
        panel.init(antSim);
        antSim.addObserver(panel);
     //   panel.setSize();
        frame.setSize(WIDTH, HEIGHT);
        frame.add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Ant Langton");
        frame.show();
    }
}
