package cs355.Shapes;

import cs355.Helper;
import cs355.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.geom.Point2D;

/**
 * Created by eric on 4/29/15.
 */
public class Circle extends Shape {
    private double radius;

    public Circle(Color color, Point2D origin, double radius) {
        super.setColor(color);
        super.setCenter(origin);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public Point2D calculateTopLeftCorner() {
        return new Point2D.Double(super.getCenter().getX() - radius, super.getCenter().getY() - radius);
    }

    @Override
    public Vector2D getHandleDisplacement() {
        return null;
    }

    @Override
    public List<Square> getHandles() {
        return new ArrayList<Square>();
    }

    @Override
    public boolean isClickInShape(Point2D click) {
        Point2D newClick = Helper.viewToObject(this, click);

        if(Math.pow(newClick.getX(), 2) + Math.pow(newClick.getY(), 2) <= Math.pow(radius, 2)) {
            return true;
        }
        return false;
    }
}
