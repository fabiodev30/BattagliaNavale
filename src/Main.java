import classes.Griglia;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci la dimensione della griglia:");
        Integer dimensioneGriglia = scanner.nextInt();
        Griglia griglia = new Griglia(dimensioneGriglia);
        griglia.start();
    }
}