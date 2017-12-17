package tests;

import classes.Point;
import classes.PolarPoint;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class PolarPointTest {

    @Test
    public void getRho() {

        PolarPoint polarPoint = new PolarPoint(12,23);
        Assertions.assertEquals(12, polarPoint.getRho());
    }

    @Test
    public void getPhi() {

        PolarPoint polarPoint = new PolarPoint(12,23);
        Assertions.assertEquals(23, polarPoint.getPhi());
    }

    @Test
    public void toCartesian() {

        PolarPoint polarPoint = new PolarPoint(13, 22.6);
        Point point = polarPoint.toCartesian();
        Assertions.assertEquals(-10, (int) point.getX());
        Assertions.assertEquals(-7, (int) point.getY());
    }

}