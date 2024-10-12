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
    public List<Appointment> findAll(Long userId) {
        List<Appointment> appointments = new ArrayList<>();
        try(  Connection connection = this.dataSource.getConnection();
              PreparedStatement stm = connection.prepareStatement("SELECT * FROM APPOINTMENT where ownerId = ?;");) {

            stm.setLong(1, userId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Timestamp dateStamp = rs.getTimestamp("appointmentDate");
                Timestamp timeStamp = rs.getTimestamp("appointmentTime");
                Long clinicId = rs.getLong("clinicId");
                // Split it into LocalDate and LocalTime
                LocalDate appointmentDate = dateStamp.toLocalDateTime().toLocalDate();
                LocalTime appointmentTime = timeStamp.toLocalDateTime().toLocalTime();
                Appointment appointment = new Appointment(
                        rs.getLong("id"), // id
                        rs.getLong("veterinarianId"), // vetId
                        rs.getLong("clinicId"), // clinic
                        rs.getLong("ownerId"),
                        appointmentTime,
                        appointmentDate,
                        rs.getLong("reason"),
                        rs.getLong("petId")); // petAge)
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
        String insertQuery = "INSERT INTO appointment (veterinarianId, clinicId, ownerId, petId, reason, appointmentTime, appointmentDate) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(insertQuery)) {

            stmt.setLong(1, appointment.getVeterinarianId());
            stmt.setLong(2, appointment.getClinicId());
            stmt.setLong(3, appointment.getUserId());
            stmt.setLong(4, appointment.getPetId());
            stmt.setLong(5, appointment.getReason());
            stmt.setTime(6, java.sql.Time.valueOf(appointment.getAppointmentTime())); // LocalTime to Time
            stmt.setDate(7, java.sql.Date.valueOf(appointment.getAppointmentDate())); // LocalDate to Date
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
    // Find appointment by appointment id
    public Appointment findById(Long id) {
        Appointment appointment = new Appointment();
        String query = "SELECT * FROM APPOINTMENT WHERE id=?";

        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setLong(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                appointment.setId(rs.getLong("id"));
                appointment.setVeterinarianId(rs.getLong("veterinarianId"));
                appointment.setClinicId(rs.getLong("clinicId"));
                appointment.setUserId(rs.getLong("ownerId"));
                appointment.setPetId(rs.getLong("petId"));
                appointment.setReason(rs.getLong("reason"));
                appointment.setAppointmentTime(rs.getTime("appointmentTime").toLocalTime()); // Time to LocalTime
                appointment.setAppointmentDate(rs.getDate("appointmentDate").toLocalDate()); // Date to LocalDate
            }
        } catch (SQLException e) {
            throw new UncategorizedScriptException("Error in findById", e);
        }
        return appointment;
    }

    @Override
    public List<Appointment> findAllByVetId(Long vetId) {
        List<Appointment> appointments = new ArrayList<>();
        try(  Connection connection = this.dataSource.getConnection();
              PreparedStatement stm = connection.prepareStatement("SELECT * FROM APPOINTMENT where veterinarianid = ?;")) {

            stm.setLong(1, vetId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Timestamp dateStamp = rs.getTimestamp("appointmentDate");
                Timestamp timeStamp = rs.getTimestamp("appointmentTime");
                Long clinicId = rs.getLong("clinicId");
                // Split it into LocalDate and LocalTime
                LocalDate appointmentDate = dateStamp.toLocalDateTime().toLocalDate();
                LocalTime appointmentTime = timeStamp.toLocalDateTime().toLocalTime();
                Appointment appointment = new Appointment(
                        rs.getLong("id"), // id
                        rs.getLong("veterinarianId"), // vetId
                        rs.getLong("clinicId"), // clinic
                        rs.getLong("ownerId"),
                        appointmentTime,
                        appointmentDate,
                        rs.getLong("reason"),
                        rs.getLong("petId")); // petAge)
                appointments.add(appointment);
            }
            connection.close();
            return appointments;
        } catch (SQLException e) {
            throw new UncategorizedScriptException("Error in findAllByVetId", e);
        }
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
