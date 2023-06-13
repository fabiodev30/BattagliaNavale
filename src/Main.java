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
            if (isFirtsGame) {
                isFirtsGame = false;
            } else {
                /*System.out.println("\nVuoi giocare un'altra partita? (S/N)");
                Scanner scanner = new Scanner(System.in);
                String risposta = scanner.nextLine();
                if (risposta.equals("N")) {
                    System.out.println("Grazie per aver giocato!");
                    break;
                }*/
                // MENU SCelta 0 - esci dal gioco 1 - ricomincia partita da zero 2 - continua partita
                System.out.println("\nMENU");
                System.out.println("0 - Esci dal gioco");
                System.out.println("1 - Ricomincia partita da zero");
                System.out.println("2 - Continua partita");
                Scanner scanner = new Scanner(System.in);
                // fino a che non inserisce un numero valido ovvero un numero tra 0 e 2
                Boolean isNumeroValido = false;
                while (!isNumeroValido) {
                    Integer scelta = scanner.nextInt();
                    if (scelta == 0) {
                        System.out.println("Grazie per aver giocato!");
                        return;
                    } else if (scelta == 1) {
                        punteggioGiocatore1.azzeraPunteggio();
                        punteggioGiocatore2.azzeraPunteggio();
                        System.out.println("Punteggi azzerati!");
                        isNumeroValido = true;
                    } else if (scelta == 2) {
                        isNumeroValido = true;
                    } else {
                        System.out.println("Inserisci un numero valido");
                    }
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