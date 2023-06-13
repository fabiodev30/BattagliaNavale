package classes;

public class Punteggio {
    private Integer punteggio;

    public Punteggio() {
        this.punteggio = 0;
    }

    public Integer getPunteggio() {
        return this.punteggio;
    }

    public void setPunteggio(Integer punteggio) {
        this.punteggio = punteggio;
    }

    // aumenta il punteggio di 1
    public void aumentaPunteggio() {
        this.punteggio++;
    }

    // azzera punteggio
    public void azzeraPunteggio() {
        this.punteggio = 0;
    }
}
