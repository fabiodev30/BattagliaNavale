import classes.Griglia;
import classes.Punteggio;

import java.util.InputMismatchException;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    static Boolean isFirtsGame = true;

    public static void main(String[] args) {
        Punteggio punteggioGiocatore1 = new Punteggio();
        Punteggio punteggioGiocatore2 = new Punteggio();
        System.out.println("BENVENUTO A BATTAGLIA NAVALE");
        // Inserire nomi giocator
        System.out.println("Inserisci il nome del Giocatore 1:");
        Scanner scannerNomeGiocatore1 = new Scanner(System.in);
        String nomeGiocatore1 = scannerNomeGiocatore1.nextLine();
        System.out.println("Inserisci il nome del Giocatore 2:");
        Scanner scannerNomeGiocatore2 = new Scanner(System.in);
        String nomeGiocatore2 = scannerNomeGiocatore2.nextLine();
        System.out.println("Benvenuti " + nomeGiocatore1 + " e " + nomeGiocatore2);
        while (true) {
            if (isFirtsGame) {
                isFirtsGame = false;
            } else {
                // MENU SCelta 0 - esci dal gioco 1 - ricomincia partita da zero 2 - continua partita
                System.out.println("\nMENU");
                System.out.println("0 - Esci dal gioco");
                System.out.println("1 - Ricomincia partita da zero");
                System.out.println("2 - Continua partita");
                Scanner scanner = new Scanner(System.in);
                // fino a che non inserisce un numero valido ovvero un numero tra 0 e 2
                Boolean isNumeroValido = false;
                while (!isNumeroValido) {
                    try {
                        Integer scelta = scanner.nextInt();
                        if (scelta == 0) {
                            System.out.println("Grazie per aver giocato!");
                            return;
                        } else if (scelta == 1) {
                            punteggioGiocatore1.azzeraPunteggio();
                            punteggioGiocatore2.azzeraPunteggio();
                            System.out.println("Punteggi azzerati!");
                            // reinserimento nomi giocatori
                            System.out.println("Inserisci il nome del Giocatore 1:");
                            Scanner scannerNomeGiocatore1New = new Scanner(System.in);
                            nomeGiocatore1 = scannerNomeGiocatore1New.nextLine();
                            System.out.println("Inserisci il nome del Giocatore 2:");
                            Scanner scannerNomeGiocatore2New = new Scanner(System.in);
                            nomeGiocatore2 = scannerNomeGiocatore2New.nextLine();
                            System.out.println("Benvenuti " + nomeGiocatore1 + " e " + nomeGiocatore2);
                            isNumeroValido = true;
                        } else if (scelta == 2) {
                            isNumeroValido = true;
                        } else {
                            System.out.println("Inserisci un numero valido");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Inserisci un numero valido (0, 1 o 2)");
                        scanner.nextLine();
                        continue;
                    }
                }
            }
            Scanner scanner = new Scanner(System.in);
            System.out.println("Inserire la dimensione della griglia:");
            // fino a che non inserisce un numero valido ovvero un numero maggiore di 0
            Boolean isNumeroValido = false;
            Integer dimensioneGriglia = 0;
            while (!isNumeroValido) {
                try {
                    dimensioneGriglia = scanner.nextInt();
                    if (dimensioneGriglia > 0) {
                        isNumeroValido = true;
                    } else {
                        System.out.println("Inserisci un numero valido (maggiore di 0)");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Inserisci un numero valido (maggiore di 0)");
                    scanner.nextLine();
                    continue;
                }
            }
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
                stampaPunteggi(nomeGiocatore2, punteggioGiocatore2, nomeGiocatore1, punteggioGiocatore1);
            } else {
                System.out.println("Hai vinto Giocatore 1");
                punteggioGiocatore1.aumentaPunteggio();
                // stampa i punteggi
                stampaPunteggi(nomeGiocatore1, punteggioGiocatore1, nomeGiocatore2, punteggioGiocatore2);
            }
        }
    }

    public static void stampaPunteggi(String nomeGiocatore, Punteggio punteggioGiocatore, String nomeGiocatore1, Punteggio punteggioGiocatore1) {
        System.out.println("Punteggio " + nomeGiocatore + ": " + punteggioGiocatore.getPunteggio() + " " + "Punteggio " + nomeGiocatore1 + ": " + punteggioGiocatore1.getPunteggio());
    }
}