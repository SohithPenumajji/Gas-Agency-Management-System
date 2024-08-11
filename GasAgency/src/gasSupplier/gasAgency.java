package gasSupplier;

public interface gasAgency {

    public String agencyName = "Indane Gas";
    public int agencyCode = 255483;
    public int phNumber = 288890;

    // Default method allows us to declare and define in the interface itself
    default void agencyDisplay() {
        System.out.println("The agency name is " + agencyName);
        System.out.println("The agency code is " + agencyCode);
        System.out.println("The agency phone number is " + phNumber);
    }
}
