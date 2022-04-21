/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkautomat;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mschotte
 */
public class test {
    public static void main(String[] args) {
        Geldmenge startMenge = new Geldmenge(5,0,8,0,7,0,0,0);
        Kasse kasse = new Kasse(startMenge);
        try {
            
            
            
            Geldmenge zahlung1 = new Geldmenge(0,0,0,0,0,1,0,0);
            Geldmenge rueck =kasse.bezahle(zahlung1, 260);
            
            Geldmenge zahlung2=new Geldmenge(0,0,0,0,2);
            Geldmenge rueck2=kasse.bezahle(zahlung2, 260);
             } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
            kasse.writeTransaktionen("testTransaktionen");
            
       
        
        
        
    }
}
