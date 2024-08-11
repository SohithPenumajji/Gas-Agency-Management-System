package gasBooking;

import Customers.Customer;
import java.util.Date;

public class GasConnection extends Customer {

    public int numOfCylinders;
    public Date lastDate;

    public GasConnection(String name, String street, String area, int pinCode, String mobile, int numOfCylinders) {
        super(name, street, area, pinCode, mobile);
        this.numOfCylinders = numOfCylinders;
        this.lastDate = new Date(); 
    }

    public int getNumOfCylinders() {
        return numOfCylinders;
    }

    public void setNumOfCylinders(int numOfCylinders) {
        this.numOfCylinders = numOfCylinders;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }
}
