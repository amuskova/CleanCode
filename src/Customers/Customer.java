package Customers;

public class Customer implements CustomerInterface{
    private String id;
    private String name;
    private String address;

    public Customer(String id, String name, String address){
        this.setID(id);
        this.setName(name);
        this.setAddress(address);
    }

    @Override
    public String getID() {
        return this.id;
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
    public void setID(String id) {
        this.id = id;
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
    public void displayCustomerInformation() {
        System.out.println("ID: " + this.getID());
        System.out.println("Customer name: " + this.getName());
        System.out.println("Customer address: " + this.getAddress());
    }
}
