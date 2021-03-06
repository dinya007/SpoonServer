package ru.mipt.restaurant.server.utils;

import org.junit.Assert;
import org.junit.Test;
import ru.mipt.restaurant.server.domain.Location;

import java.awt.geom.Rectangle2D;

public class LocationHelperTest {

    @Test
    public void testMapToRectangle() throws Exception {
        double topLeftLatitude = 20.0;
        double topLeftLongitude = 0.0;
        double bottomRightLatitude = 0.0;
        double bottomRightLongitude = 10.0;

        Location topLeft = new Location(topLeftLatitude, topLeftLongitude);
        Location bottomRight = new Location(bottomRightLatitude, bottomRightLongitude);

        Rectangle2D rectangle = CoordinateHelper.toRectangle(topLeft, bottomRight);

        Assert.assertTrue(Double.compare(topLeftLongitude, rectangle.getX()) == 0);
        Assert.assertTrue(Double.compare(topLeftLatitude, rectangle.getY()) == 0);
        Assert.assertTrue(Double.compare(10.0, rectangle.getWidth()) == 0);
        Assert.assertTrue(Double.compare(20.0, rectangle.getHeight()) == 0);
    }


    @Test
    public void testIsInside() throws Exception {
        Rectangle2D rectangle = new Rectangle2D.Double(0.0, 20.0, 10.0, 20.0);

        Location insidePoint = new Location(5.0, 5.0);
        Location outsidePoint = new Location(19.0, 11.0);

        Assert.assertTrue(CoordinateHelper.isInside(rectangle, insidePoint));
        Assert.assertFalse(CoordinateHelper.isInside(rectangle, outsidePoint));
    }

}