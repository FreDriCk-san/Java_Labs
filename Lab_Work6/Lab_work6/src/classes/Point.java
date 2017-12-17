package classes;

import com.sun.javafx.geom.Vec3d;

public class Point {

    private double x;
    private double y;
    private double z;

    public Point (double x, double y) {
        this.x = x;
        this.y = y;
        this.z = 0;
    }

    public Point (double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double distanceTo (Point point) {

        return Math.sqrt(Math.pow(x - point.getX(), 2.0) +
                Math.pow(y - point.getY(), 2.0) +
                Math.pow(z - point.getZ(), 2.0));
    }

    public PolarPoint toPolar() {

        return new PolarPoint(
                Math.sqrt(Math.pow(x, 2.0) + Math.pow(y, 2.0)),
                coordinatesToPhi() );
    }

    private double coordinatesToPhi() {

        double phi = 0;
        if (x < 0) {
            phi = Math.atan2(y, x) + Math.PI;
        } else if (x > 0 && y < 0) {
            phi = Math.atan2(y, x) + 2 * Math.PI;
        } else if (x > 0 && y >= 0) {
            phi = Math.atan2(y, x);
        } else if (x == 0 && y > 0) {
            phi = Math.PI / 2;
        } else if (x == 0 && y < 0) {
            phi = 3 * Math.PI / 2;
        }
        return phi;

    }

    public CylindricalPoint toCylindrical() {

        return new CylindricalPoint(
                Math.sqrt(Math.pow(x, 2.0) + Math.pow(y, 2.0)),
                coordinatesToPhi(),
                z );
    }

    public static boolean isOneStraightLine (Point first, Point second, Point third) {

        double x = (third.getX() - first.getX()) * (second.getY() - first.getY()) * (second.getZ() - first.getZ());
        double y = (third.getY() - first.getY()) * (second.getX() - first.getX()) * (second.getZ() - first.getZ());
        double z = (third.getZ() - first.getZ()) * (second.getY() - first.getY()) * (second.getX() - first.getX());

        return x == y && y == z;
    }

    public static boolean isOnePlane (Point first, Point second, Point third, Point fourth) {

        Vec3d firstVector = new Vec3d();
        Vec3d secondVector = new Vec3d();

        firstVector.set(second.getX() - first.getX(), second.getY() - first.getY(), second.getZ() - first.getZ());
        secondVector.set(third.getX() - first.getX(), third.getY() - first.getY(), third.getZ() - first.getZ());

        firstVector.cross(firstVector, secondVector);
        secondVector.set(fourth.getX() - first.getX(), fourth.getY() - first.getY(), fourth.getZ() - first.getZ());

        return firstVector.dot(secondVector) == 0;
    }

}
