
package atm;

/**
 *
 * @author jeff Bryan
 * CMIS 242
 * Project 2
 */
public class Account {

    private double balance;

   
    public Account() {

    }

    
    public void setBalance(double balance) {
        this.balance = balance;
    }

    
    public double getBalance() {
        return this.balance;
    }

    
    public class Checking extends Account {

        public Checking() {
        }
    }

    
    public class Savings extends Account {

        public Savings() {
        }
    }

       
    public void withdraw(double withdrawAmount) throws InsufficientFunds {

        
        if (this.balance - withdrawAmount <= 0) {
            throw new InsufficientFunds();
        }

        this.balance = this.balance - withdrawAmount;
    }

    
    public void deposit(double depositAmount) {
        this.balance = this.balance + depositAmount;
    }

    
    public void transferTo(double transferAmount) {
        this.balance = this.balance + transferAmount;
    }

    
    public void transferFrom(double transferAmount) throws InsufficientFunds {
        
        if (this.balance - transferAmount < 0) {
            throw new InsufficientFunds();
        }

        this.balance = this.balance - transferAmount;
    }
}
    
    

