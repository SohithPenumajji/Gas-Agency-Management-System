package main;


import gasBooking.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    static int bcount = 0, ccount = 0, dcount = 0, pcount = 0;
    static String dpName;

    public static void cylinderCount(Delivery[] deliveries) {
        String[] months = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        for (Delivery delivery : deliveries) {
            int count = 0;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(delivery.dt_2);

            System.out.println("In the month of: " + months[calendar.get(Calendar.MONTH)]);
            System.out.println("In " + delivery.area);
            if (delivery.status.equals("delivered")) {
                count += delivery.numOfCylinders;
            }
            System.out.println("- " + count + " cylinders delivered\n");
        }
    }

    public static void checkLate(Delivery[] deliveries) {
        String[] months = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        int[] month = new int[12];

        for (Delivery delivery : deliveries) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(delivery.dt_2);
            int deliveryMonth = calendar.get(Calendar.MONTH);
            if (delivery.status.equals("delivered") && delivery.amount == 769) {
                month[deliveryMonth] += 1;
            }
        }

        System.out.println("----------Late Delivery----------");
        for (int i = 0; i < 12; i++) {
            if (month[i] != 0) {
                System.out.println("In " + months[i] + " there are " + month[i]);
            }
        }
        System.out.println("\n");
    }

    public static void numOfSingleCylinders(Delivery[] deliveries) {
        System.out.println("----------Single Cylinder Holders----------");
        for (int i = 0; i < deliveries.length; i++) {
            if (deliveries[i].numOfCylinders == 1) {
                System.out.println("* Customer Name: " + deliveries[i].name);
                System.out.println("* Mobile Number: " + deliveries[i].mobNum);
                System.out.println("* Gas Connection Number: " + (i + 101));
            }
        }
        System.out.println("\n");
    }

    public static void DeliveryDetails(Delivery[] deliveries) {
        System.out.println("-----------Delivery Details-------------");
        System.out.println("Enter the name of the delivery person: ");
        dpName = new Scanner(System.in).nextLine();

        for (Delivery delivery : deliveries) {
            if (delivery.status.equals("delivered") && delivery.delPersonName.equals(dpName)) {
                System.out.println("* Customer Name: " + delivery.name);
                System.out.println(" - " + delivery.street + " " + delivery.area + " " + delivery.pinCode);
            }
        }
        System.out.println("\n");
    }

    public static void printReport(Delivery[] deliveries) {
        for (int i = 0; i < deliveries.length; i++) {
            if (deliveries[i].status.equals("delivered")) {
                dcount++;
            } else if (deliveries[i].status.equals("booked")) {
                bcount++;
            } else if (deliveries[i].status.equals("cancelled")) {
                ccount++;
            } else if (deliveries[i].status.equals("pending")) {
                pcount++;
            } else {
                System.out.println("Status Invalid");
            }
        }

        System.out.println("* Report");
        System.out.println(" - " + bcount + " booked");
        System.out.println(" - " + dcount + " delivered");
        System.out.println(" - " + ccount + " cancelled");
        System.out.println(" - " + pcount + " pending");
        System.out.println("\n");
    }

    public static void printInvoice(Delivery[] deliveries) {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String invoiceDate = sdf.format(d);

        for (int i = 0; i < deliveries.length; i++) {
            if (deliveries[i].status.equals("delivered")) {
                System.out.println("-----------------------------------------------------------------------------------");
                System.out.println("                                    INVOICE                                        ");
                System.out.println("-----------------------------------------------------------------------------------");
                System.out.println("Gas Agency Code: " + gasSupplier.gasAgency.agencyCode + "\t\t\t" + " Date of Invoice: " + invoiceDate);
                System.out.println("Gas Agency Name: " + gasSupplier.gasAgency.agencyName + "\t\t" + " Agency Phone Number: " + gasSupplier.gasAgency.phNumber);
                System.out.println("Gas Connection Number: " + (i + 101) + "\t\t\t" + " Customer Name: " + deliveries[i].name);
                System.out.println("Booking Date: " + sdf.format(deliveries[i].dt_1) + "\t\t" + " Customer Mobile Number: " + deliveries[i].mobNum);
                System.out.println("-----------------------------------------------------------------------------------");
                System.out.println("Amount: " + deliveries[i].amount);
                System.out.println("Refund: " + deliveries[i].refund);
                System.out.println("Total Amount: " + (deliveries[i].amount - deliveries[i].refund));
                System.out.println("-----------------------------------------------------------------------------------");
                System.out.println("Delivery Person Name: " + deliveries[i].delPersonName + "\t\t" + " Delivery Person Mobile: " + deliveries[i].delPhno);
                System.out.println("Delivery Date: " + sdf.format(deliveries[i].dt_2));
                System.out.println("-----------------------------------------------------------------------------------");
                System.out.println("\n\n");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("**********************************************************");
        System.out.println("                      GAS AGENCY                          ");
        System.out.println("**********************************************************");

        Delivery[] deliveries = new Delivery[5];
        deliveries[0] = new Delivery("Gopal", "4Th Avenue", "Sagar Nagar", 530009, "9010520154", 1);
        // Assuming you fill in the rest of the deliveries

        for (Delivery delivery : deliveries) {
            delivery.delPersonDetails();
            delivery.getDate();
            delivery.validate();
            delivery.amountCalc();
            delivery.verifyOtp();
        }

        System.out.println();
        cylinderCount(deliveries);
        checkLate(deliveries);
        numOfSingleCylinders(deliveries);
        DeliveryDetails(deliveries);
        printReport(deliveries);
        printInvoice(deliveries);
    }
}
