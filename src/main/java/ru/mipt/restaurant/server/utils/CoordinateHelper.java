package ru.mipt.restaurant.server.utils;

import ru.mipt.restaurant.server.domain.Location;

import java.awt.geom.Rectangle2D;

public class CoordinateHelper {

    private CoordinateHelper() {
        throw new AssertionError("This constructor hasn't to be called.");
    }

    public static Rectangle2D toRectangle(Location topLeft, Location bottomRight) {
        return new Rectangle2D.Double(topLeft.getLon(),
                topLeft.getLat(),
                bottomRight.getLon() - topLeft.getLon(),
                topLeft.getLat() - bottomRight.getLat());
    }

    public static boolean isInside(Rectangle2D rectangle, Location location) {
        return rectangle.getX() <= location.getLon()
                && rectangle.getY() >= location.getLat()
                && rectangle.getX() + rectangle.getWidth() >= location.getLon()
                && rectangle.getY() - rectangle.getHeight() <= location.getLat();
    }

}
