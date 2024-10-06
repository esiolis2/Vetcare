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
                TreatmentPlan treatmentPlan = mapSetToTreatmentPlan(rs);
                treatmentPlans.add(treatmentPlan);
            }
        } catch (SQLException e) {
            throw new UncategorizedScriptException("Error fetching all treatment plans", e);
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
                TreatmentPlan treatmentPlan = mapSetToTreatmentPlan(rs);
                treatmentPlans.add(treatmentPlan);
            }
        } catch (SQLException e) {
            throw new UncategorizedScriptException("Error fetching treatment plans by Pet ID", e);
        }
        return treatmentPlans;
    }

    @Override
    public void addTreatmentPlan(TreatmentPlan treatmentPlan) {
        String query = "INSERT INTO TreatmentPlans (PetID, Diagnosis, TreatmentType, TreatmentDescription, PetCondition, " +
                "IsEmergency, StartDate, EndDate, PrescribedMedications, TreatmentDuration, NextSteps, VetName, FollowUpDate, " +
                "CostEstimate, TreatmentOutcome, TreatmentNotes, ClinicLocation, IsInsured, InsuranceDetails, PaymentStatus) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, treatmentPlan.getPet().getPetID());
            stmt.setString(2, treatmentPlan.getDiagnosis());
            stmt.setString(3, treatmentPlan.getTreatmentType());
            stmt.setString(4, treatmentPlan.getTreatmentDescription());
            stmt.setString(5, treatmentPlan.getPetCondition());
            stmt.setBoolean(6, treatmentPlan.getIsEmergency());
            stmt.setDate(7, java.sql.Date.valueOf(treatmentPlan.getStartDate()));
            stmt.setDate(8, treatmentPlan.getEndDate() != null ? java.sql.Date.valueOf(treatmentPlan.getEndDate()) : null);
            stmt.setString(9, treatmentPlan.getPrescribedMedications());
            stmt.setString(10, treatmentPlan.getTreatmentDuration());
            stmt.setString(11, treatmentPlan.getNextSteps());
            stmt.setString(12, treatmentPlan.getVetName());
            stmt.setDate(13, treatmentPlan.getFollowUpDate() != null ? java.sql.Date.valueOf(treatmentPlan.getFollowUpDate()) : null);
            stmt.setDouble(14, treatmentPlan.getCostEstimate());
            stmt.setString(15, treatmentPlan.getTreatmentOutcome());
            stmt.setString(16, treatmentPlan.getTreatmentNotes());
            stmt.setString(17, treatmentPlan.getClinicLocation());
            stmt.setBoolean(18, treatmentPlan.getIsInsured());
            stmt.setString(19, treatmentPlan.getInsuranceDetails());
            stmt.setString(20, treatmentPlan.getPaymentStatus());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Treatment plan successfully added.");
            }


        } catch (SQLException e) {
            throw new UncategorizedScriptException("Error inserting new treatment plan", e);
        }
    }

    //using to avoid the duplicates...
    private TreatmentPlan mapSetToTreatmentPlan(ResultSet rs) throws SQLException {
        return new TreatmentPlan(
                rs.getLong("TreatmentPlanID"),
                rs.getString("Diagnosis"),
                rs.getString("TreatmentType"),
                rs.getString("TreatmentDescription"),
                rs.getString("PetCondition"),
                rs.getBoolean("IsEmergency"),
                rs.getDate("StartDate") != null ? rs.getDate("StartDate").toLocalDate() : null,
                rs.getDate("EndDate") != null ? rs.getDate("EndDate").toLocalDate() : null,
                rs.getString("PrescribedMedications"),
                rs.getString("TreatmentDuration"),
                rs.getString("NextSteps"),
                rs.getString("VetName"),
                rs.getDate("FollowUpDate") != null ? rs.getDate("FollowUpDate").toLocalDate() : null,
                rs.getDouble("CostEstimate"),
                rs.getString("TreatmentOutcome"),
                rs.getString("TreatmentNotes"),
                rs.getString("ClinicLocation"),
                rs.getBoolean("IsInsured"),
                rs.getString("InsuranceDetails"),
                rs.getString("PaymentStatus"),
                rs.getTimestamp("CreatedAt").toLocalDateTime().toLocalDate(),
                rs.getTimestamp("UpdatedAt").toLocalDateTime().toLocalDate()
        );
    }




    @Override
    public void updateTreatmentPlan(TreatmentPlan treatmentPlan) {
        String query = "UPDATE TreatmentPlans SET Diagnosis = ?, TreatmentType = ?, PetCondition = ?, " +
                "IsEmergency = ?, StartDate = ?, EndDate = ?, PrescribedMedications = ?, " +
                "TreatmentDuration = ?, NextSteps = ?, VetName = ?, FollowUpDate = ?, CostEstimate = ?, " +
                "TreatmentOutcome = ?, ClinicLocation = ?, IsInsured = ?, InsuranceDetails = ?, " +
                "PaymentStatus = ? WHERE TreatmentPlanID = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, treatmentPlan.getDiagnosis());
            stmt.setString(2, treatmentPlan.getTreatmentType());
            stmt.setString(3, treatmentPlan.getPetCondition());
            stmt.setBoolean(4, treatmentPlan.getIsEmergency());
            stmt.setDate(5, java.sql.Date.valueOf(treatmentPlan.getStartDate()));
            stmt.setDate(6, treatmentPlan.getEndDate() != null ? java.sql.Date.valueOf(treatmentPlan.getEndDate()) : null);
            stmt.setString(7, treatmentPlan.getPrescribedMedications());
            stmt.setString(8, treatmentPlan.getTreatmentDuration());
            stmt.setString(9, treatmentPlan.getNextSteps());
            stmt.setString(10, treatmentPlan.getVetName());
            stmt.setDate(11, treatmentPlan.getFollowUpDate() != null ? java.sql.Date.valueOf(treatmentPlan.getFollowUpDate()) : null);
            stmt.setDouble(12, treatmentPlan.getCostEstimate());
            stmt.setString(13, treatmentPlan.getTreatmentOutcome());
            stmt.setString(14, treatmentPlan.getClinicLocation());
            stmt.setBoolean(15, treatmentPlan.getIsInsured());
            stmt.setString(16, treatmentPlan.getInsuranceDetails());
            stmt.setString(17, treatmentPlan.getPaymentStatus());
            stmt.setLong(18, treatmentPlan.getTreatmentPlanID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating treatment plan", e);
        }
    }

    @Override
    public List<TreatmentPlan> findByUserId(Long userId) {
        String query = "SELECT tp.* FROM TreatmentPlans tp "
                + "JOIN pet_info p ON tp.PetID = p.PetID "
                + "WHERE p.OwnerId = ?";

        List<TreatmentPlan> treatmentPlans = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TreatmentPlan treatmentPlan = mapSetToTreatmentPlan(rs);
                treatmentPlans.add(treatmentPlan);
            }
        } catch (SQLException e) {
            throw new UncategorizedScriptException("Error fetching treatment plans by User ID", e);
        }
        return treatmentPlans;
    }
}

















//    @Override
//    public void updateTreatmentPlan(TreatmentPlan treatmentPlan) {
//        StringBuilder queryBuilder = new StringBuilder("UPDATE TreatmentPlans SET ");
//        List<Object> parameters = new ArrayList<>();
//
//        if (treatmentPlan.getDiagnosis() != null) {
//            queryBuilder.append("Diagnosis = ?, ");
//            parameters.add(treatmentPlan.getDiagnosis());
//        }
//        if (treatmentPlan.getTreatmentType() != null) {
//            queryBuilder.append("TreatmentType = ?, ");
//            parameters.add(treatmentPlan.getTreatmentType());
//        }
//        if (treatmentPlan.getPetCondition() != null) {
//            queryBuilder.append("PetCondition = ?, ");
//            parameters.add(treatmentPlan.getPetCondition());
//        }
//        if (treatmentPlan.getIsEmergency() != null) {
//            queryBuilder.append("IsEmergency = ?, ");
//            parameters.add(treatmentPlan.getIsEmergency());
//        }
//        if (treatmentPlan.getStartDate() != null) {
//            queryBuilder.append("StartDate = ?, ");
//            parameters.add(java.sql.Date.valueOf(treatmentPlan.getStartDate()));
//        }
//        if (treatmentPlan.getEndDate() != null) {
//            queryBuilder.append("EndDate = ?, ");
//            parameters.add(java.sql.Date.valueOf(treatmentPlan.getEndDate()));
//        }
//        if (treatmentPlan.getPrescribedMedications() != null) {
//            queryBuilder.append("PrescribedMedications = ?, ");
//            parameters.add(treatmentPlan.getPrescribedMedications());
//        }
//        if (treatmentPlan.getTreatmentDuration() != null) {
//            queryBuilder.append("TreatmentDuration = ?, ");
//            parameters.add(treatmentPlan.getTreatmentDuration());
//        }
//        if (treatmentPlan.getNextSteps() != null) {
//            queryBuilder.append("NextSteps = ?, ");
//            parameters.add(treatmentPlan.getNextSteps());
//        }
//        if (treatmentPlan.getVetName() != null) {
//            queryBuilder.append("VetName = ?, ");
//            parameters.add(treatmentPlan.getVetName());
//        }
//        if (treatmentPlan.getFollowUpDate() != null) {
//            queryBuilder.append("FollowUpDate = ?, ");
//            parameters.add(java.sql.Date.valueOf(treatmentPlan.getFollowUpDate()));
//        }
//        if (treatmentPlan.getCostEstimate() != null) {
//            queryBuilder.append("CostEstimate = ?, ");
//            parameters.add(treatmentPlan.getCostEstimate());
//        }
//        if (treatmentPlan.getTreatmentOutcome() != null) {
//            queryBuilder.append("TreatmentOutcome = ?, ");
//            parameters.add(treatmentPlan.getTreatmentOutcome());
//        }
//        if (treatmentPlan.getClinicLocation() != null) {
//            queryBuilder.append("ClinicLocation = ?, ");
//            parameters.add(treatmentPlan.getClinicLocation());
//        }
//        if (treatmentPlan.getIsInsured() != null) {
//            queryBuilder.append("IsInsured = ?, ");
//            parameters.add(treatmentPlan.getIsInsured());
//        }
//        if (treatmentPlan.getInsuranceDetails() != null) {
//            queryBuilder.append("InsuranceDetails = ?, ");
//            parameters.add(treatmentPlan.getInsuranceDetails());
//        }
//        if (treatmentPlan.getPaymentStatus() != null) {
//            queryBuilder.append("PaymentStatus = ?, ");
//            parameters.add(treatmentPlan.getPaymentStatus());
//        }
//
//        // Remove the last comma and space from the query string
//        queryBuilder.setLength(queryBuilder.length() - 2);
//        queryBuilder.append(" WHERE TreatmentPlanID = ?");
//
//        parameters.add(treatmentPlan.getTreatmentPlanID());
//
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement stmt = connection.prepareStatement(queryBuilder.toString())) {
//
//            // Set the parameters for the prepared statement
//            for (int i = 0; i < parameters.size(); i++) {
//                stmt.setObject(i + 1, parameters.get(i));
//            }
//
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException("Error updating treatment plan", e);
//        }
//    }