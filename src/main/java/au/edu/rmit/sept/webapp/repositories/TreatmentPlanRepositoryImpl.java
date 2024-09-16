package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.TreatmentPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.init.UncategorizedScriptException;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TreatmentPlanRepositoryImpl implements TreatmentPlanRepository {

    private final DataSource dataSource;

    @Autowired
    public TreatmentPlanRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<TreatmentPlan> findAll() {
        List<TreatmentPlan> treatmentPlans = new ArrayList<>();
        String query = "SELECT * FROM TreatmentPlans";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                TreatmentPlan treatmentPlan = new TreatmentPlan(
                        rs.getLong("TreatmentPlanID"),
                        rs.getLong("PetID"),
                        rs.getString("TreatmentDescription"),
                        rs.getDate("StartDate").toLocalDate(),
                        rs.getString("PrescribedMedications"),
                        rs.getString("TreatmentDuration"),
                        rs.getString("NextSteps")
                );
                treatmentPlans.add(treatmentPlan);
            }
        } catch (SQLException e) {
            throw new UncategorizedScriptException("Error fetching treatment plans", e);
        }
        return treatmentPlans;
    }

    @Override
    public List<TreatmentPlan> findByPetId(Long petId) {
        String query = "SELECT * FROM TreatmentPlans WHERE PetID = ?";
        List<TreatmentPlan> treatmentPlans = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, petId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TreatmentPlan treatmentPlan = new TreatmentPlan(
                        rs.getLong("TreatmentPlanID"),
                        rs.getLong("PetID"),
                        rs.getString("TreatmentDescription"),
                        rs.getDate("StartDate").toLocalDate(),
                        rs.getString("PrescribedMedications"),
                        rs.getString("TreatmentDuration"),
                        rs.getString("NextSteps")
                );
                treatmentPlans.add(treatmentPlan);
            }
        } catch (SQLException e) {
            throw new UncategorizedScriptException("Error fetching treatment plans by pet ID", e);
        }
        return treatmentPlans;
    }


    @Override
    public void addTreatmentPlan(TreatmentPlan treatmentPlan) {
        String query = "INSERT INTO TreatmentPlans (PetID, TreatmentDescription, StartDate, PrescribedMedications, TreatmentDuration, NextSteps) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, treatmentPlan.getPetID());
            stmt.setString(2, treatmentPlan.getTreatmentDescription());
            stmt.setDate(3, java.sql.Date.valueOf(treatmentPlan.getStartDate()));
            stmt.setString(4, treatmentPlan.getPrescribedMedications());
            stmt.setString(5, treatmentPlan.getTreatmentDuration());
            stmt.setString(6, treatmentPlan.getNextSteps());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new UncategorizedScriptException("Error inserting treatment plan", e);
        }
    }
}
