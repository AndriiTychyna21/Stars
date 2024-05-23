package StarAlgorithm;

public class Bukin extends Algorithm{

    public Bukin(){
        this.minX = -15;
        this.maxX = -5;
        this.minY = -3;
        this.maxY = 3;
    }
    @Override
    public double function(double x, double y){
        double number1 = y - 0.01 * Math.pow(x, 2);
        double number2 = x + 10;
        number1 = (number1 < 0) ? -number1 : number1;
        number2 = (number2 < 0) ? -number2 : number2;
        return 100 * Math.sqrt(number1) + 0.01 * number2;
    }
}