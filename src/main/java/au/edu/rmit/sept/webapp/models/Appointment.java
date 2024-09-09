package au.edu.rmit.sept.webapp.models;
import java.time.LocalDateTime;
public class Appointment {

    Long id;
    Long veterinarianId;
    Long petOwnerId;
    String clinic;
    String ownerName;
    String email;
    String phone;
    LocalDateTime appointmentTime;
    String reason;
    String petName;
    String petType;
    Integer petAge;

    public Appointment(Long id, Long veterinarianId, Long petOwnerId, String clinic, String ownerName, String email,
                       String phone, LocalDateTime appointmentTime, String reason, String petName, String petType, Integer petAge) {
        this.id = id;
        this.veterinarianId = veterinarianId;
        this.petOwnerId = petOwnerId;
        this.clinic = clinic;
        this.ownerName = ownerName;
        this.email = email;
        this.phone = phone;
        this.appointmentTime = appointmentTime;
        this.reason = reason;
        this.petName = petName;
        this.petType = petType;
        this.petAge = petAge;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVeterinarianId() {
        return veterinarianId;
    }

    public void setVeterinarianId(Long veterinarianId) {
        this.veterinarianId = veterinarianId;
    }

    public Long getPetOwnerId() {
        return petOwnerId;
    }

    public void setPetOwnerId(Long petOwnerId) {
        this.petOwnerId = petOwnerId;
    }

    public String getClinic() {
        return clinic;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public Integer getPetAge() {
        return petAge;
    }

    public void setPetAge(Integer petAge) {
        this.petAge = petAge;
    }
}
