import StarAlgorithm.Algorithm;
import StarAlgorithm.Bukin;
import StarAlgorithm.Matyasa;
import StarAlgorithm.Schaffer;

public class Main {
    public static void main(String[] args) {
        double average = 0;
        Algorithm bukin = null;
        System.out.println("Bukin №6 function:");
        for (int i = 0; i < 1000; i++){
            bukin = new Bukin();
            average += bukin.calculate();
        }
        System.out.println(bukin);
        System.out.println("average = " + average/1000);

        average = 0;
        Algorithm schaffer = null;
        System.out.println("\nSchaffer №2 function:");
        for (int i = 0; i < 1000; i++){
            schaffer = new Schaffer();
            average += schaffer.calculate();
        }
        System.out.println(schaffer);
        System.out.println("avarage = " + average/1000);

        average = 0;
        Algorithm matyasa = null;
        System.out.println("\nMatyasa function:");
        for (int i = 0; i < 1000; i++){
            matyasa = new Matyasa();
            average += matyasa.calculate();
        }
        System.out.println(matyasa);
        System.out.println("avarage = " + average/1000);
    }
}
