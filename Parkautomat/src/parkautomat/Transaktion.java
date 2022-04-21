/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkautomat;


import java.time.LocalDateTime;



public class Transaktion {
    private LocalDateTime zeitpunkt;
    private int zuZahlenderBetrag;
    private Geldmenge gezahlt;
    private Geldmenge wechselgeld;
    private Geldmenge geldspeicherAnf;
    private Geldmenge geldspeicherDanach;

    public Transaktion(LocalDateTime zeitpunkt, int zuZahlenderBetrag, Geldmenge gezahlt, Geldmenge wechselgeld, Geldmenge geldspeicherAnf, Geldmenge geldspeicherDanach) {
        this.zeitpunkt = zeitpunkt;
        this.zuZahlenderBetrag = zuZahlenderBetrag;
        this.gezahlt = gezahlt;
        this.wechselgeld = wechselgeld;
        this.geldspeicherAnf = geldspeicherAnf;
        this.geldspeicherDanach = geldspeicherDanach;
    }

    @Override
    public String toString() {
        return "Transaktion{" + "zeitpunkt=" + zeitpunkt + ", zuZahlenderBetrag=" + zuZahlenderBetrag + ", gezahlt=" + gezahlt +
                ", wechselgeld=" + wechselgeld + ", geldspeicherAnf=" + geldspeicherAnf + ", geldspeicherDanach=" + geldspeicherDanach + '}';
    }
    
    

    public Transaktion() {
    }

    public LocalDateTime getZeitpunkt() {
        return zeitpunkt;
    }

    public void setZeitpunkt(LocalDateTime zeitpunkt) {
        this.zeitpunkt = zeitpunkt;
    }

    public int getZuZahlenderBetrag() {
        return zuZahlenderBetrag;
    }

    public void setZuZahlenderBetrag(int zuZahlenderBetrag) {
        this.zuZahlenderBetrag = zuZahlenderBetrag;
    }

    public Geldmenge getGezahlt() {
        return gezahlt;
    }

    public void setGezahlt(Geldmenge gezahlt) {
        this.gezahlt = gezahlt;
    }

    public Geldmenge getWechselgeld() {
        return wechselgeld;
    }

    public void setWechselgeld(Geldmenge wechselgeld) {
        this.wechselgeld = wechselgeld;
    }

    public Geldmenge getGeldspeicherAnf() {
        return geldspeicherAnf;
    }

    public void setGeldspeicherAnf(Geldmenge geldspeicherAnf) {
        this.geldspeicherAnf = geldspeicherAnf;
    }

    public Geldmenge getGeldspeicherDanach() {
        return geldspeicherDanach;
    }

    public void setGeldspeicherDanach(Geldmenge geldspeicherDanach) {
        this.geldspeicherDanach = geldspeicherDanach;
    }
    
    
    
    
    
}
