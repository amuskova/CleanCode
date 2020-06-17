package Accounts;
import Exceptions.InvalidIbanException;
import Exceptions.NegativeAmountException;


public abstract class Account {
    private static final int LENGTH_IBAN = 22;
    private String iban;
    private String ownerID;
    private double amount;

    public Account(String iban, String ownerID, double amount){
        this.setIBAN(iban);
        this.setOwnerID(ownerID);
        this.setAmount(amount);
    }
    public void setIBAN(String iban){
        int lengthIBAN = iban.length();
        if(lengthIBAN != LENGTH_IBAN){
            throw new InvalidIbanException("The IBAN should be with 22 symbols!");
        }
        this.iban = iban;
    }
    public void setOwnerID(String ownerID){
        this.ownerID = ownerID;
    }
    public void setAmount(double amount){
        this.validateAmount(amount);
        this.amount = amount;
    }
    public String getIban(){
        return this.iban;
    }
    public String getOwnerID(){
        return this.ownerID;
    }
    public double getAmount(){
        return this.amount;
    }
    public abstract void deposit(double amount);
    public abstract void withdraw(double amount);
    public void showAccountInformation(){
        System.out.println("Iban: " + this.getIban());
        System.out.println("Owner ID: " + this.getOwnerID());
        System.out.println("Current balance: " + this.getAmount());
    }
    protected void validateAmount(double amount){
        if(amount < 0){
            throw new NegativeAmountException("The amount can not be negative!");
        }
    }
    protected void addAmount(double amountToAdd){
        this.validateAmount(amountToAdd);
        this.amount += amountToAdd;
        System.out.println("Successful!");
    }
    protected void removeAmount(double amountToRemove){
        this.validateAmount(amountToRemove);
        this.amount -= amountToRemove;
        System.out.println("Successful!");
    }
}
