package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.Prescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.init.UncategorizedScriptException;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PrescriptionRepositoryImpl implements PrescriptionRepository {

    private final DataSource dataSource;

    @Autowired
    public PrescriptionRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Prescription> findAll() {
        List<Prescription> prescriptions = new ArrayList<>();
        String query = "SELECT * FROM Prescriptions";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Prescription prescription = mapSetToPrescription(rs);
                prescriptions.add(prescription);
            }
        } catch (SQLException e) {
            throw new UncategorizedScriptException("Error fetching all prescriptions", e);
        }
        return prescriptions;
    }

    @Override
    public List<Prescription> findByPetId(Long petId) {
        String query = "SELECT * FROM Prescriptions WHERE PetID = ?";
        List<Prescription> prescriptions = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, petId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Prescription prescription = mapSetToPrescription(rs);
                prescriptions.add(prescription);
            }
        } catch (SQLException e) {
            throw new UncategorizedScriptException("Error fetching prescriptions by Pet ID", e);
        }
        return prescriptions;
    }

    @Override
    public void addPrescription(Prescription prescription) {
        String query = "INSERT INTO Prescriptions (PetID, MedicationName, Dosage, Instructions, NextRefillDate, " +
                "QuantityPrescribed, RefillCount, ExpiryDate, UpdatedAt) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            if (prescription.getUpdatedAt() == null) {
                prescription.setUpdatedAt(LocalDate.now());
            }

            stmt.setLong(1, prescription.getPet().getPetID());
            stmt.setString(2, prescription.getMedicationName());
            stmt.setString(3, prescription.getDosage());
            stmt.setString(4, prescription.getInstructions());
            stmt.setDate(5, prescription.getNextRefillDate() != null ? Date.valueOf(prescription.getNextRefillDate()) : null);
            stmt.setInt(6, prescription.getQuantityPrescribed());
            stmt.setInt(7, prescription.getRefillCount());
            stmt.setDate(8, prescription.getExpiryDate() != null ? Date.valueOf(prescription.getExpiryDate()) : null);
            stmt.setTimestamp(9, Timestamp.valueOf(prescription.getUpdatedAt().atStartOfDay()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new UncategorizedScriptException("Error inserting new prescription", e);
        }
    }



    private Prescription mapSetToPrescription(ResultSet rs) throws SQLException {

        return new Prescription(
                rs.getLong("PrescriptionID"),
                rs.getString("MedicationName"),
                rs.getString("Dosage"),
                rs.getString("Instructions"),
                rs.getDate("NextRefillDate") != null ? rs.getDate("NextRefillDate").toLocalDate() : null,
                rs.getInt("QuantityPrescribed"),
                rs.getInt("RefillCount"),
                rs.getDate("ExpiryDate") != null ? rs.getDate("ExpiryDate").toLocalDate() : null,
                null,
                rs.getTimestamp("UpdatedAt").toLocalDateTime().toLocalDate()
        );
    }




}

