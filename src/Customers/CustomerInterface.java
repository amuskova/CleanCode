package Customers;

public interface CustomerInterface {
    String getID();
    String getName();
    String getAddress();
    void setID(String id);
    void setName(String name);
    void setAddress(String address);
    void displayCustomerInformation();
}
