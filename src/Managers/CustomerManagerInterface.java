package Managers;

import Accounts.Account;
import Accounts.AccountType;
import Customers.Customer;

public interface CustomerManagerInterface {
    void addCustomer(Customer newCustomer);
    void deleteCustomer(String ownerId);
    void listAllCustomers();
    void listAccountsByOwnerID(String ownerID);
    void addAccount(Account account, AccountType type);
    void deleteAccount(String ownerID, String iban);
    Account getAccount(String ownerID, String iban);
}
