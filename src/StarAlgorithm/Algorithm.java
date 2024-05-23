package StarAlgorithm;

import java.util.*;

public abstract class Algorithm {

    protected double minX;
    protected double maxX;
    protected double minY;
    protected double maxY;
    private List<Point> population = new ArrayList<>();
    private final int populationCount = 1000;
    private final int sizeOfPopulation = 12;
    private double a = 0.5;

    public class Point{
        public double x;
        public double y;
        public double fitness;

        Point(){
            Random rand = new Random();
            x = rand.nextDouble(minX, maxX);
            y = rand.nextDouble(minY, maxY);
            fitness = function(x, y);
        }
        Point(double x, double y){
            this.x = x;
            this.y = y;
            fitness = function(x, y);
        }
    }

    public class Triangle{
        public Point[] p = new Point[3];
        private int best_point;
        private Point centriod;

        public Triangle (Point A, Point B, Point C){
            p[0] = A;
            p[1] = B;
            p[2] = C;
            if (A.fitness < B.fitness) best_point = 0;
            else best_point = 1;
            if (C.fitness < p[best_point].fitness) best_point = 2;

            double x = (A.x + B.x + C.x)/3;
            double y = (A.y + B.y + C.y)/3;
            centriod = new Point(x, y);
        }

        public Point[] transfer(){
            Point[] shiftedPoints = new Point[3];
            double x;
            double y;
            for (int i = 0; i < 3; i++){
                if (i == best_point){
                    x = (1+a)*p[best_point].x - a*centriod.x;
                    y = (1+a)*p[best_point].y - a*centriod.y;
                }
                else{
                    x = (p[best_point].x + a*p[i].x)/(1 + a);
                    y = (p[best_point].y + a*p[i].y)/(1 + a);
                }
                shiftedPoints[i] = new Point(x, y);
                inBoundary(shiftedPoints[i]);
            }
            return  shiftedPoints;
        }

        public Point[] rotate(){
            Point[] rotatedPoints = new Point[2];
            double x;
            double y;
            double b;
            Random rand = new Random();
            for (int i = 0; i < 2; i++){
                b = Math.PI * rand.nextDouble(0, 360)/180;
                if (i == best_point){
                    x = p[2].x + (p[2].x - p[best_point].x) * Math.cos(b) - (p[2].y - p[best_point].y * Math.sin(b));
                    y = p[2].y + (p[2].x - p[best_point].x) * Math.sin(b) - (p[2].y - p[best_point].y * Math.cos(b));
                }
                else{
                    x = p[i].x + (p[i].x - p[best_point].x) * Math.cos(b) - (p[i].y - p[best_point].y * Math.sin(b));
                    y = p[i].y + (p[i].x - p[best_point].x) * Math.sin(b) - (p[i].y - p[best_point].y * Math.cos(b));
                }
                rotatedPoints[i] = new Point(x, y);
                inBoundary(rotatedPoints[i]);
            }
            return rotatedPoints;
        }

        public Point[] rotateCenter(){
            Point[] rotatedPoints = new Point[3];
            double x;
            double y;
            double b;
            Random rand = new Random();
            for (int i = 0; i < 3; i++){
                b = Math.PI * rand.nextDouble(0, 360)/180;
                x = (p[i].x - centriod.x) * Math.cos(b) - (p[i].y - centriod.y * Math.sin(b));
                y = (p[i].x - centriod.x) * Math.sin(b) - (p[i].y - centriod.y * Math.cos(b));
                rotatedPoints[i] = new Point(x, y);
                inBoundary(rotatedPoints[i]);
            }
            return rotatedPoints;
        }

    }
    public abstract double function(double x, double y);

    public double function(Point p){
        return function(p.x, p.y);
    }

    private Triangle[] toTriangle(){
        Random rand = new Random();
        int a, b, c;
        Point A, B, C;
        Triangle[] triangles = new Triangle[sizeOfPopulation/3];
        for (int i = 0; i < sizeOfPopulation/3; i++){
            do{
                a = rand.nextInt(0, sizeOfPopulation);
                b = rand.nextInt(0, sizeOfPopulation);
                c = rand.nextInt(0, sizeOfPopulation);
            }
            while (a == b || a == c || b == c);
            A = population.get(a);
            B = population.get(b);
            C = population.get(c);
            triangles[i] = new Triangle(A, B, C);
        }
        return triangles;
    }

    private void inBoundary(Point p){
        while (p.x > maxX){
            p.x = minX + (p.x - maxX);
        }
        while (p.y > maxY){
            p.y = minY + (p.y - maxY);
        }
        while (p.x < minX){
            p.x = maxX - (minX - p.x);
        }
        while (p.y < minY){
            p.y = maxY - (minY - p.y);
        }
    }
    public double calculate(){
        for ( int i = 0; i < sizeOfPopulation; i++){
            population.add(new Point());
        }
        Triangle[] triangles;
        for (int i = 0; i < populationCount; i++){
            triangles = toTriangle();
            for (int t = 0; t < triangles.length; t++){
                population.addAll(Arrays.asList(triangles[t].transfer()));
                population.addAll(Arrays.asList(triangles[t].rotate()));
                population.addAll(Arrays.asList(triangles[t].rotateCenter()));
            }
            population.sort(new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                    if (o1.fitness == o2.fitness) return 0;
                    return o1.fitness < o2.fitness ? -1 : 1;
                }
            });
            while (population.size() != sizeOfPopulation){
                population.remove(sizeOfPopulation);
            }
            a *= 0.99;
        }
        return population.get(0).fitness;
    }

    @Override
    public String toString() {
        try {
            return "Best point: x = " + population.get(0).x + "; y = " + population.get(0).y + "; fitness: " + population.get(0).fitness;
        }catch (Exception e){
            return "Ready to start calculation";
        }
    }
}
