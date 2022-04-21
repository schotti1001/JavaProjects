/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkautomat;

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
    
    
    public void writeTransaktionen(String dateiname){
        try {
            FileWriter fw=new FileWriter("H:\\Daten\\Java\\"+dateiname+".txt");
            for (Transaktion transaktion : getTransaktionen()) {
                fw.write(transaktion.toString() + "\n");
                
                fw.flush();
            }
            fw.close();
        } catch (IOException ex) {
            System.out.println("Fehler beim schreiben!");
        }
        
    }
    
    //Funktion zur Berechnung des Wechselgelds einer Transaktion
    public Geldmenge bezahle(Geldmenge bezahlt, int zuZahlen) throws Exception {
         //intitialisierung
        int anzZweiEuro = 0, anzEinEuro = 0, anzFuenfzigCent = 0, anzZwanzigCent = 0, anzZehnCent = 0;
        //zwischenspeicher für späteres zurücksetzten bzw einspeichern int transaktion
        Geldmenge geldspeicherDavor=new Geldmenge(this.getGeldspeicher());
        //zu zahlender Betrag
        int wechselgeldBetrag = bezahlt.getBetrag() - zuZahlen;
        
        
        //Überprüfung ob genug geld gezahlt wurde
        if(wechselgeldBetrag>0){
       
            //überprüfung auf alle Geldwerte ob in den Wechselgeldbetrag der Münzwert ein oder mehrfach passt
            //falls dies zutrifft die Münzen aus dem geldspeicher abziehen und den wert vom wechselgeld abziehen
            
        if (wechselgeldBetrag / 200 <= this.getGeldspeicher().getZweiEuro()) {
            anzZweiEuro = wechselgeldBetrag / 200;
            wechselgeldBetrag = wechselgeldBetrag % 200;
           
        }
        if (wechselgeldBetrag / 100 <= this.getGeldspeicher().getEinEuro()) {
            anzEinEuro = wechselgeldBetrag / 100;
            wechselgeldBetrag = wechselgeldBetrag % 100;
           
        }
        if (wechselgeldBetrag / 50 <= this.getGeldspeicher().getFuenfzigCent()) {
            anzFuenfzigCent = wechselgeldBetrag / 50;
            wechselgeldBetrag = wechselgeldBetrag % 50;
           
        }
        if (wechselgeldBetrag / 10 <= this.getGeldspeicher().getZwanzigCent()) {
            anzZwanzigCent = wechselgeldBetrag / 20;
            wechselgeldBetrag = wechselgeldBetrag % 20;
          
        }
        if (wechselgeldBetrag / 10 <= this.getGeldspeicher().getZehnCent()) {
            anzZehnCent = wechselgeldBetrag / 10;
            wechselgeldBetrag = wechselgeldBetrag % 10;
          
        }
        if (wechselgeldBetrag != 0) {
            //falls der betrag mit den verfügbaren münzen bezahlt werden kann
            this.geldspeicher=geldspeicherDavor;
            Transaktion tr=new Transaktion(LocalDateTime.now(),zuZahlen,bezahlt,new Geldmenge(0,0,0,0,0,0,0,0),geldspeicherDavor,geldspeicherDavor);
            transaktionen.add(tr);
            throw new Exception("Fehler: Nicht genügend Münzen für das Wechselgeld vorhanden!");
        }
        }else{
            Transaktion tr=new Transaktion(LocalDateTime.now(),zuZahlen,bezahlt,new Geldmenge(0,0,0,0,0,0,0,0),geldspeicherDavor,geldspeicherDavor);
            transaktionen.add(tr);
            throw new Exception("Nicht genug Geld eingeworfen");
        }
        
        //Erfolgreich
        //Aus dem Geldspeicher die ausgegebenen Münzen abziehen
         this.getGeldspeicher().setZweiEuro(getGeldspeicher().getZweiEuro()-anzZweiEuro);
         this.getGeldspeicher().setEinEuro(getGeldspeicher().getEinEuro()-anzEinEuro);
         this.getGeldspeicher().setFuenfzigCent(getGeldspeicher().getFuenfzigCent()-anzFuenfzigCent);
         this.getGeldspeicher().setZwanzigCent(getGeldspeicher().getZwanzigCent()-anzZwanzigCent);
         this.getGeldspeicher().setZehnCent(getGeldspeicher().getZehnCent()-anzZehnCent);
         
         //Erstellen einer geldmenge mit dem errechneten Wechselgeld
        Geldmenge wechselgeld=new Geldmenge(anzZehnCent, anzZwanzigCent, anzFuenfzigCent, anzEinEuro, anzZweiEuro, 0, 0, 0); 
        
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
