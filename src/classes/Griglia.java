package classes;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Griglia {
    private Boolean isPrimoGiro;
    private Integer dimensioneGriglia;
    private Integer[][] griglia;
    private Integer nNavi;
    private Integer nNaviRimaste;
    private Scanner scanner;
    private List<Nave> navi;

    public Griglia(Integer dimensioneGriglia) {
        this.dimensioneGriglia = dimensioneGriglia;
        this.griglia = new Integer[dimensioneGriglia][dimensioneGriglia];
        this.nNavi = dimensioneGriglia * dimensioneGriglia / 3;
        this.nNaviRimaste = nNavi;
        this.scanner = new Scanner(System.in);
        this.navi = new ArrayList<>();
        this.isPrimoGiro = true;
    }

    // Crea griglia vuota con 0
    private void creaGriglia() {
        for (int i = 0; i < dimensioneGriglia; i++) {
            for (int j = 0; j < dimensioneGriglia; j++) {
                griglia[i][j] = 0;
            }
        }
    }

    // Creazione nave
    private Nave creaNave() {
        boolean naveCreata = false;
        Nave nave = null;
        List<Coordinate> coordinate = null;

        while (!naveCreata) {
            coordinate = new ArrayList<>();
            nave = new Nave();

            // Inserimento nome nave
            System.out.println("Inserisci il nome della nave:");
            scanner = new Scanner(System.in);
            String nome = scanner.nextLine();
            nave.setNome(nome);


            Integer lunghezza = 0;
            scanner = new Scanner(System.in);
            while (lunghezza <= 0 || lunghezza > dimensioneGriglia) {// Inserimento lunghezza nave
                System.out.println("\nInserisci la lunghezza della nave:");
                try {
                    lunghezza = scanner.nextInt();
                    if (lunghezza > dimensioneGriglia) {
                        System.out.println("La lunghezza della nave è maggiore della dimensione della griglia. Riprova.");
                    } else if (lunghezza <= 0) {
                        System.out.println("La lunghezza della nave deve essere maggiore di 0. Riprova.");
                    }
                }catch (InputMismatchException e){
                    System.out.println("La lunghezza della nave deve essere un numero. Riprova.");
                    scanner.nextLine(); // Per consumare l'input non valido e passare alla riga successiva
                    continue; // Torna al ciclo 'while'
                }
            }
            nave.setLunghezza(lunghezza);


            scanner = new Scanner(System.in);
            // Inserimento coordinate x deve essere un numero compreso tra 0 compreso e la dimensione della griglia
            Integer x = -1;
            while (x < 0 || x >= dimensioneGriglia) {
                // Inserimento coordinate nave
                System.out.println("Inserisci la coordinata x di partenza della nave:");
                try {
                    x = scanner.nextInt();
                    if (x < 0 || x >= dimensioneGriglia) {
                        System.out.println("La coordinata x deve essere compresa tra 0 e " + (dimensioneGriglia - 1) + ". Riprova.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("La coordinata x deve essere un numero. Riprova.");
                    scanner.nextLine(); // Per consumare l'input non valido e passare alla riga successiva
                    continue; // Torna al ciclo 'while'
                }
            }
            scanner = new Scanner(System.in);
            Integer y = -1;
            while (y < 0 || y >= dimensioneGriglia) {
                System.out.println("Inserisci la coordinata y di partenza della nave:");
                try {
                    y = scanner.nextInt();
                    if (y < 0 || y >= dimensioneGriglia) {
                        System.out.println("La coordinata x deve essere compresa tra 0 e " + (dimensioneGriglia - 1) + ". Riprova.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("La coordinata y deve essere un numero. Riprova.");
                    scanner.nextLine(); // Per consumare l'input non valido e passare alla riga successiva
                    continue; // Torna al ciclo 'while'
                }
            }
            Coordinate c = new Coordinate(x, y);

            // Controllo se la nave è fuori dalla griglia
            if (controlCoordinate(x, y)) {
                System.out.println("La nave è fuori dalla griglia o sovrapposta ad un'altra nave");
                continue;
            }
            // se passa il controllo, aggiungo la coordinata alla nave
            coordinate.add(c);
            boolean isOutOrAlreadyExist = false;

            if (lunghezza > 1) {
                System.out.println("Inserisci l'orientamento della nave (1: verticale, 2: orizzontale):");
                scanner = new Scanner(System.in);
                int orientamento = scanner.nextInt();

                for (int i = 0; i < lunghezza; i++) {
                    Coordinate c1;
                    if (orientamento == 1) {
                        if (controlCoordinate(x + i, y)) {
                            System.out.println("La nave è fuori dalla griglia o sovrapposta ad un'altra nave");
                            isOutOrAlreadyExist = true;
                            break;
                        }
                        c1 = new Coordinate(x + i, y);
                    } else {
                        if (controlCoordinate(x, y + i)) {
                            System.out.println("La nave è fuori dalla griglia o sovrapposta ad un'altra nave");
                            isOutOrAlreadyExist = true;
                            break;
                        }
                        c1 = new Coordinate(x, y + i);
                    }
                    coordinate.add(c1);
                }
            }

            if (isOutOrAlreadyExist) {
                continue;
            }
            // Controllo se la nave è sovrapposta ad un'altra nave
            Boolean posizioneOccupata = coordinate.stream().anyMatch(nCoordinate -> griglia[nCoordinate.getX()][nCoordinate.getY()] == 1);
            if (!posizioneOccupata) {
                naveCreata = true;
            } else {
                System.out.println("Posizione non valida. Riprova.");
            }
        }

        // Inserisci coordinate nella nave
        nave.setCoordinate(coordinate);
        return nave;
    }

    // Questa funzione inserisce le navi fino a che non sono finite
    private void inserisciNavi() {
        for (int i = 0; i < nNavi; i++) {
            System.out.println("Navi rimaste da inserire: " + nNaviRimaste);
            if (isPrimoGiro) {
                System.out.println("\nPartenza del gioco. Inserisci la prima nave.");
                isPrimoGiro = false;
            } else {
                // chiedere all'utente di inserire la nave se vuole
                System.out.println("Vuoi inserire una nave? (1: si, 2: no)");
                Integer scelta = scanner.nextInt();
                if (scelta == 2) {
                    break;
                }
            }
            Nave nave = creaNave();
            navi.add(nave);
            nNaviRimaste--;
            // Stampa nave inserita con nome
            System.out.println("Nave inserita: " + nave.getNome());
            List<Coordinate> coordinate = nave.getCoordinate();
            for (Coordinate c : coordinate) {
                griglia[c.getX()][c.getY()] = 1;
            }
        }
    }

    // Stampa griglia
    public void stampaGriglia() {
        for (int i = 0; i < dimensioneGriglia; i++) {
            for (int j = 0; j < dimensioneGriglia; j++) {
                System.out.print(griglia[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Colpisci Nave
    public void colpisciNave() {
        System.out.println("Inserisci la coordinata x di colpo:");
        Integer x = scanner.nextInt();
        System.out.println("Inserisci la coordinata y di colpo:");
        Integer y = scanner.nextInt();
        Coordinate c = new Coordinate(x, y);
        if (griglia[c.getX()][c.getY()] == 1) {
            System.out.println("Hai colpito una nave!");
            griglia[c.getX()][c.getY()] = 0;
            // Verifica se la nave è affondata
            Nave naveAffondata = isNaveAffondata();
            if (naveAffondata != null) {
                System.out.println("Hai affondato la nave " + naveAffondata.getNome());
            }
        } else {
            System.out.println("Hai colpito l'acqua!");
        }
    }

    // Verifica se la nave è affondata
    private Nave isNaveAffondata() {
        for (Nave nave : navi) {
            // Verifica se tutte le posizioni della nave sono state colpite
            Boolean tuttePosizioniColpite = nave.getCoordinate().stream().allMatch(coordinate -> griglia[coordinate.getX()][coordinate.getY()] == 0);
            if (tuttePosizioniColpite) {
                return nave;
            }
        }
        return null;
    }

    public boolean checkVittoria() {
        for (int i = 0; i < dimensioneGriglia; i++) {
            for (int j = 0; j < dimensioneGriglia; j++) {
                if (griglia[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    // Check se la x e la y che stiamo inserendo nella griglia non sono già state inserite
    private boolean checkCoordinate(int x, int y) {
        return griglia[x][y] == 0;
    }

    private boolean controlCoordinate(Integer x, Integer y) {
        return !checkCoordinate(x, y) || !controlloCoordinataDimensione(x, y);
    }

    private Boolean controlloCoordinataDimensione(Integer x, Integer y) {
        if (x >= dimensioneGriglia || y >= dimensioneGriglia) {
            System.out.println("La nave non può essere inserita in questa posizione. Riprova.");
            return false;
        }
        return true;
    }

    // Start
    public void start() {
        // Crea griglia e inserisci navi
        creaGriglia();
        inserisciNavi();
        stampaGriglia();
    }
}
