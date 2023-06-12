package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Griglia {
    private Integer dimensioneGriglia;
    private int[][] griglia;
    private Integer nNavi;
    private Scanner scanner;
    private List<Nave> navi;

    public Griglia(Integer dimensioneGriglia) {
        this.dimensioneGriglia = dimensioneGriglia;
        this.griglia = new int[dimensioneGriglia][dimensioneGriglia];
        this.nNavi = dimensioneGriglia * dimensioneGriglia / 3;
        this.scanner = new Scanner(System.in);
        this.navi = new ArrayList<>();
    }

    // Crea griglia vuota con 0
    public void creaGriglia() {
        for (int i = 0; i < dimensioneGriglia; i++) {
            for (int j = 0; j < dimensioneGriglia; j++) {
                griglia[i][j] = 0;
            }
        }
    }

    // Creazione nave
    public Nave creaNave() {
        boolean naveCreata = false;
        Nave nave = null;
        List<Coordinate> coordinate = null;

        while (!naveCreata) {
            nave = new Nave();
            System.out.println("Inserisci il nome della nave:");
            String nome = scanner.nextLine();
            nave.setNome(nome);

            System.out.println("Inserisci la lunghezza della nave:");
            String lunghezzaInput = scanner.nextLine();
            int lunghezza;
            try {
                lunghezza = Integer.parseInt(lunghezzaInput);
                if (lunghezza <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Lunghezza non valida. Riprova.");
                System.out.println("La Lunghezza deve essere un numero intero e maggiore di 0.");
                continue;
            }
            nave.setLunghezza(lunghezza);

            System.out.println("Inserisci la coordinata x di partenza della nave:");
            int x = scanner.nextInt();
            System.out.println("Inserisci la coordinata y di partenza della nave:");
            int y = scanner.nextInt();
            Coordinate c = new Coordinate(x, y);
            coordinate = new ArrayList<>();
            coordinate.add(c);

            if (lunghezza > 1) {
                System.out.println("Inserisci l'orientamento della nave (1: verticale, 2: orizzontale):");
                int orientamento = scanner.nextInt();
                if (orientamento == 1) {
                    for (int i = 1; i < lunghezza; i++) {
                        Coordinate c1 = new Coordinate(x + i, y);
                        coordinate.add(c1);
                    }
                } else {
                    for (int i = 1; i < lunghezza; i++) {
                        Coordinate c1 = new Coordinate(x, y + i);
                        coordinate.add(c1);
                    }
                }
            }

            boolean posizioneOccupata = coordinate.stream().anyMatch(nCoordinate -> griglia[nCoordinate.getX()][nCoordinate.getY()] == 1);
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
    public void inserisciNavi() {
        for (int i = 0; i < nNavi; i++) {
            System.out.println("Inserisci Nuova nave");
            Nave nave = creaNave();
            navi.add(nave);

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
        int x = scanner.nextInt();
        System.out.println("Inserisci la coordinata y di colpo:");
        int y = scanner.nextInt();
        Coordinate c = new Coordinate(x, y);
        if (griglia[c.getX()][c.getY()] == 1) {
            System.out.println("Hai colpito una nave!");
            griglia[c.getX()][c.getY()] = 0;
            // Verifica se la nave è affondata
            Nave naveAffondata = isNaveAffondata(x, y);
            if (naveAffondata != null) {
                System.out.println("Hai affondato la nave " + naveAffondata.getNome());
            }

        } else {
            System.out.println("Hai colpito l'acqua!");
        }
    }

    // Verifica se la nave è affondata
    public Nave isNaveAffondata(Integer x, Integer y) {
        for (Nave nave : navi) {
            boolean tuttePosizioniColpite = nave.getCoordinate().stream()
                    .allMatch(coordinate -> griglia[coordinate.getX()][coordinate.getY()] == 0);
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

    // Start
    public void start() {
        creaGriglia();
        inserisciNavi();
        stampaGriglia();
        while (true) {
            colpisciNave();
            stampaGriglia();
            if (checkVittoria()) {
                System.out.println("Hai vinto!");
                break;
            }
        }
    }
}
