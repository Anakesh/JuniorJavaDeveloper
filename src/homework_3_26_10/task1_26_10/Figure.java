package homework_3_26_10.task1_26_10;

/**
 * Created by Pavel on 26.10.2018.
 */
public abstract class Figure {
    private int x;
    private int y;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Figure(int x, int y){
        this.x = x;
        this.y = y;
    }

    abstract float getPerimeter();
    abstract float getSquare();
}
