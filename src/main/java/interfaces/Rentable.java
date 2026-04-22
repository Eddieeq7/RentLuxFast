package interfaces;

public interface Rentable {
    double calculateRentalCost(int hours);
    boolean isAvailable();
    String getVehicleInfo();
}
