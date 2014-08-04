package model;

import java.util.ArrayList;
import java.util.List;


public class AntSim {
    private static final long DELAY = 1;
    public static final byte WHITE = 0;
    private static final byte BLACK = 1;
    private List<Observer> observers;
    private int width = 300;
    private int height = 300;
    private byte[][] map;
    private Thread runner;
    private List<Ant> ants;

    public AntSim() {
        this.observers = new ArrayList<Observer>();

        initAnt();
        startUpdate();
    }

    private void startUpdate() {
        runner = new Thread(new Runnable() {
            @Override
            public void run() {
                updateLoop();
            }
        });
        runner.start();

    }

    private void updateLoop() {
        while (true) {
            update();
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    private void update() {
        for (Ant ant : ants) {
            if (getMapValue(ant.getPos()) == WHITE) {
                ant.turnLeft();
                setMapValue(ant.getPos(), BLACK);
            } else {
                ant.turnRight();
                setMapValue(ant.getPos(), WHITE);
            }
            move(ant);
        }
        updateObservers();
    }

    private void updateObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    private void move(Ant ant) {
        Position pos = getPosOnDirection(ant.getPos(), ant.getDirection());
        if (inMapUnlimited(pos)) {
            ant.setPos(pos);
        }
    }

    private boolean inMapUnlimited(Position pos) {
        if (pos.getX() < 0) {
            pos.setX(width - 1);
        }
        if (pos.getX() >= width) {
           pos.setX(0);
        }

        if (pos.getY() < 0) {
            pos.setY(height - 1);
        }
        if (pos.getY() >= height) {
            pos.setY(0);
        }

        return true;
    }

    private boolean inMap(Position pos) {
        if (pos.getX() < 0 || pos.getX() >= width) {
            return false;
        }
        if (pos.getY() < 0 || pos.getY() >= height) {
            return false;
        }
        return true;
    }

    private Position getPosOnDirection(Position pos, Direction direction) {
        Position p = pos.copy();
        if (direction.equals(Direction.UP)) {
            p.setY(p.getY() - 1);
        } else if (direction.equals(Direction.DOWN)) {
            p.setY(p.getY() + 1);
        } else if (direction.equals(Direction.LEFT)) {
            p.setX(p.getX() - 1);
        } else if (direction.equals(Direction.RIGHT)) {
            p.setX(p.getX() + 1);
        }
        return p;

    }

    private void setMapValue(Position pos, byte value) {
        map[pos.getX()][pos.getY()] = value;
    }

    private int getMapValue(Position pos) {
        return map[pos.getX()][pos.getY()];
    }

    private void initAnt() {
        map = new byte[width][height];
        ants = new ArrayList<Ant>();
        Ant firstAnt = new Ant((int) width / 2, (int) height / 2);
        ants.add(firstAnt);
        //    addRandomAnts(1000);
    }

    private void addRandomAnts(int count) {
        for (int i = 0; i < count; i++) {
            addAnt((int) (Math.random() * width), (int) (Math.random() * height));
        }
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public byte[][] getMap() {
        return map;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void addAnt(int x, int y) {
        if (inMap(new Position(x, y)))
            ants.add(new Ant(x, y));
    }

    public List<Ant> getAnts() {
        return ants;
    }
}
