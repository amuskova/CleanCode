package Accounts;

import Exceptions.NotEnoughMoneyException;

public class CurrentAccount extends Account{
    public CurrentAccount(String iban, String ownerID, double amount){
        super(iban, ownerID, amount);
    }

    @Override
    public void deposit(double amount) {
        super.addAmount(amount);
    }

    @Override
    public void withdraw(double amount) {
        double currentAmount = super.getAmount();
        if(currentAmount < amount){
            throw new NotEnoughMoneyException("Not enough money!");
        }
        super.removeAmount(amount);
    }

    @Override
    public void showAccountInformation() {
        super.showAccountInformation();
    }
}
