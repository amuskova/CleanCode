package Bank;

import Accounts.Account;
import Managers.CustomerManager;
import Managers.CustomerManagerInterface;

public class Bank implements BankInterface{
    private String name;
    private String address;
    private CustomerManagerInterface customerManager;

    public Bank(String name, String address, CustomerManagerInterface customerManager){
        this.setName(name);
        this.setAddress(address);
        this.customerManager = customerManager;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getAddress() {
        return this.address;
    }

    @Override
    public void showBankInformation() {
        System.out.println("Bank information: ");
        System.out.println("Name of the bank: " + this.getName());
        System.out.println("Address of the bank: " + this.getAddress());
    }

    @Override
    public void withdrawFromAccount(String ownerID, String iban, double amount) {
        Account account = this.customerManager.getAccount(ownerID,iban);
        account.withdraw(amount);
    }

    @Override
    public void depositToAccount(String ownerID, String iban, double amount) {
        Account account = this.customerManager.getAccount(ownerID, iban);
        account.deposit(amount);
    }

    @Override
    public void transfer(String ownerID, String fromIban, String customerIDToTransfer, String toIban, double amount) {
        Account sender = this.customerManager.getAccount(ownerID, fromIban);
        Account receiver = this.customerManager.getAccount(customerIDToTransfer,toIban);
        sender.withdraw(amount);
        receiver.deposit(amount);
    }
}
