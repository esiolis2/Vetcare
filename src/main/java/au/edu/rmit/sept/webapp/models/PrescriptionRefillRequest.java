package au.edu.rmit.sept.webapp.models;

import java.time.LocalDateTime;
import java.util.List;

public class PrescriptionRefillRequest {

    private Long requestID;
    private Long petID;
    private List<String> medications;
    private String address;
    private String city;
    private String state;
    private String postcode;
    private String notes;
    private LocalDateTime requestDate;


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
