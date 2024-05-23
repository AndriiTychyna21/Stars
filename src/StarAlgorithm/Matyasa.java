package StarAlgorithm;

public class Matyasa extends Algorithm{
    public Matyasa(){
        this.minX = -10;
        this.maxX = 10;
        this.minY = -10;
        this.maxY = 10;
    }

    @Override
    public double function(double x, double y) {
        return 0.26 * (x * x + y * y) - 0.48 * x * y;
    }
}
