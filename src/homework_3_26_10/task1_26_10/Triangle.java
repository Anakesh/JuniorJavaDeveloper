package homework_3_26_10.task1_26_10;

/**
 * Created by Pavel on 26.10.2018.
 */
public class Triangle extends Figure {
    private float side;
    private float height;

    public Triangle(int x,int y){

    }

    @Override
    float getPerimeter() {
        return 0.5f*side*height;
    }

    @Override
    float getSquare() {
        return 0;
    }

}
