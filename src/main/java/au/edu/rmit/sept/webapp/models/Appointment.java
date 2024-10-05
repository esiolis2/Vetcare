package au.edu.rmit.sept.webapp.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {

    private Long id;
    private Long veterinarianId;
    private Long clinicId; // Changed to Clinic object
    private Long userId;
    private LocalTime appointmentTime;
    private LocalDate appointmentDate;
    private Long reason;
    private Long petId;

    // Constructors, getters, and setters...

    public Appointment(Long id, Long veterinarianId, Long clinicId, Long userId, LocalTime appointmentTime, LocalDate appointmentDate, Long reason, Long petId) {
        this.id = id;

        this.veterinarianId = veterinarianId;
        this.clinicId = clinicId;
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

    public Long getUserId(){
        return userId;
    }
    public void setUserId(Long userId){
        this.userId = userId;
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

    public Long getReason() {
        return reason;
    }

    public void setReason(Long reason) {
        this.reason = reason;
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }


}
