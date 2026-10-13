package model;

public class Warehouse {

    private String warehouseId;
    private String name;
    private String location;
    private int capacity;

    public Warehouse(String warehouseId,
                     String name,
                     String location,
                     int capacity) {

        this.warehouseId = warehouseId;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}