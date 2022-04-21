/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkautomat;

public class Geldmenge {
    private int zehnCent;
    private int zwanzigCent;
    private int fuenfzigCent;
    private int einEuro;
    private int zweiEuro;
    private int fuenfEuro;
    private int zehnEuro;
    private int zwanzigEuro;

    public Geldmenge(int zehnCent, int zwanzigCent, int fuenfzigCent, int einEuro, int zweiEuro, int fuenfEuro, int zehnEuro, int zwanzigEuro) {
        this.zehnCent = zehnCent;
        this.zwanzigCent = zwanzigCent;
        this.fuenfzigCent = fuenfzigCent;
        this.einEuro = einEuro;
        this.zweiEuro = zweiEuro;
        this.fuenfEuro = fuenfEuro;
        this.zehnEuro = zehnEuro;
        this.zwanzigEuro = zwanzigEuro;
    }
    
    public Geldmenge(int zehnCent, int zwanzigCent, int fuenfzigCent, int einEuro, int zweiEuro) {
        this.zehnCent = zehnCent;
        this.zwanzigCent = zwanzigCent;
        this.fuenfzigCent = fuenfzigCent;
        this.einEuro = einEuro;
        this.zweiEuro = zweiEuro;
        this.fuenfEuro = 0;
        this.zehnEuro = 0;
        this.zwanzigEuro = 0;
    }

    public Geldmenge(Geldmenge alteMenge) {
        this.zehnCent = alteMenge.getZehnCent();
        this.zwanzigCent = alteMenge.getZwanzigCent();
        this.fuenfzigCent =alteMenge.getFuenfzigCent();
        this.einEuro = alteMenge.getEinEuro();
        this.zweiEuro = alteMenge.getZweiEuro();
        this.fuenfEuro = alteMenge.getFuenfEuro();
        this.zehnEuro = alteMenge.getZehnEuro();
        this.zwanzigEuro = alteMenge.getZwanzigEuro();
    }
    //zum Testen
    @Override
    public String toString() {
        return "Geldmenge{" + "zehnCent=" + zehnCent + ", zwanzigCent=" + zwanzigCent + ", fuenfzigCent=" + fuenfzigCent + ", einEuro=" + einEuro + ", zweiEuro=" + zweiEuro + ", fuenfEuro=" + fuenfEuro + ", zehnEuro=" + zehnEuro + ", zwanzigEuro=" + zwanzigEuro + '}';
    }
    
    
    
    public int getBetrag(){
        int gesBetrag=0;
        gesBetrag+=this.getZehnCent()*10;
        gesBetrag+=this.getZwanzigCent()*20;
        gesBetrag+=this.getFuenfzigCent()*50;
        gesBetrag+=this.getEinEuro()*100;
        gesBetrag+=this.getZweiEuro()*200;
        gesBetrag+=this.getFuenfEuro()*500;
        gesBetrag+=this.getZehnEuro()*1000;
        gesBetrag+=this.getZwanzigEuro()*2000;  
        return gesBetrag;
    }

    public Geldmenge() {
    }

    public int getZehnCent() {
        return zehnCent;
    }

    public void setZehnCent(int zehnCent) {
        this.zehnCent = zehnCent;
    }

    public int getZwanzigCent() {
        return zwanzigCent;
    }

    public void setZwanzigCent(int zwanzigCent) {
        this.zwanzigCent = zwanzigCent;
    }

    public int getFuenfzigCent() {
        return fuenfzigCent;
    }

    public void setFuenfzigCent(int fuenfzigCent) {
        this.fuenfzigCent = fuenfzigCent;
    }

    public int getEinEuro() {
        return einEuro;
    }

    public void setEinEuro(int einEuro) {
        this.einEuro = einEuro;
    }

    public int getZweiEuro() {
        return zweiEuro;
    }

    public void setZweiEuro(int zweiEuro) {
        this.zweiEuro = zweiEuro;
    }

    public int getFuenfEuro() {
        return fuenfEuro;
    }

    public void setFuenfEuro(int fuenfEuro) {
        this.fuenfEuro = fuenfEuro;
    }

    public int getZehnEuro() {
        return zehnEuro;
    }

    public void setZehnEuro(int zehnEuro) {
        this.zehnEuro = zehnEuro;
    }

    public int getZwanzigEuro() {
        return zwanzigEuro;
    }

    public void setZwanzigEuro(int zwanzigEuro) {
        this.zwanzigEuro = zwanzigEuro;
    }
    
    //erhöht geldmenge g1
    public Geldmenge erhoehe(Geldmenge g1){
        
        g1.setZwanzigEuro(g1.getZwanzigEuro()+this.getZwanzigEuro());
        g1.setZehnEuro(g1.getZehnEuro()+this.getZehnEuro());
        g1.setFuenfEuro(g1.getFuenfEuro()+this.getFuenfEuro());
        g1.setZweiEuro(g1.getZweiEuro()+this.getZweiEuro());
        g1.setEinEuro(g1.getEinEuro()+this.getEinEuro());
        g1.setFuenfzigCent(g1.getFuenfzigCent()+this.getFuenfzigCent());
        g1.setZwanzigCent(g1.getZwanzigCent()+this.getZwanzigCent());
        g1.setZehnCent(g1.getZehnCent()+this.getZehnCent());
        return g1;
    }
       
    
    
    
    
}
