package Managers;

import Accounts.*;
import Customers.Customer;
import Customers.CustomerInterface;
import Exceptions.*;
import java.util.*;

public class CustomerManager implements CustomerManagerInterface{
    private Map<Customer, List<Account>> customerManager;
    public CustomerManager(){
        this.customerManager = new HashMap<>();
    }

    @Override
    public void addCustomer(Customer newCustomer) {
        Customer customer = this.checkCustomerExistence(newCustomer.getID());
        if(customer != null){
            throw new CustomerAlreadyExistsException("The customer already exists");
        }
        this.customerManager.put(newCustomer, new ArrayList<>());
        System.out.println("The customer was successfully added!");
    }

    @Override
    public void deleteCustomer(String ownerID) {
        Customer customer = this.getCustomer(ownerID);
        this.customerManager.remove(customer);
        System.out.println("The customer was successfully deleted");
    }

    @Override
    public void listAllCustomers() {
        if(this.customerManager.size() == 0){
            System.out.println("No customers in the bank!");
            return;
        }
        System.out.println("All customers: ");
        for(Customer customer : this.customerManager.keySet()){
            customer.displayCustomerInformation();
        }
    }

    @Override
    public void listAccountsByOwnerID(String ownerID) {
        Customer currentCustomer = this.getCustomer(ownerID);
        List<Account> currentCustomerAccounts = this.customerManager.get(currentCustomer);
        if(currentCustomerAccounts.size() == 0){
            System.out.println("This customer has no accounts!");
            return;
        }
        for(Account account : currentCustomerAccounts){
            account.showAccountInformation();
        }
    }

    @Override
    public void addAccount(Account account, AccountType type) {
        Customer currentCustomer = this.getCustomer(account.getOwnerID());
        Account currentAccount = this.getAccountByCustomer(currentCustomer, account.getIban());
        if(currentAccount != null){
            throw new AccountAlreadyExistsException("The account already exist");
        }
        Account newAccount = this.createAccount(account, type);
        List<Account> currentCustomerAccounts = this.customerManager.get(currentCustomer);
        currentCustomerAccounts.add(newAccount);
        this.customerManager.put(currentCustomer, currentCustomerAccounts);
    }

    @Override
    public void deleteAccount(String ownerID, String iban) {
        Customer currentCustomer = this.getCustomer(ownerID);
        Account currentAccount = this.getAccountByCustomer(currentCustomer,iban);
        if(currentAccount == null){
            throw new AccountNotFoundException("No such account int he bank!");
        }
        List<Account> currentCustomerAccounts = this.customerManager.get(currentCustomer);
        currentCustomerAccounts.remove(currentAccount);
        this.customerManager.put(currentCustomer,currentCustomerAccounts);
    }

    @Override
    public Account getAccount(String ownerID, String iban) {
        Customer currentCustomer = this.getCustomer(ownerID);
        Account account = this.getAccountByCustomer(currentCustomer,iban);
        if(account == null){
            throw new AccountNotFoundException("No such account in the bank!");
        }
        return account;
    }

    private Account getAccountByCustomer(Customer customer, String iban){
        List<Account> currentCustomerAccounts = this.customerManager.get(customer);
        for(Account account : currentCustomerAccounts){
            if(account.getIban().equals(iban)){
                return account;
            }
        }
        return null;
    }
    private Customer checkCustomerExistence(String customerID){
        for(Customer customer : this.customerManager.keySet()){
            if (customer.getID().equals(customerID)){
                return customer;
            }
        }
        return null;
    }
    private Customer getCustomer(String ownerID){
        Customer customer = this.checkCustomerExistence(ownerID);
        if(customer == null){
            throw new CustomerNotFoundException("No such customer in the bank!");
        }
        return customer;
    }
    private Account createAccount(Account account, AccountType type){
        Scanner scanner = new Scanner(System.in);
        switch (type){
            case PRIVILEGE:
                System.out.println("Enter overdraft: ");
                double overdraft = scanner.nextDouble();
                return new PrivilegeAccount(account.getIban(),account.getOwnerID(),account.getAmount(),overdraft);
            case SAVINGS:
                System.out.println("Enter interest rate: ");
                double interestRate = scanner.nextDouble();
                return new SavingsAccount(account.getIban(),account.getOwnerID(),account.getAmount(), interestRate);
            case CURRENT:
                return new CurrentAccount(account.getIban(),account.getOwnerID(),account.getAmount());
            default:
                throw new InvalidTypeException("No such account type");
        }
    }
}
