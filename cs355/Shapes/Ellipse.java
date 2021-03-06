package cs355.Shapes;

import cs355.Helper;
import cs355.Vector2D;

import java.awt.*;
import java.util.List;
import java.awt.geom.Point2D;
import java.util.Arrays;

/**
 * Created by eric on 4/29/15.
 */
public class Ellipse extends Shape {
    private double height;
    private double width;

    public Ellipse(Color color, Point2D origin, double height, double width) {
        super.setColor(color);
        super.setCenter(origin);
        this.height = height;
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public Point2D calculateTopLeftCorner() {
        return new Point2D.Double(super.getCenter().getX() - this.width/2, super.getCenter().getY() - this.height/2);
    }

    @Override
    public Vector2D getHandleDisplacement() {
        return new Vector2D(- HANDLE_WIDTH() / 2, this.height / 2 + HANDLE_DISPLACEMENT() + HANDLE_WIDTH() / 2);
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

        if(Math.pow(newClick.getX() / (width / 2), 2) + Math.pow(newClick.getY() / (height / 2), 2) <= 1) {
            return true;
        }
        return false;
    }
}
