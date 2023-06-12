package classes;

import java.util.List;

public class Nave {
    private List<Coordinate> coordinate;
    private Integer lunghezza;
    private String nome;


    public Nave(List<Coordinate> coordinate, Integer lunghezza) {
        this.coordinate = coordinate;
        this.lunghezza = lunghezza;

    }

    public Nave() {
        this.coordinate = null;
        this.lunghezza = 0;

    }

    public List<Coordinate> getCoordinate() {
        return this.coordinate;

    }

    public Integer getLunghezza() {
        return this.lunghezza;

    }

    public void setCoordinate(List<Coordinate> coordinate) {
        this.coordinate = coordinate;

    }

    public void setLunghezza(Integer lunghezza) {
        this.lunghezza = lunghezza;

    }

    public String getNome() {
        return this.nome;

    }

    public void setNome(String nome) {
        this.nome = nome;

    }
}
