package homework_3_26_10.task1_26_10;

import java.util.List;

/**
 * Created by Pavel on 26.10.2018.
 */
public abstract class Figure {
    private List<Point> coordinates;

    abstract float getPerimeter();
    abstract float getSquare();
}

class Point{
    int x;
    int y;
}