package au.edu.rmit.sept.webapp.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {

    private Long id;
    private Long veterinarianId;
    private Long clinicId; // Changed to Clinic object
    private String ownerName;
    private String email;
    private String phone;
    private LocalTime appointmentTime;
    private LocalDate appointmentDate;
    private String reason;
    private String petName;
    private String petType;
    private Integer petAge;

    // Constructors, getters, and setters...

    public Appointment(Long id, Long veterinarianId, Long clinicId, String ownerName, String email,
                       String phone, LocalTime appointmentTime, LocalDate appointmentDate, String reason,
                       String petName, String petType, Integer petAge) {
        this.id = id;
        this.veterinarianId = veterinarianId;
        this.clinicId = clinicId;
        this.ownerName = ownerName;
        this.email = email;
        this.phone = phone;
        this.appointmentTime = appointmentTime;
        this.appointmentDate = appointmentDate;
        this.reason = reason;
        this.petName = petName;
        this.petType = petType;
        this.petAge = petAge;
    }

    public Appointment() {}

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

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
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

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
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
