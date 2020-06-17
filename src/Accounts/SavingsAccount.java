package Accounts;

public class SavingsAccount extends CurrentAccount{
    private double interestRate;
    public SavingsAccount(String iban, String ownerID, double amount, double interestRate){
        super(iban, ownerID, amount);
        this.setInterestRate(interestRate);
    }
    public void setInterestRate(double interestRate){
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public void showAccountInformation() {
        super.showAccountInformation();
        System.out.println("Interest rate: " + this.getInterestRate());
    }
}
