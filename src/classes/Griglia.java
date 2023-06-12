package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Griglia {
    private int[][] griglia;
    private Integer nNavi;

    private Scanner scanner;

    private List<Nave> navi;

    public Griglia() {
        this.griglia = new int[10][10];
        this.nNavi = 1;
        this.scanner = new Scanner(System.in);
        this.navi = new ArrayList<>();
    }

    // crea griglia vuota con 0
    public void creaGriglia() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                griglia[i][j] = 0;
            }
        }
    }

    // creazione nave
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
            // la lunghezza della nave deve essere un numero intero e maggiore di 0
            Boolean lunghezzaValida = false;
            Integer lunghezza = null;
            while (!lunghezzaValida) {
                lunghezza = scanner.nextInt();
                if (lunghezza > 0) {
                    lunghezzaValida = true;
                } else {
                    System.out.println("Lunghezza non valida. Riprova.");
                    System.out.println("La Lunghezza deve essere un numero intero e maggiore di 0.");
                }
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
            boolean posizioneOccupata = false;
            for (Coordinate nCoordinate : coordinate) {
                if (griglia[nCoordinate.getX()][nCoordinate.getY()] == 1) {
                    posizioneOccupata = true;
                    break;
                }
            }
            if (!posizioneOccupata) {
                naveCreata = true;
            } else {
                System.out.println("Posizione non valida. Riprova.");
            }
        }
        // inserisci coordinate nella nave
        nave.setCoordinate(coordinate);
        return nave;
    }


    // questa funzione inserisce le navi fino a che non sono finite
    public void InserisciNavi() {
        for (int i = 0; i < nNavi; i++) {
            Nave nave = creaNave();
            navi.add(nave);
            List<Coordinate> coordinate = nave.getCoordinate();
            for (Coordinate c : coordinate) {
                griglia[c.getX()][c.getY()] = 1;
            }
        }
    }

    // stampa griglia
    public void stampaGriglia() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(griglia[i][j] + " ");
            }
            System.out.println();
        }
    }

    // colpisci Nave
    public void colpisciNave() {
        System.out.println("Inserisci la coordinata di colpo:");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        Coordinate c = new Coordinate(x, y);
        if (griglia[c.getX()][c.getY()] == 1) {
            System.out.println("Hai colpito una nave!");
            griglia[c.getX()][c.getY()] = 0;
            // verifica se la nave è affondata
            Nave naveAffondata = isNaveAffondata(x, y);
            if (naveAffondata != null) {
                System.out.println("Hai affondato la nave " + naveAffondata.getNome());
            }

        } else {
            System.out.println("Hai colpito l'acqua!");
        }
    }

    // verifica se la nave è affondata
    public Nave isNaveAffondata(Integer x, Integer y) {
        Nave naveAffondata = null;
        // for each nave in navi
        for (Nave nave : navi) {
            // for each coordinate in nave
            for (Coordinate coordinate : nave.getCoordinate()) {
                // if coordinate is equal to x,y
                if (coordinate.getX() == x && coordinate.getY() == y) {
                    // recupero la nave
                    naveAffondata = nave;
                }
            }
        }
        // itero per coordinate in naveAffondata e vedo se tutte le posizioni nella griglia sono 0
        if (naveAffondata != null) {
            for (Coordinate coordinate : naveAffondata.getCoordinate()) {
                if (griglia[coordinate.getX()][coordinate.getY()] == 1) {
                    return null;
                }
            }
            return naveAffondata;
        }
        return null;
    }

    public boolean checkVittoria(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++){
                if (griglia[i][j] == 1){
                    return false;
                }
            }
        }
        return true;
    }

    // start
    public void start() {
        creaGriglia();
        InserisciNavi();
        stampaGriglia();
        while (true) {
            colpisciNave();
            stampaGriglia();
            if (checkVittoria()){
                System.out.println("Hai vinto!");
                break;
            }
        }
    }

}
