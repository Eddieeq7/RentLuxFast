import vehicles.Supercar;
import vehicles.LuxurySUV;
import vehicles.Exotic;
import members.GoldMember;
import members.PlatinumMember;
import booking.Booking;

public class Main {

    public static void main(String[] args) {

        Supercar lambo = new Supercar("V001", "Lamborghini", "Huracan", "GOLD", 202);
        LuxurySUV rolls = new LuxurySUV("V002", "Rolls Royce", "Cullinan", "STANDARD", 5);
        Exotic bugatti = new Exotic("V003", "Bugatti", "Chiron", "PLATINUM", "France");

        GoldMember goldUser = new GoldMember("M001", "Eduardo");
        PlatinumMember platinumUser = new PlatinumMember("M002", "Jordan");

        Booking booking1 = new Booking(goldUser, lambo, 3, "123 Main St");
        Booking booking2 = new Booking(platinumUser, bugatti, 3, "456 Ocean Dr");

        System.out.println("=== BOOKINGS ===");

        System.out.println(goldUser.getName() + " booked " + lambo.getVehicleInfo());
        System.out.println("Total: $" + booking1.getFinalCost());
        System.out.println("Access to exclusive? " + goldUser.canAccessExclusive());

        System.out.println();

        System.out.println(platinumUser.getName() + " booked " + bugatti.getVehicleInfo());
        System.out.println("Total: $" + booking2.getFinalCost());
        System.out.println("Access to exclusive? " + platinumUser.canAccessExclusive());
    }
}
