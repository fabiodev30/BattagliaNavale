package classes;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public Coordinate() {
        this.x = 0;
        this.y = 0;

    }

    public int getX() {
        return this.x;

    }

    public int getY() {
        return this.y;

    }

    public void setX(int x) {
        this.x = x;

    }

    public void setY(int y) {
        this.y = y;

    }

    public boolean equals(Coordinate c) {
        return this.x == c.getX() && this.y == c.getY();

    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";

    }
}
