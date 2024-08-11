package gasBooking;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Delivery extends Booking {

    public String delPersonName;
    int customerOtp;

    public Delivery(String name, String street, String area, int pinCode, String mobile, int numOfCylinders) {
        super(name, street, area, pinCode, mobile, numOfCylinders);
    }

    public void amountCalc() {
        long dayDiff = dt_2.getTime() - dt_1.getTime();
        long newDiff = TimeUnit.DAYS.convert(dayDiff, TimeUnit.MILLISECONDS);

        if (newDiff > 7) {
            refund = 42;
            amount = amount - refund;
        }
    }

    public void verifyOtp() {
        if (status.equals("booked")) {
            System.out.println("Enter OTP: ");
            customerOtp = new Scanner(System.in).nextInt();

            if (customerOtp != otp) {
                status = "cancelled";
            } else {
                status = "delivered";
            }
        } else {
            System.out.println("I am Sorry, No Booking found!!");
        }
    }

    public void delPersonDetails() {
        System.out.println("\nEnter the Delivery person Name: ");
        delPersonName = new Scanner(System.in).nextLine();
    }
}
