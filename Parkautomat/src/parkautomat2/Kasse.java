/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkautomat2;

import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.util.ArrayList;


/**
 *
 * @author mschotte
 */
public class Kasse {

    private Geldmenge geldspeicher;
    private ArrayList<Transaktion> transaktionen=new ArrayList<>();
    public Kasse(Geldmenge geldspeicher) {
        this.geldspeicher = geldspeicher;
    }

    public Kasse() {
    }
    
    //schreibt Transaktionen in File
    public void writeTransaktionen(String dateiname){
        
        try( FileWriter fw=new FileWriter("H:\\Daten\\Java\\"+dateiname+".txt");) {
           
            for (Transaktion transaktion : getTransaktionen()) {
                fw.write(transaktion.toString() + "\n");
                
                fw.flush();
            }
            
        } catch (IOException ex) {
            
            System.out.println("Fehler beim schreiben!");
        }
        
    }
    
    //Funktion zur Berechnung des Wechselgelds einer Transaktion
    public Geldmenge bezahle(Geldmenge bezahlt, int zuZahlen) throws Exception {
         //intitialisierung
         
         //[0] = 2 € [1] = 1 €...
        int[] anzahlen={0, 0, 0, 0, 0};
        //zwischenspeicher für späteres zurücksetzten bzw einspeichern int transaktion
        Geldmenge geldspeicherDavor=new Geldmenge(this.getGeldspeicher());
        //zu zahlender Betrag
        int wechselgeldBetrag = bezahlt.getBetrag() - zuZahlen;
        
        
        //Überprüfung ob genug geld gezahlt wurde
        if(wechselgeldBetrag>0){
       
            //überprüfung auf alle Geldwerte ob in den Wechselgeldbetrag der Münzwert ein oder mehrfach passt
            //falls dies zutrifft die Münzen aus dem geldspeicher abziehen und den wert vom wechselgeld abziehen
            for (int i=4;i>=0; i--){
                Geldeinheit aktMuenze=Geldeinheit.values()[i];
                anzahlen[i] = Math.min(wechselgeldBetrag / aktMuenze.getBetrag() , this.getGeldspeicher().getAnzahl(i));
                wechselgeldBetrag -= anzahlen[i]* aktMuenze.getBetrag();
            }
           

        if (wechselgeldBetrag != 0) {
            //falls der betrag mit den verfügbaren münzen bezahlt werden kann
            this.geldspeicher=geldspeicherDavor;
            Transaktion tr=new Transaktion(LocalDateTime.now(),zuZahlen,bezahlt,new Geldmenge(0,0,0,0,0,0,0,0),geldspeicherDavor,geldspeicherDavor);
            transaktionen.add(tr);
            throw new WechselgeldException("Nicht genügend Münzen für das Wechselgeld vorhanden!",  bezahlt.getBetrag() - zuZahlen, geldspeicher);
        }
        }else{
            Transaktion tr=new Transaktion(LocalDateTime.now(),zuZahlen,bezahlt,new Geldmenge(0,0,0,0,0,0,0,0),geldspeicherDavor,geldspeicherDavor);
            transaktionen.add(tr);
            throw new UnzureichendeGeldmengeException("Nicht genug Geld eingeworfen", bezahlt, zuZahlen);
        }
        
        //Erfolgreich
        //Aus dem Geldspeicher die ausgegebenen Münzen abziehen
        for (int i=4;i>=0; i--){
            this.getGeldspeicher().setAnzahl(i, (-anzahlen[i]));
        }
        
        
         
         //Erstellen einer geldmenge mit dem errechneten Wechselgeld
        Geldmenge wechselgeld=new Geldmenge(anzahlen[0], anzahlen[1], anzahlen[2], anzahlen[3], anzahlen[4], 0, 0, 0); 
        
        Geldmenge geldspeicherDanach=this.getGeldspeicher();
        //Geldspeicher um den betrag erhöhen der eingeworfen wurde
        bezahlt.erhoehe(geldspeicher);
        //Transaktion erstellen und wegspeichern
        Transaktion tr=new Transaktion(LocalDateTime.now(),zuZahlen,bezahlt,wechselgeld,geldspeicherDavor,geldspeicherDanach);
        transaktionen.add(tr);
        
        return wechselgeld;
    }

    public ArrayList<Transaktion> getTransaktionen() {
        return transaktionen;
    }

    public void setTransaktionen(ArrayList<Transaktion> transaktionen) {
        this.transaktionen = transaktionen;
    }
    
    
    //Geldspeicher als centbetrag
    public int getBetrag() {
        return geldspeicher.getBetrag();
    }

    public Geldmenge getGeldspeicher() {
        return geldspeicher;
    }

    public void setGeldspeicher(Geldmenge geldspeicher) {
        this.geldspeicher = geldspeicher;
    }

}
