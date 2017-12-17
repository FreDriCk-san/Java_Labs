package classes;

public class PolarPoint {

    private double rho;
    private double phi;

    public PolarPoint(double rho, double phi) {

        this.rho = rho;
        this.phi = phi;
    }

    public double getRho() {
        return rho;
    }

    public double getPhi() {
        return phi;
    }

    public Point toCartesian() {
        return new Point(rho * Math.cos(phi), rho * Math.sin(phi));
    }

}
