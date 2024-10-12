package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.MedicalHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.init.UncategorizedScriptException;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MedicalHistoryRepositoryImpl implements MedicalHistoryRepository {

    @Autowired
    private final DataSource dataSource;

    public MedicalHistoryRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<MedicalHistory> findMedicalHistoryByPetId(Long petId) {
        String query = "SELECT * FROM MedicalHistory WHERE PetID = ?";
        List<MedicalHistory> medicalHistories = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, petId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                MedicalHistory history = new MedicalHistory(
                        rs.getLong("PetID"),
                        rs.getDate("LastVisitDate") != null ? rs.getDate("LastVisitDate").toLocalDate() : null,
                        rs.getString("LastDiagnosis"),
                        rs.getString("TreatmentProvided"),
                        rs.getString("MedicationsPrescribed"),
                        rs.getString("OngoingConditions"),
                        rs.getDate("NextScheduledVisit") != null ? rs.getDate("NextScheduledVisit").toLocalDate() : null
                );
                medicalHistories.add(history);
            }
        } catch (SQLException e) {
            throw new UncategorizedScriptException("Error fetching medical history", e);
        }
        return medicalHistories;
    }


    @Override
    public void addMedicalHistory(MedicalHistory medicalHistory) {
        String query = "INSERT INTO MedicalHistory (PetID, LastVisitDate, LastDiagnosis, TreatmentProvided, MedicationsPrescribed, OngoingConditions, NextScheduledVisit) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, medicalHistory.getPetID());

            if (medicalHistory.getLastVisitDate() != null) {
                stmt.setDate(2, Date.valueOf(medicalHistory.getLastVisitDate()));
            } else {
                stmt.setNull(2, java.sql.Types.DATE);
            }

            stmt.setString(3, medicalHistory.getLastDiagnosis());
            stmt.setString(4, medicalHistory.getTreatmentProvided());
            stmt.setString(5, medicalHistory.getMedicationsPrescribed());
            stmt.setString(6, medicalHistory.getOngoingConditions());

            if (medicalHistory.getNextScheduledVisit() != null) {
                stmt.setDate(7, Date.valueOf(medicalHistory.getNextScheduledVisit()));
            } else {
                stmt.setNull(7, java.sql.Types.DATE);
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new UncategorizedScriptException("Error inserting medical history", e);
        }
    }

}
