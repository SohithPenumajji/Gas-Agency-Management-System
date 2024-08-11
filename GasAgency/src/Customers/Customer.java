package Customers;

import gasSupplier.gasAgency;

public class Customer implements gasAgency {

    public String name;
    public String street;
    public String area;
    public int pinCode;
    public String mobNum;

    public Customer(String name, String street, String area, int pinCode, String mobile) {
        this.name = name;
        this.street = street;
        this.area = area;
        this.pinCode = pinCode;
        this.mobNum = mobile;
    }
}
