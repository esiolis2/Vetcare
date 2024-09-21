package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.init.UncategorizedScriptException;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@Primary
public class AppointmentRepositoryImpl implements AppointmentRepository {

    @Autowired
    private final DataSource dataSource;
    public AppointmentRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Appointment> findAll() {
        try {
            Connection connection = this.dataSource.getConnection();
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM APPOINTMENT;");
            List<Appointment> appointments = new ArrayList<>();
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Timestamp timestamp = rs.getTimestamp("appointmentTime");
                Long clinicId = rs.getLong("clinic");
                // Split it into LocalDate and LocalTime
                LocalDate appointmentDate = timestamp.toLocalDateTime().toLocalDate();
                LocalTime appointmentTime = timestamp.toLocalDateTime().toLocalTime();
                Appointment appointment = new Appointment(
                        rs.getLong("id"), // id
                        rs.getLong("veterinarianId"), // vetId
                        rs.getLong("clinicId"), // clinic
                        rs.getString("ownerName"), // ownerName
                        rs.getString("email"), // email
                        rs.getString("phone"), // phone
                        appointmentTime,
                        appointmentDate,
                        rs.getString("reason"), // reason
                        rs.getString("petName"), // petName
                        rs.getString("petType"), // petType
                        rs.getInt("petAge")); // petAge)
                appointments.add(appointment);
            }
            connection.close();
            return appointments;
        } catch (SQLException e) {
            throw new UncategorizedScriptException("Error in findAll", e);
        }
    }

    // https://www.tutorialspoint.com/jdbc/jdbc-insert-records.htm
    @Override
    public Appointment addAppointment(Appointment appointment) {
        String insertQuery = "INSERT INTO appointment (veterinarianId, clinicId, ownerName, email, phone, petName, petType, petAge, reason, appointmentTime, appointmentDate) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(insertQuery)) {

            stmt.setLong(1, appointment.getVeterinarianId());
            stmt.setLong(2, appointment.getClinicId());
            stmt.setString(3, appointment.getOwnerName());
            stmt.setString(4, appointment.getEmail());
            stmt.setString(5, appointment.getPhone());
            stmt.setString(6, appointment.getPetName());
            stmt.setString(7, appointment.getPetType());
            stmt.setInt(8, appointment.getPetAge());
            stmt.setString(9, appointment.getReason());
            stmt.setTime(10, java.sql.Time.valueOf(appointment.getAppointmentTime())); // LocalTime to Time
            stmt.setDate(11, java.sql.Date.valueOf(appointment.getAppointmentDate())); // LocalDate to Date
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Appointment successfully added.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new UncategorizedScriptException("Error inserting appointment", e);
        }

        return appointment;
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) {
        String updateQuery = "UPDATE appointment SET appointmentTime = ?, appointmentDate = ?, veterinarianId = ? WHERE id = ?";
        try(
                Connection connection = this.dataSource.getConnection();
                PreparedStatement stm = connection.prepareStatement(updateQuery)){
                stm.setTime(1, java.sql.Time.valueOf(appointment.getAppointmentTime())); // LocalTime to Time
                stm.setDate(2, java.sql.Date.valueOf(appointment.getAppointmentDate())); // LocalDate to Date
                stm.setLong(3, appointment.getVeterinarianId());
                stm.setLong(4, appointment.getId());

                int rowsAffected = stm.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Appointment successfully rescheduled.");
                } else {
                    System.out.println("No appointment found with the given ID.");
                }
        }
        catch (SQLException e){
            throw new IllegalArgumentException("Cannot update appointment", e);
        }
        return appointment;
    }

    @Override
    public Appointment findById(Long id) {
        Appointment appointment;
        try {
            Connection connection = this.dataSource.getConnection();
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM APPOINTMENT WHERE id=?;");
            appointment = new Appointment();
            stm.setLong(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                appointment.setId(rs.getLong("id"));
                appointment.setVeterinarianId(rs.getLong("veterinarianId"));
                appointment.setClinicId(rs.getLong("clinicId"));
                appointment.setOwnerName(rs.getString("ownerName"));
                appointment.setEmail(rs.getString("email"));
                appointment.setPhone(rs.getString("phone"));
                appointment.setPetName(rs.getString("petName"));
                appointment.setPetType(rs.getString("petType"));
                appointment.setPetAge(rs.getInt("petAge"));
                appointment.setReason(rs.getString("reason"));
                appointment.setAppointmentTime(rs.getTime("appointmentTime").toLocalTime()); // Time to LocalTime
                appointment.setAppointmentDate(rs.getDate("appointmentDate").toLocalDate()); // Date to LocalDate
            }
        } catch (SQLException e) {
            throw new UncategorizedScriptException("Error in findById", e);
        }
        return appointment;
    }

    @Override
    public boolean removeAppointment(Long appointmentId) {
        String deleteQuery = "DELETE FROM appointment WHERE id = ?";

        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(deleteQuery)) {

            stmt.setLong(1, appointmentId);  // Set the appointment ID to delete

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Appointment successfully deleted.");
            } else {
                System.out.println("No appointment found with the provided ID.");
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
            throw new UncategorizedScriptException("Error deleting appointment", e);
        }
        return true;
    }


}
