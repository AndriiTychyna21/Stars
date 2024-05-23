package StarAlgorithm;

public class Schaffer extends Algorithm{
    public Schaffer(){
        this.minX = -100;
        this.maxX = 100;
        this.minY = -100;
        this.maxY = 100;
    }
    @Override
    public double function(double x, double y){
        double number1 = x * x - y * y;
        number1 = Math.pow(Math.sin(number1), 2);
        double number2 = x * x + y * y;
        number2 = Math.pow(1 + 0.001 * number2, 2);
        return 0.5 + (number1 - 0.5)/number2;
    }
}
