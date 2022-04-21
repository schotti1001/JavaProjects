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
public class Girokonto extends Konto{

    private double overdraft;
    
    public Girokonto(double overdraft) {
        super();
        this.overdraft = overdraft;
        
    }

    @Override
    public String toString() {
        return "Girokonto{" + "overdraft=" + overdraft + " Kontotstand: " + super.getBalance() + '}';
    }
    

    public double getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(double overdraft) {
        this.overdraft = overdraft;
    }

    
    @Override
    public void withdraw(double betrag) throws Exception {
        if(this.getBalance()-betrag >= -this.getOverdraft() && betrag >0){
       this.setBalance(this.getBalance()-betrag);
        }else{
            throw new InsufficientBalanceException(this.getBalance(), betrag, "Fehler beim abbuchen Konto zu weit überzogen");
        }
    }

    

   
    
    
}
