package gasBooking;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Booking extends GasConnection {

    public double otp = 5678, amount = 811, refund = 0;
    public String dt, delDate, status, delPhno = "123642";
    public Date dt_1, dt_2;

    public Booking(String name, String street, String area, int pinCode, String mobile, int numOfCylinders) {
        super(name, street, area, pinCode, mobile, numOfCylinders);
    }

    public void getDate() {
        System.out.println("Enter Booking Date (dd/MM/yyyy): ");
        dt = new Scanner(System.in).next(); 
        dt_1 = null;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            dt_1 = dateFormat.parse(dt);
        } catch (Exception ex) {
            System.out.println("Error in entering Booking Date: " + ex);
        }

        System.out.println("Enter Delivery Date (dd/MM/yyyy): ");
        delDate = new Scanner(System.in).nextLine();

        try {
            dt_2 = dateFormat.parse(delDate);
        } catch (Exception ex) {
            System.out.println("Error parsing Delivery Date: " + ex);
        }

        try {
            long difference = dt_2.getTime() - dt_1.getTime();
            long newDifference = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
            if (newDifference > 7) {
                status = "pending";
            } else {
                status = "booked";
            }
        } catch (Exception e) {
            System.out.println("Error while finding date difference: " + e);
        }
    }

    public void validate() {
        long gap = dt_1.getTime() - getLastDate().getTime(); 
        long diff = TimeUnit.DAYS.convert(gap, TimeUnit.MILLISECONDS);

        System.out.println("Difference between two dates is: " + diff);

        if (getNumOfCylinders() == 1) { 
            if (diff < 30) {
                System.out.println("Booking cannot be done");
                status = "completed";
            } else {
                status = "booked";
                setLastDate(dt_1); 
            }
        } else if (getNumOfCylinders() == 2) { 
            if (diff > 50) {
                System.out.println("Booking cannot be done");
                status = "pending";
            } else {
                status = "booked";
                setLastDate(dt_1); 
            }
        }
    }
}
