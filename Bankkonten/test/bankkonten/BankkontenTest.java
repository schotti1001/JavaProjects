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
public class BankkontenTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Girokonto g1 = new Girokonto(2000.0);
        try {
            g1.deposit(5000.0);
            System.out.println(g1.toString());
        } catch (InsufficientBalanceException ex) {
            System.out.println(ex.getMessage() + "Kontostand: " + ex.kontostand + " abzubuchender Betrag: " + ex.abzubuchenderBetrag);
        } catch (Exception e) {
            System.out.println("Unbekannter Fehler aufgetreten!");
        }
        try {
            g1.withdraw(2500.00);
            System.out.println(g1.toString());
        } catch (InsufficientBalanceException ex) {
            System.out.println(ex.getMessage() + "Kontostand: " + ex.kontostand + " abzubuchender Betrag: " + ex.abzubuchenderBetrag);
        } catch (Exception e) {
            System.out.println("Unbekannter Fehler aufgetreten!");
        }
        try {
            g1.withdraw(4501.00);
            System.out.println(g1.toString());
        } catch (InsufficientBalanceException ex) {
            System.out.println(ex.getMessage() + "Kontostand: " + ex.kontostand + " abzubuchender Betrag: " + ex.abzubuchenderBetrag);
        } catch (Exception e) {
            System.out.println("Unbekannter Fehler aufgetreten!");
        }

        try {
            g1.deposit(2500.0);
            System.out.println(g1.toString());
        } catch (InsufficientBalanceException ex) {
            System.out.println(ex.getMessage() + "Kontostand: " + ex.kontostand + " abzubuchender Betrag: " + ex.abzubuchenderBetrag);
        } catch (Exception e) {
            System.out.println("Unbekannter Fehler aufgetreten!");
        }

        Sparkonto s1 = new Sparkonto();
        try {
            s1.deposit(5000.0);
            System.out.println(s1.toString());
        } catch (InsufficientBalanceException ex) {
            System.out.println(ex.getMessage() + "Kontostand: " + ex.kontostand + " abzubuchender Betrag: " + ex.abzubuchenderBetrag);
        } catch (Exception e) {
            System.out.println("Unbekannter Fehler aufgetreten!");
        }
        try {
            s1.withdraw(5001.0);
            System.out.println(s1.toString());
        } catch (InsufficientBalanceException ex) {
            System.out.println(ex.getMessage() + "Kontostand: " + ex.kontostand + " abzubuchender Betrag: " + ex.abzubuchenderBetrag);
        } catch (Exception e) {
            System.out.println("Unbekannter Fehler aufgetreten!");
        }

        try {
            System.out.println(g1.toString());
            System.out.println(s1.toString());
            g1.transfer(2000.0, s1);
            System.out.println(g1.toString());
            System.out.println(s1.toString());
        } catch (InsufficientBalanceException ex) {
            System.out.println(ex.getMessage() + "Kontostand: " + ex.kontostand + " abzubuchender Betrag: " + ex.abzubuchenderBetrag);
        } catch (Exception e) {
            System.out.println("Unbekannter Fehler aufgetreten!");
        }

    }

}
