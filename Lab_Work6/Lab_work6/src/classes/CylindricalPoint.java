package classes;

public class CylindricalPoint extends PolarPoint {

    private double z;

    public CylindricalPoint (double rho, double phi, double z) {
        super(rho, phi);
        this.z = z;
    }

    public double getRho() {
        return super.getRho();
    }

    public double getPhi() {
        return super.getPhi();
    }

    public double getZ() {
        return z;
    }

    @Override
    public Point toCartesian() {
        return new Point(super.getRho() * Math.cos(super.getPhi()), super.getRho() * Math.sin(super.getPhi()), z);
    }

}
