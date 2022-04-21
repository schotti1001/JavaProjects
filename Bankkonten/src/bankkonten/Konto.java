package bankkonten;


public abstract class Konto {

    private double kontostand;

    
    public Konto() {
        this.kontostand = 0.0;
    }

    
    
    @Override
    public abstract String toString();

    public double getBalance() {
        return kontostand;
    }

    public void setBalance(double betrag) {
        this.kontostand = betrag;
    }

    public abstract void withdraw(double betrag) throws Exception;

    public void deposit(double betrag) throws Exception {
        if (betrag > 0) {
            this.setBalance(this.getBalance() + betrag);
        } else {
            throw new Exception("Betrag kleiner als 0 nicht erlaubt!");
        }
    }

    public void transfer(double betrag, Konto auf) throws Exception {
        this.withdraw(betrag);
        auf.deposit(betrag);
    }
}
