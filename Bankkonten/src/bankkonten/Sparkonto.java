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
public class Sparkonto extends Konto {

    public Sparkonto() {
           super();
          
    }

   
    @Override
    public String toString() {
        return "Sparkonto{"  + " Kontotstand: " + super.getBalance() + '}';
    }
    
    
    @Override
    public void withdraw(double betrag) throws Exception {
       if(this.getBalance()-betrag >= 0.0 && betrag > 0){
         this.setBalance(-betrag);
        }else{
            throw new InsufficientBalanceException(this.getBalance(), betrag, "Fehler beim abbuchen Konto würde unter 0 fallen");
        }
    }
    
    
    
}
