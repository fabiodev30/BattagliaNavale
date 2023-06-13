package classes;

import java.util.Objects;

public class Coordinate {
    private Integer x;
    private Integer y;

    public Coordinate(Integer x, Integer y) {
        this.x = x;
        this.y = y;

    }

    public Coordinate() {
        this.x = 0;
        this.y = 0;

    }

    public Integer getX() {
        return this.x;

    }

    public Integer getY() {
        return this.y;

    }

    public void setX(Integer x) {
        this.x = x;

    }

    public void setY(Integer y) {
        this.y = y;

    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";

    }
}
