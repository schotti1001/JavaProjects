/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schwebebahn;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.apache.jasper.tagplugins.jstl.ForEach;

/**
 *
 * @author mschotte
 */
@Named
@SessionScoped
public class VerbindungsBean implements Serializable {

    private List<Verbindung> verbindungen = new ArrayList<>();
    private List<Stadt> bahnhoefe=new ArrayList<>();
    private String startbahnhof;
    private String zielbahnhof;
    private int entfernung;
    private List<Verbindung> alteVerbindungen=new ArrayList<>();
    private  int a[][];
    
      static PreparedStatement befehl = null;
	static ResultSet ergebnis = null;
        
    public VerbindungsBean() {
    }

    
    
    @PostConstruct
    public void init() {
        //Lädt alle Daten aus DB ein
        try {
            

            ResultSet res = loadAllBahnhoefe();

            while (res.next()) {
                bahnhoefe.add(new Stadt( res.getString("Name")));
            }
            
           

            ResultSet res2 = loadAllVerbindungen();

            while (res2.next()) {
                verbindungen.add(new Verbindung( res2.getString("Startbahnhof"),res2.getString("Zielbahnhof"),res2.getInt("Entfernung")));
            }
              
        } catch (Exception e) {
            System.out.println("Fehler:");
            e.printStackTrace();
            System.out.println(e);
        }
        /*
        bahnhoefe.add(new Stadt("Hannover"));
        bahnhoefe.add(new Stadt("Essen"));
        bahnhoefe.add(new Stadt("Erfurt"));
        bahnhoefe.add(new Stadt("Frankfurt"));
        bahnhoefe.add(new Stadt("Stuttgart"));
        bahnhoefe.add(new Stadt("München"));
        bahnhoefe.add(new Stadt("Plattling"));
        bahnhoefe.add(new Stadt("Berlin"));
        bahnhoefe.add(new Stadt("Dresden"));
        bahnhoefe.add(new Stadt("Hamburg"));
        
       
        
        verbindungen.add(new Verbindung("Hannover", "Essen", 194));
        verbindungen.add(new Verbindung("Essen", "Erfurt", 312));
        verbindungen.add(new Verbindung("Erfurt", "Frankfurt", 263));
        verbindungen.add(new Verbindung("Erfurt", "Stuttgart", 344));
        verbindungen.add(new Verbindung("Erfurt", "München", 329));
        verbindungen.add(new Verbindung("Erfurt", "Berlin", 326));
        verbindungen.add(new Verbindung("Erfurt", "Hamburg", 382));
        verbindungen.add(new Verbindung("Frankfurt", "Stuttgart", 179));
        verbindungen.add(new Verbindung("München", "Plattling", 130));
        verbindungen.add(new Verbindung("Berlin", "Dresden", 178));
        verbindungen.add(new Verbindung("Berlin", "Hamburg", 320));
        */
       //Befüllt Listen
         a=new int[bahnhoefe.size()][bahnhoefe.size()];
        
        for (int i = 0; i < bahnhoefe.size(); i++) {
             for (int j = 0; j < verbindungen.size(); j++) {
            if(bahnhoefe.get(i).getName().equals(verbindungen.get(j).getStartbahnhof())){
                Stadt zielBahnhof=new Stadt(verbindungen.get(j).getZielbahnhof());
                int indZiel=bahnhoefe.indexOf(zielBahnhof);
               a[i][indZiel]=verbindungen.get(j).getEntfernung();
               a[indZiel][i]=verbindungen.get(j).getEntfernung();
            }
            }
        }
        floydWarshall(a);
       //ausgeben();
    }
    

    //ausgeben des aktuellen werte
    public void ausgeben(){
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                System.out.print(a[i][j] + ",");
            }
            System.out.println("new Line");
        }
    }
    
  
    
    
    public void floydWarshall(int[][] pMatrix) {
        //Berechnet fehlende Werte
        int[][] a = pMatrix;
        int N = a.length;

        int y, x, j;

        for (y = 0; y < N; y++) {
            for (x = 0; x < N; x++) {
                if (a[x][y] > 0) {
                    for (j = 0; j < N; j++) {
                        if (a[y][j] > 0) {
                            if ((a[x][j] == 0) || (a[x][y] + a[y][j] < a[x][j])) {
                                a[x][j] = a[x][y] + a[y][j];
                            }
                        }
                    }
                }
            }
        }
        
        for (int i = 0; i < a.length; i++) {
            a[i][i]=0;
            
        }

        for (y = 0; y < N; y++) {
            String s="";
            for (x = 0; x < N; x++) {
                s+=a[y][x] < 10 ? "  " + a[y][x] : " " + a[y][x];
            }
            System.out.println(s);
        }

    }

    public void loescheAlte(){
        this.getAlteVerbindungen().clear();
    }
    
    public void berechneEntfernungNeu(){
        //Liest die Entfernung und speichert als alte Verbinung in Tab ein
        int indStart=bahnhoefe.indexOf(new Stadt(this.startbahnhof));
        int indZiel=bahnhoefe.indexOf(new Stadt(this.zielbahnhof));
        this.entfernung=a[indStart][indZiel];
        alteVerbindungen.add(new Verbindung(zielbahnhof, startbahnhof, entfernung));
    }
    
    public void ladeDaten(){
        //Lädt daten erneut as DB ein
        try {
            bahnhoefe.clear();

            ResultSet res = loadAllBahnhoefe();

            while (res.next()) {
                bahnhoefe.add(new Stadt( res.getString("Name")));
            }
            
            verbindungen.clear();

            ResultSet res2 = loadAllVerbindungen();

            while (res2.next()) {
                verbindungen.add(new Verbindung( res2.getString("Startbahnhof"),res2.getString("Zielbahnhof"),res2.getInt("Entfernung")));
            }
              
        } catch (Exception e) {
            System.out.println("Fehler beim erneuten laden "+ e);
        }
    }
    
    public void berechneEntfernung() {
        //Alte Methode zurr berechnung
        for (int i = 0; i < verbindungen.size(); i++) {
            if (startbahnhof.equals(verbindungen.get(i).getStartbahnhof())) {
                if (zielbahnhof.equals(verbindungen.get(i).getZielbahnhof())) {
                    this.entfernung= verbindungen.get(i).getEntfernung();
                    alteVerbindungen.add(new Verbindung(startbahnhof, zielbahnhof, entfernung));
                }
            } else if (startbahnhof.equals(verbindungen.get(i).getZielbahnhof())) {
                if (zielbahnhof.equals(verbindungen.get(i).getStartbahnhof())) {
                    this.entfernung= verbindungen.get(i).getEntfernung();
                    alteVerbindungen.add(new Verbindung(zielbahnhof, startbahnhof, entfernung));
                }
            }
        }
       

    }
    
    // Lesen Datensätze in Tabelle für Bahnhoefe
	public ResultSet loadAllBahnhoefe()throws Exception{
		
		 String sql = "Select * from JSF_Staedte";
        Connection con = JDBCVerbindung.getVerbindung();
        befehl = con.prepareStatement(sql);

        ergebnis = befehl.executeQuery();
        return ergebnis;	
	}
	
// Lesen Datensätze in Tabelle für Verbindungel 
	public ResultSet loadAllVerbindungen()throws Exception{
		
		 String sql = "Select * from JSF_Verbindungen";
        Connection con = JDBCVerbindung.getVerbindung();
        befehl = con.prepareStatement(sql);

        ergebnis = befehl.executeQuery();
        return ergebnis;	
	}
	

	
    
    

    public int[][] getA() {
        return a;
    }

    public void setA(int[][] a) {
        this.a = a;
    }

    

    
    
    public List<Verbindung> getAlteVerbindungen() {
        return alteVerbindungen;
    }

    public void setAlteVerbindungen(List<Verbindung> alteVerbindungen) {
        this.alteVerbindungen = alteVerbindungen;
    }

    
    
    public List<Stadt> getBahnhoefe() {
        return bahnhoefe;
    }

    public void setBahnhoefe(List<Stadt> bahnhoefe) {
        this.bahnhoefe = bahnhoefe;
    }


   
    
    public int getEntfernung() {
        return entfernung;
    }

    public void setEntfernung(int entfernung) {
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

    public List<Verbindung> getVerbindungen() {
        return verbindungen;
    }

    public void setVerbindungen(List<Verbindung> verbindungen) {
        this.verbindungen = verbindungen;
    }


}
