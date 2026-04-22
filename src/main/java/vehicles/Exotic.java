package vehicles;

public class Exotic extends LuxuryVehicle {

    private String countryOfOrigin;

    public Exotic(String vehicleId, String brand, String model, String membershipRequired, String countryOfOrigin) {
        super(vehicleId, brand, model, membershipRequired);
        this.countryOfOrigin = countryOfOrigin;
    }

    @Override
    public double calculateRentalCost(int hours) {
        return 300.0 * hours;
    }

    public String getCountryOfOrigin() { return countryOfOrigin; }
}
