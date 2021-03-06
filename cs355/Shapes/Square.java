package cs355.Shapes;

import cs355.*;
import cs355.Vector2D;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;

/**
 * Created by eric on 4/29/15.
 */
public class Square extends Shape {
    private double width;

    public Square(Color color, Point2D origin, double width) {
        super.setColor(color);
        super.setCenter(new Point2D.Double(origin.getX() + width / 2, origin.getY() + width / 2));
        this.width = width;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public boolean isClickInHandle(Shape parent, Point2D click) {
        if(parent instanceof Line) {
            Point2D newClick = Helper.viewToWorld(click);
            if(Math.abs(newClick.getX() - this.getCenter().getX()) <= width && Math.abs(newClick.getY() - this.getCenter().getY()) <= width) {
                return true;
            }
            return false;

        } else {
            Point2D newClick = Helper.viewToObject(parent, click);

            newClick.setLocation(0, newClick.getY() - parent.getHandleDisplacement().get(1) - Shape.HANDLE_WIDTH() / 2);

            if(Math.abs(newClick.getX()) <= width && Math.abs(newClick.getY()) <= width) {
                return true;
            }
            return false;
        }
    }

    @Override
    public Point2D calculateTopLeftCorner() {
        return new Point2D.Double(super.getCenter().getX() - this.width/2, super.getCenter().getY() - this.width/2);
    }

    @Override
    public Vector2D getHandleDisplacement() {
        return new Vector2D(- HANDLE_WIDTH() / 2, this.width / 2 + HANDLE_DISPLACEMENT() + HANDLE_WIDTH() / 2);
    }

    @Override
    public List<Square> getHandles() {
        final Vector2D displacement = this.getHandleDisplacement();
        final Point2D.Double origin = new Point2D.Double(this.getCenter().getX() + displacement.get(0), this.getCenter().getY() + displacement.get(1));
        final Square handle = new Square(null, origin, HANDLE_WIDTH());
        handle.setRotationAngle(this.getRotationAngle());
        return Arrays.asList(handle);
    }

    @Override
    public boolean isClickInShape(Point2D click) {
        Point2D newClick = Helper.viewToObject(this, click);

        if(Math.abs(newClick.getX()) <= width / 2 && Math.abs(newClick.getY()) <= width / 2) {
            return true;
        }
        return false;
    }
}
