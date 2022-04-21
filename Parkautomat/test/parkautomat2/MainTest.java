/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkautomat2;

import java.util.logging.Level;
import java.util.logging.Logger;
import parkautomat2.Geldmenge;
import parkautomat2.Kasse;

/**
 *
 * @author mschotte
 */
public class MainTest {

    public static void main(String[] args) {
        Geldmenge startMenge = new Geldmenge(5, 0, 8, 0, 7, 0, 0, 0);
        Kasse kasse = new Kasse(startMenge);
        try {

            Geldmenge zahlung1 = new Geldmenge(0, 0, 0, 0, 0, 1, 0, 0);
            Geldmenge rueck1 = kasse.bezahle(zahlung1, 260);
        } catch (WechselgeldException ex) {
            System.out.println(ex.getMessage() + " Vorhandene Geldmenge: " + ex.getVorhanden() + " Wechselgeldmenge: " + ex.getWechselGeld());
        } catch (UnzureichendeGeldmengeException ex) {
            System.out.println(ex.getMessage() + " Eingeworfen: " + ex.getEingeworfen() + " Zuzahlen:" + ex.getZuZahlen());
        } catch (Exception e) {
            System.out.println("unbekannter Fehler");
        }
        
        
        try {
            Geldmenge zahlung2 = new Geldmenge(0, 0, 0, 0, 2);
            Geldmenge rueck2 = kasse.bezahle(zahlung2, 260);
        } catch (WechselgeldException ex) {
            System.out.println(ex.getMessage() + " Vorhandene Geldmenge: " + ex.getVorhanden() + " Wechselgeldmenge: " + ex.getWechselGeld());
        } catch (UnzureichendeGeldmengeException ex) {
            System.out.println(ex.getMessage() + " Eingeworfen: " + ex.getEingeworfen() + " Zuzahlen:" + ex.getZuZahlen());
        } catch (Exception e) {
            System.out.println("unbekannter Fehler!");
        }
        
        kasse.writeTransaktionen("testTransaktionen");

    }
}
