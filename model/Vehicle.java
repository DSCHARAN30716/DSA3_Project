package model;

public class Vehicle {

    private String vehicleId;
    private String vehicleType;
    private int capacity;
    private String currentLocation;
    private String status;

    public Vehicle(String vehicleId,
                   String vehicleType,
                   int capacity,
                   String currentLocation,
                   String status) {

        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
        this.capacity = capacity;
        this.currentLocation = currentLocation;
        this.status = status;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}