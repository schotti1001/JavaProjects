/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkautomat2;

/**
 *
 * @author mschotte
 */
public class WechselgeldException extends Exception{
    final private int wechselGeld;
    final private Geldmenge vorhanden;
    
    public WechselgeldException(String message, int wechselGeld, Geldmenge vorhanden){
        super(message);
        this.wechselGeld =wechselGeld;
        this.vorhanden=vorhanden;
    }

    public int getWechselGeld() {
        return wechselGeld;
    }

    public Geldmenge getVorhanden() {
        return vorhanden;
    }
    
    
}
