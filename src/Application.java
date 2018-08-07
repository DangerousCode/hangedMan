import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        File dictionary = new File("C:/Users/Administrador/IdeaProjects/ahorcado/src/resources/dictionary.txt");
        boolean repeat = false;
        do {

            repeat = play(getRandomWordDictionary(dictionary));

        } while (repeat);

    }

    private static boolean play(String word) {

        //Number of chars will be showed
        int numberOfCharsToShow, livesRemaining = 5;
        char[] wordArray;
        boolean winner = false;
        Scanner sc = new Scanner(System.in);
        String nombre;

        System.out.print("What's your name?: ");
        nombre = sc.next();
        System.out.println("Let's play");

        //La funcionalidad requerida es que muestre maximo la mitad de las letras de la palabra en orden aleatorio.
        numberOfCharsToShow = word.length() / 2;
        wordArray = new char[word.length()];

        for (int i = 0; i < numberOfCharsToShow; i++) {

            //Gets a random position to put char
            int random = (int) (Math.random() * word.length());
            wordArray[random] = word.charAt(random);
        }

        do {

            System.out.println("Current lives: " + livesRemaining);
            drawCharArrayAndHM(wordArray, livesRemaining);
            System.out.print("Write a position (from 1 to " + (word.length()) + "): ");
            int positionToWrite = sc.nextInt();
            System.out.print("Insert the char to put into the position: ");
            char charToWrite = sc.next().charAt(0);

            if (word.charAt(positionToWrite - 1) == charToWrite) {
                wordArray[positionToWrite - 1] = charToWrite;
            } else {
                livesRemaining--;
            }

            if (word.equals(new String(wordArray))) {
                winner = true;
            }

        } while (livesRemaining != 0 && !winner);

        if (!winner) {
            System.out.println("You lose :(");
        } else {
            System.out.println("You win!!!! :D");
        }

        System.out.print("Do you want to play again?");
        return sc.nextBoolean();
    }

    private static void drawCharArrayAndHM(char[] wordArray, int livesRemaining) {

        if (livesRemaining > 0)
            System.out.println("  O");
        if (livesRemaining > 1)
            System.out.print(" /");
        if (livesRemaining > 2)
            System.out.print("|");
        if (livesRemaining > 3)
            System.out.println("\\");
        if (livesRemaining > 4)
            System.out.println("  /\\");

        for (int i = 0; i < wordArray.length; i++) {
            if (wordArray[i] == 0) {
                System.out.print("_ ");
            } else {
                System.out.print(wordArray[i]);
            }
        }
        System.out.println("\n");
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
                for (int i = 0; i < rndWordIndx - 1; i++) {

                    reader.readLine();

                }

                return reader.readLine();

            } catch (IOException e) {

            }
        }

        return null;
    }

    private static BufferedReader openBufferedReader(File file) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return br;
    }

    private static void closeBufferedReader(BufferedReader bufferedReader) {

        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
