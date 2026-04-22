package vehicles;

import interfaces.Rentable;

public abstract class LuxuryVehicle implements Rentable {

    private String vehicleId;
    private String brand;
    private String model;
    private boolean isAvailable;
    private String membershipRequired;

    public LuxuryVehicle(String vehicleId, String brand, String model, String membershipRequired) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.membershipRequired = membershipRequired;
        this.isAvailable = true;
    }

    public abstract double calculateRentalCost(int hours);

    public String getVehicleId() { return vehicleId; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public boolean isAvailable() { return isAvailable; }
    public String getMembershipRequired() { return membershipRequired; }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public String getVehicleInfo() {
        return brand + " " + model + " (" + vehicleId + ")";
    }
}
