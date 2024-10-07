package au.edu.rmit.sept.webapp.models;

import java.time.LocalDateTime;
import java.util.List;

public class PrescriptionRefillRequest {

    private Long requestID; // Unique identifier for the refill request
    private Long petID; // ID of the pet associated with the request
    private List<String> medications; // List of medications requested for refill
    private String address; // Delivery address for the refill request
    private String city; // City of the delivery address
    private String state; // State of the delivery address
    private String postcode; // Postcode of the delivery address
    private String notes; // Additional notes or instructions for the refill request
    private LocalDateTime requestDate; // Date and time when the refill request was made

    // Full constructor (with requestID and requestDate)
    public PrescriptionRefillRequest(Long requestID, Long petID, List<String> medications, String address,
                                     String city, String state, String postcode, String notes, LocalDateTime requestDate) {
        this.requestID = requestID;
        this.petID = petID;
        this.medications = medications;
        this.address = address;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
        this.notes = notes;
        this.requestDate = requestDate;
    }

    // Default constructor
    public PrescriptionRefillRequest() {
        // Default constructor for initialization
    }

    // Getter and Setter methods

    public Long getRequestID() {
        return requestID;
    }

    public void setRequestID(Long requestID) {
        this.requestID = requestID;
    }

    public Long getPetID() {
        return petID;
    }

    public void setPetID(Long petID) {
        this.petID = petID;
    }

    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }
}
