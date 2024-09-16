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
