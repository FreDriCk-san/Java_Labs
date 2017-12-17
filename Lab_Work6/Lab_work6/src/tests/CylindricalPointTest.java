package tests;

import classes.CylindricalPoint;
import classes.Point;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class CylindricalPointTest {

    @Test
    public void getRho() {

        CylindricalPoint cylindricalPoint = new CylindricalPoint(12,23,34);
        Assertions.assertEquals(12, cylindricalPoint.getRho());
    }

    @Test
    public void getPhi() {

        CylindricalPoint cylindricalPoint = new CylindricalPoint(12,23,34);
        Assertions.assertEquals(23, cylindricalPoint.getPhi());
    }

    @Test
    public void getZ() {

        CylindricalPoint cylindricalPoint = new CylindricalPoint(12,23,34);
        Assertions.assertEquals(34, cylindricalPoint.getZ());
    }

    @Test
    public void toCartesian() {

        CylindricalPoint cylindricalPoint = new CylindricalPoint(13, 22.6, 0);
        Point point = cylindricalPoint.toCartesian();
        Assertions.assertEquals(-10, (int) point.getX());
        Assertions.assertEquals(-7, (int) point.getY());
    }

}