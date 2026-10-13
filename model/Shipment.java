package model;

public class Shipment {

    private String shipmentId;
    private String customerName;
    private String packageCode;
    private String source;
    private String destination;
    private double weight;
    private String status;

    public Shipment(String shipmentId,
                    String customerName,
                    String packageCode,
                    String source,
                    String destination,
                    double weight) {

        this.shipmentId = shipmentId;
        this.customerName = customerName;
        this.packageCode = packageCode;
        this.source = source;
        this.destination = destination;
        this.weight = weight;
        this.status = "Created";
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}