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
public class UnzureichendeGeldmengeException extends RuntimeException{
    final private Geldmenge eingeworfen;
    final private int zuZahlen;

    public UnzureichendeGeldmengeException(String message, Geldmenge eingeworfen, int zuZahlen) {
        super(message);
        this.eingeworfen = eingeworfen;
        this.zuZahlen = zuZahlen;
    }

    public Geldmenge getEingeworfen() {
        return eingeworfen;
    }

    public int getZuZahlen() {
        return zuZahlen;
    }
    
    
    
    
}
