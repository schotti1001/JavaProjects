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


    public enum Geldeinheit {

        zehnCent(10),
        zwanzigCent(20),
        fuenfzigCent(50),
        einEuro(100),
        zweiEuro(200),
        fuenfEuro(500),
        zehnEuro(1000),
        zwanzigEuro(2000);

        private final int betrag;

        private Geldeinheit(int betrag) {
            this.betrag = betrag;
        }

    public int getBetrag() {
        return betrag;
    }
        
        
        
    }


