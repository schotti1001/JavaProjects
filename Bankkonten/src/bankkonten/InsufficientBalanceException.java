/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankkonten;

/**
 *
 * @author mschotte
 */
public class InsufficientBalanceException extends RuntimeException {
    final double kontostand;
    final double abzubuchenderBetrag;

    public InsufficientBalanceException(double kontostand, double abzubuchenderBetrag, String message) {
        super(message);
        this.kontostand = kontostand;
        this.abzubuchenderBetrag = abzubuchenderBetrag;
    }

    public double getKontostand() {
        return kontostand;
    }

    public double getAbzubuchenderBetrag() {
        return abzubuchenderBetrag;
    }
    
    
    
    
}
