import classes.Griglia;
import classes.Punteggio;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    static Boolean isFirtsGame = true;
    public static void main(String[] args) {
        Punteggio punteggioGiocatore1 = new Punteggio();
        Punteggio punteggioGiocatore2 = new Punteggio();
        System.out.println("BENVENUTO A BATTAGLIA NAVALE");
        while (true) {
            // COSA FARE:
            // CONTINUARE A GIOCARE O TERMINARE IL GIOCO
            // Inserimento dimensione griglia
            if (isFirtsGame) {
                isFirtsGame = false;
            } else {
                System.out.println("\nVuoi giocare un'altra partita? (S/N)");
                Scanner scanner = new Scanner(System.in);
                String risposta = scanner.nextLine();
                if (risposta.equals("N")) {
                    System.out.println("Grazie per aver giocato!");
                    break;
                }
            }
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
                punteggioGiocatore2.aumentaPunteggio();
                // stampa i punteggi
                System.out.println("Punteggio Giocatore 2: " + punteggioGiocatore2.getPunteggio()+" "+"Punteggio Giocatore 1: " + punteggioGiocatore1.getPunteggio());
            } else {
                System.out.println("Hai vinto Giocatore 1");
                punteggioGiocatore1.aumentaPunteggio();
                System.out.println("Punteggio Giocatore 1: " + punteggioGiocatore1.getPunteggio()+" "+"Punteggio Giocatore 2: " + punteggioGiocatore2.getPunteggio());
            }
        }
    }
}