package Bank;

public interface BankInterface {

    void setName(String name);
    void setAddress(String address);
    String getName();
    String getAddress();
    void showBankInformation();
    void withdrawFromAccount(String ownerID, String iban, double amount);
    void depositToAccount(String ownerID, String iban, double amount);
    void transfer(String ownerID, String fromIban, String customerIDToTransfer, String toIban, double amount);

}
