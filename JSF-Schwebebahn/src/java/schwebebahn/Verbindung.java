/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schwebebahn;

/**
 *
 * @author mschotte
 */
public class Verbindung {
    private String startbahnhof;
    private String zielbahnhof;
    private int entfernung;
    

    public Verbindung(String startbahnhof, String zielbahnhof, int entfernung) {
        this.startbahnhof = startbahnhof;
        this.zielbahnhof = zielbahnhof;
        this.entfernung = entfernung;
    }

    public String getStartbahnhof() {
        return startbahnhof;
    }

    public void setStartbahnhof(String startbahnhof) {
        this.startbahnhof = startbahnhof;
    }

    public String getZielbahnhof() {
        return zielbahnhof;
    }

    public void setZielbahnhof(String zielbahnhof) {
        this.zielbahnhof = zielbahnhof;
    }

    public int getEntfernung() {
        return entfernung;
    }

    public void setEntfernung(int entfernung) {
        this.entfernung = entfernung;
    }
    
    
}
