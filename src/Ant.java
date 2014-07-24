
public class Ant {
    private Position pos;
    private Direction direction;

    public Ant(int x, int y) {
        setPos(new Position(x, y));
        setDirection(Direction.DOWN);
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public void turnLeft() {
        if (direction.equals(Direction.UP)) {
            direction = Direction.LEFT;
        } else if (direction.equals(Direction.LEFT)) {
            direction = Direction.DOWN;
        } else if (direction.equals(Direction.DOWN)) {
            direction = Direction.RIGHT;
        } else {
            direction = Direction.UP;
        }
    }

    public void turnRight() {
        if (direction.equals(Direction.UP)) {
            direction = Direction.RIGHT;
        } else if (direction.equals(Direction.LEFT)) {
            direction = Direction.UP;
        } else if (direction.equals(Direction.DOWN)) {
            direction = Direction.LEFT;
        } else {
            direction = Direction.DOWN;
        }
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
