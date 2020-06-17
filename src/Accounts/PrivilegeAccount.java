package Accounts;

import Exceptions.NotEnoughMoneyException;

public class PrivilegeAccount extends CurrentAccount{
    private double overdraft;
    public PrivilegeAccount(String iban, String ownerID, double amount, double overdraft){
        super(iban, ownerID, amount);
        this.setOverdraft(overdraft);
    }
    public void setOverdraft(double overdraft){
        this.overdraft = overdraft;
    }
    public double getOverdraft(){
        return this.overdraft;
    }

    @Override
    public void withdraw(double amount) {
        double currentAllowedAmount = super.getAmount() + this.getOverdraft();
        if(currentAllowedAmount < amount){
            throw new NotEnoughMoneyException("Not enough money!");
        }
        super.removeAmount(amount);
    }

    @Override
    public void showAccountInformation() {
        super.showAccountInformation();
        System.out.println("Overdraft: " + this.getOverdraft());
    }
}
