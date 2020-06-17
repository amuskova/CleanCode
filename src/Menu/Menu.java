package Menu;

import Accounts.Account;
import Accounts.AccountType;
import Accounts.CurrentAccount;
import Bank.Bank;
import Customers.Customer;
import Managers.CustomerManager;
import Managers.CustomerManagerInterface;
import java.util.Scanner;

public class Menu implements MenuInterface{
    private static final String BANK_NAME = "Unicredit";
    private static final String BANK_ADDRESS = "Sofia, bul. Ivan Asen, 12";
    private Scanner scanner = new Scanner(System.in);
    private Bank bank;
    private CustomerManagerInterface customerManager;
    private boolean shouldQuit;

    public Menu(){
        this.customerManager = new CustomerManager();
        this.bank = new Bank(BANK_NAME,BANK_ADDRESS, this.customerManager);
        this.shouldQuit = false;
    }

    @Override
    public void printMenu() {
        System.out.println("-----------Menu------------");
        this.printAdminOperations();
        this.printBankOperations();
        System.out.println("11 Quit");
    }

    @Override
    public void readInput() {
        while(!this.shouldQuit){
            this.printMenu();
            System.out.println("Choose between 1 and 11");
            int command = scanner.nextInt();
            System.out.println();
            Command(command);
        }
    }

    private void printAdminOperations(){
        System.out.println("Admin operations 1-6 \n");
        System.out.println("1. List Customers");
        System.out.println("2. Add new customer");
        System.out.println("3. Delete customer");
        System.out.println("4. List all accounts of specific customer");
        System.out.println("5. Add new account");
        System.out.println("6. Delete account \n");
    }
    private void printBankOperations(){
        System.out.println("Bank operations 7-10 \n");
        System.out.println("7. Withdraw from account");
        System.out.println("8. Deposit to account");
        System.out.println("9. Transfer");
        System.out.println("10 Display bank information \n");
    }

    private void Command(int command){
        switch (command){
            case 1:
                this.listCustomersCommand();
                break;
            case 2:
                this.addNewCustomerCommand();
                break;
            case 3:
                this.deleteCustomerCommand();
                break;
            case 4:
                this.listAllAccountsByCustomerIDCommand();
                break;
            case 5:
                this.addNewAccountCommand();
                break;
            case 6:
                this.deleteAccountCommand();
                break;
            case 7:
                this.withdrawFromAccountCommand();
                break;
            case 8:
                this.depositToAccountCommand();
                break;
            case 9:
                this.transferCommand();
                break;
            case 10:
                this.displayInformationForTheBankCommand();
                break;
            case 11:
                this.quitCommand();
                break;
            default:
                System.out.println("Invalid command!");
                break;
        }
    }
    private void listCustomersCommand(){
        this.customerManager.listAllCustomers();
    }
    private void addNewCustomerCommand(){
        System.out.println("Enter customer ID");
        String customerID = this.scanner.next();
        System.out.println("Enter customer name");
        String name = this.scanner.next();
        System.out.println("Enter the address of the customer");
        String address = scanner.next();
        Customer newCustomer = new Customer(customerID,name,address);
        this.customerManager.addCustomer(newCustomer);
    }
    private void deleteCustomerCommand(){
        System.out.println("Enter customer ID");
        String customerID = scanner.next();
        this.customerManager.deleteCustomer(customerID);
    }
    private void listAllAccountsByCustomerIDCommand(){
        System.out.println("Enter customer ID");
        String customerID = scanner.next();
        this.customerManager.listAccountsByOwnerID(customerID);
    }
    private void addNewAccountCommand(){
        System.out.println("Enter the type of the account");
        String accountType = scanner.next();
        System.out.println("Enter IBAN");
        String iban = scanner.next();
        System.out.println("Enter owner ID");
        String ID = scanner.next();
        System.out.println("Enter amount");
        double amount = scanner.nextDouble();
        AccountType type = AccountType.valueOf(accountType.toUpperCase());
        Account newAccount = new CurrentAccount(iban, ID, amount);
        this.customerManager.addAccount(newAccount,type);
    }
    private void deleteAccountCommand(){
        System.out.println("Enter owner ID");
        String ID = scanner.next();
        System.out.println("Enter the IBAN");
        String iban = scanner.next();
        this.customerManager.deleteAccount(ID, iban);
    }

    private void withdrawFromAccountCommand(){
        System.out.println("Enter owner ID");
        String ID = scanner.next();
        System.out.println("Enter IBAN");
        String iban = scanner.next();
        System.out.println("Enter amount");
        double amount = scanner.nextDouble();
        this.bank.withdrawFromAccount(ID, iban, amount);
    }

    private void depositToAccountCommand(){
        System.out.println("Enter ownerID");
        String ID = scanner.next();
        System.out.println("Enter IBAN");
        String ownerIBAN = scanner.next();
        System.out.println("Enter amount ");
        double amount = scanner.nextDouble();
        this.bank.depositToAccount(ID,ownerIBAN, amount);
    }
    private void transferCommand(){
        System.out.println("Enter sender ID");
        String senderID = scanner.next();
        System.out.println("Enter sender IBAN");
        String senderIBAN = scanner.next();
        System.out.println("Enter receiver ID");
        String receiverID = scanner.next();
        System.out.println("Enter receiver IBAN");
        String receiverIBAN = scanner.next();
        System.out.println("Enter amount");
        double amount = scanner.nextDouble();
        this.bank.transfer(senderID,senderIBAN,receiverID,receiverIBAN,amount);
    }
    private void displayInformationForTheBankCommand(){
        this.bank.showBankInformation();
    }
    private void quitCommand(){
        shouldQuit = true;
        System.out.println("Quiting");
    }
}
