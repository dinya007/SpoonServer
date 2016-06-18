package ru.mipt.restaurant.server.utils;

import ru.mipt.restaurant.server.domain.Coordinate;

import java.awt.geom.Rectangle2D;

public class CoordinateHelper {

    private CoordinateHelper() {
        throw new AssertionError("This constructor hasn't to be called.");
    }

    public static Rectangle2D mapToRectangle(Coordinate topLeft, Coordinate bottomRight) {
        return new Rectangle2D.Double(topLeft.getLongitude(),
                topLeft.getLatitude(),
                bottomRight.getLongitude() - topLeft.getLongitude(),
                topLeft.getLatitude() - bottomRight.getLatitude());
    }

    public static boolean isInside(Rectangle2D rectangle, Coordinate coordinate) {
        return rectangle.getX() <= coordinate.getLongitude()
                && rectangle.getY() >= coordinate.getLatitude()
                && rectangle.getX() + rectangle.getWidth() >= coordinate.getLongitude()
                && rectangle.getY() - rectangle.getHeight() <= coordinate.getLatitude();
    }

}
