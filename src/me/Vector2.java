package me;

public class Vector2 {
    private int x;
    private int y;

    public Vector2(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Vector2 getVector(){
        return new Vector2(x,y);
    }
}
