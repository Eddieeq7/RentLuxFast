package vehicles;

public class LuxurySUV extends LuxuryVehicle {

    private int seatingCapacity;

    public LuxurySUV(String vehicleId, String brand, String model, String membershipRequired, int seatingCapacity) {
        super(vehicleId, brand, model, membershipRequired);
        this.seatingCapacity = seatingCapacity;
    }

    @Override
    public double calculateRentalCost(int hours) {
        return 75.0 * hours;
    }

    public int getSeatingCapacity() { return seatingCapacity; }
}
