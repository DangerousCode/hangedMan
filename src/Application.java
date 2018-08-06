import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        File dictionary = new File("ahorcado.txt");

        do{

            play(getRandomWordDictionary(dictionary));

        } while (repeat);

    }

    private static void play(String word) {

        Scanner sc = new Scanner(System.in);
        String nombre;

        System.out.print("Introduce el nombre del jugador: ");
        nombre = sc.next();

    }

    private static String getRandomWordDictionary(File dictionary) {

        int dicLineCount = 0, rndWordIndx;

        if (dictionary.exists() && dictionary.canRead()) {

            BufferedReader reader = openBufferedReader(dictionary);

            try {
                //We need this to make a Random after to take a word from dictionary
                while (reader.readLine() != null) {
                    dicLineCount++;
                }

                closeBufferedReader(reader);
                reader = openBufferedReader(dictionary);
                rndWordIndx = (int) (Math.random() * dicLineCount) + 1;

                //Read lines before the good one
                for (int i = 0; i < rndWordIndx - 1; i++){

                    reader.readLine();

                }

                return reader.readLine();

            }catch (IOException e){

            }
        }

        return null;
    }

    private static BufferedReader openBufferedReader( File file){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return br;
    }

    private static void closeBufferedReader(BufferedReader bufferedReader){

        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
