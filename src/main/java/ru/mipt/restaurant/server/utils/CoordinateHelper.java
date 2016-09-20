package ru.mipt.restaurant.server.utils;

import ru.mipt.restaurant.server.domain.Coordinates;

import java.awt.geom.Rectangle2D;

public class CoordinateHelper {

    private CoordinateHelper() {
        throw new AssertionError("This constructor hasn't to be called.");
    }

    public static Rectangle2D toRectangle(Coordinates topLeft, Coordinates bottomRight) {
        return new Rectangle2D.Double(topLeft.getLongitude(),
                topLeft.getLatitude(),
                bottomRight.getLongitude() - topLeft.getLongitude(),
                topLeft.getLatitude() - bottomRight.getLatitude());
    }

    public static boolean isInside(Rectangle2D rectangle, Coordinates coordinates) {
        return rectangle.getX() <= coordinates.getLongitude()
                && rectangle.getY() >= coordinates.getLatitude()
                && rectangle.getX() + rectangle.getWidth() >= coordinates.getLongitude()
                && rectangle.getY() - rectangle.getHeight() <= coordinates.getLatitude();
    }

}
