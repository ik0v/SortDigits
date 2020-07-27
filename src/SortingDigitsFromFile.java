import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SortingDigitsFromFile  {
    public static void main(String[] args) throws IOException {

        String input1 = "input1.txt";
        String input2 = "input2.txt";
        String output = "output.dat";

        FileReader fReader1 = new FileReader(input1);
        FileReader fReader2 = new FileReader(input2);
        textFWriter fWriter = new textFWriter(output);
        Scanner scanner1 = new Scanner(fReader1);
        Scanner scanner2 = new Scanner(fReader2);

        while(scanner1.hasNextInt()) {
            fWriter.writeNr(scanner1.nextInt());
        }

        while (scanner2.hasNextInt()) {
            fWriter.writeNr(scanner2.nextInt());
        }

        fWriter.printNumbers();
        System.out.println("\n" + fWriter.getPointer() + " numbers added");
        

    }

}
