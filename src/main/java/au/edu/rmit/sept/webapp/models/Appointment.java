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
    private Long petId;

    // Constructors, getters, and setters...

    public Appointment(Long id, Long veterinarianId, Long clinicId, String ownerName, String email,
                       String phone, LocalTime appointmentTime, LocalDate appointmentDate, String reason, Long petId) {
        this.id = id;
        this.veterinarianId = veterinarianId;
        this.clinicId = clinicId;
        this.ownerName = ownerName;
        this.email = email;
        this.phone = phone;
        this.appointmentTime = appointmentTime;
        this.appointmentDate = appointmentDate;
        this.reason = reason;
        this.petId = petId;
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

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }


}
