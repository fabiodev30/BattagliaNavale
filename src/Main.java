import classes.Griglia;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci la dimensione della griglia:");
        Integer dimensioneGriglia = scanner.nextInt();
        // Creazione della griglia giocatore 1
        System.out.println("\nInserinamento Navi Griglia Giocatore 1");
        Griglia grigliaGiocatore1 = new Griglia(dimensioneGriglia);
        grigliaGiocatore1.start();
        // creazione della griglia giocatore 2
        System.out.println("\nInserinamento Navi Griglia Giocatore 2");
        Griglia grigliaGiocatore2 = new Griglia(dimensioneGriglia);
        grigliaGiocatore2.start();

        // Inizio gioco
        Boolean turnoGiocatore1 = true;
        // Inizia il gioco finchè uno dei due giocatori non ha vinto
        while (!grigliaGiocatore1.checkVittoria() && !grigliaGiocatore2.checkVittoria()) {
            System.out.println();
            if (turnoGiocatore1) {
                System.out.println("Turno del Giocatore 1");
                grigliaGiocatore2.colpisciNave();
                grigliaGiocatore2.stampaGriglia();
            } else {
                System.out.println("Turno del Giocatore 2");
                grigliaGiocatore1.colpisciNave();
                grigliaGiocatore1.stampaGriglia();
            }
            turnoGiocatore1 = !turnoGiocatore1;
        }
        if (grigliaGiocatore1.checkVittoria()) {
            System.out.println("Hai vinto Giocatore 2");
        } else {
            System.out.println("Hai vinto Giocatore 1");
        }
    }
}