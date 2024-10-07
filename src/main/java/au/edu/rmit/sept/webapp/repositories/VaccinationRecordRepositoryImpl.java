package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.VaccinationRecord;
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
public class VaccinationRecordRepositoryImpl implements VaccinationRecordRepository {

    private final DataSource dataSource;

    @Autowired
    public VaccinationRecordRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<VaccinationRecord> findVaccinationRecordByPetId(Long petId) {
        String query = "SELECT * FROM VaccinationRecords WHERE PetID = ?";
        List<VaccinationRecord> vaccinationRecords = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, petId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                VaccinationRecord vaccinationRecord = mapToVaccinationRecord(rs);
                vaccinationRecords.add(vaccinationRecord);
            }
        } catch (SQLException e) {
            throw new UncategorizedScriptException("Error fetching vaccination records by Pet ID", e);
        }
        return vaccinationRecords;
    }

    @Override
    public void addVaccinationRecord(VaccinationRecord vaccinationRecord) {
        String query = "INSERT INTO VaccinationRecords (PetID, VaccineName, AdministeredDate, NextDueDate, BoosterRequired, Dosage, VeterinarianName, ClinicName, Status, AdditionalNotes) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, vaccinationRecord.getPetID());
            stmt.setString(2, vaccinationRecord.getVaccineName());
            stmt.setDate(3, java.sql.Date.valueOf(vaccinationRecord.getAdministeredDate()));
            stmt.setDate(4, vaccinationRecord.getNextDueDate() != null ? java.sql.Date.valueOf(vaccinationRecord.getNextDueDate()) : null);
            stmt.setString(5, vaccinationRecord.getBoosterRequired());
            stmt.setBigDecimal(6, vaccinationRecord.getDosage());
            stmt.setString(7, vaccinationRecord.getVeterinarianName());
            stmt.setString(8, vaccinationRecord.getClinicName());
            stmt.setString(9, vaccinationRecord.getStatus());
            stmt.setString(10, vaccinationRecord.getAdditionalNotes());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new UncategorizedScriptException("Error inserting vaccination record", e);
        }
    }

    @Override
    public List<VaccinationRecord> findAllVaccinationRecords() {
        String query = "SELECT * FROM VaccinationRecords";
        List<VaccinationRecord> vaccinationRecords = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                VaccinationRecord vaccinationRecord = mapToVaccinationRecord(rs);
                vaccinationRecords.add(vaccinationRecord);
            }
        } catch (SQLException e) {
            throw new UncategorizedScriptException("Error fetching all vaccination records", e);
        }
        return vaccinationRecords;
    }

    private VaccinationRecord mapToVaccinationRecord(ResultSet rs) throws SQLException {
        VaccinationRecord vaccinationRecord = new VaccinationRecord();
        vaccinationRecord.setVaccinationID(rs.getLong("VaccinationID"));
        vaccinationRecord.setPetID(rs.getLong("PetID"));
        vaccinationRecord.setVaccineName(rs.getString("VaccineName"));
        vaccinationRecord.setAdministeredDate(rs.getDate("AdministeredDate").toLocalDate());
        vaccinationRecord.setNextDueDate(rs.getDate("NextDueDate") != null ? rs.getDate("NextDueDate").toLocalDate() : null);
        vaccinationRecord.setBoosterRequired(rs.getString("BoosterRequired"));
        vaccinationRecord.setDosage(rs.getBigDecimal("Dosage"));
        vaccinationRecord.setVeterinarianName(rs.getString("VeterinarianName"));
        vaccinationRecord.setClinicName(rs.getString("ClinicName"));
        vaccinationRecord.setStatus(rs.getString("Status"));
        vaccinationRecord.setAdditionalNotes(rs.getString("AdditionalNotes"));
        return vaccinationRecord;
    }

}



//    @Override
//    public void updateVaccinationRecord(VaccinationRecord vaccinationRecord) {
//        String query = "UPDATE VaccinationRecords SET VaccineName = ?, AdministeredDate = ?, NextDueDate = ?, " +
//                "BoosterRequired = ?, Dosage = ?, VeterinarianName = ?, ClinicName = ?, Status = ?, " +
//                "AdditionalNotes = ? WHERE VaccinationRecordID = ?";
//
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement stmt = connection.prepareStatement(query)) {
//
//            stmt.setString(1, vaccinationRecord.getVaccineName());
//            stmt.setDate(2, java.sql.Date.valueOf(vaccinationRecord.getAdministeredDate()));
//            stmt.setDate(3, vaccinationRecord.getNextDueDate() != null ? java.sql.Date.valueOf(vaccinationRecord.getNextDueDate()) : null);
//            stmt.setString(4, vaccinationRecord.getBoosterRequired());
//            stmt.setBigDecimal(5, vaccinationRecord.getDosage());
//            stmt.setString(6, vaccinationRecord.getVeterinarianName());
//            stmt.setString(7, vaccinationRecord.getClinicName());
//            stmt.setString(8, vaccinationRecord.getStatus());
//            stmt.setString(9, vaccinationRecord.getAdditionalNotes());
//            stmt.setLong(10, vaccinationRecord.getVaccinationRecordID());
//
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException("Error updating vaccination record", e);
//        }
//    }



