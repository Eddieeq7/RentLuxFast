package vehicles;

public class Supercar extends LuxuryVehicle {

    private int topSpeedMPH;

    public Supercar(String vehicleId, String brand, String model, String membershipRequired, int topSpeedMPH) {
        super(vehicleId, brand, model, membershipRequired);
        this.topSpeedMPH = topSpeedMPH;
    }

    @Override
    public double calculateRentalCost(int hours) {
        return 150.0 * hours;
    }

    public int getTopSpeedMPH() { return topSpeedMPH; }
}
