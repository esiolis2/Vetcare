package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.VaccinationRecord;
import au.edu.rmit.sept.webapp.repositories.VaccinationRecordRepository;
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
                VaccinationRecord vaccinationRecord = new VaccinationRecord();
                vaccinationRecord.setVaccinationID(rs.getLong("VaccinationID"));
                vaccinationRecord.setVaccineName(rs.getString("VaccineName"));
                vaccinationRecord.setAdministeredDate(rs.getDate("AdministeredDate").toLocalDate());
                vaccinationRecord.setNextDueDate(rs.getDate("NextDueDate") != null ? rs.getDate("NextDueDate").toLocalDate() : null);
                vaccinationRecord.setBoosterRequired(rs.getString("BoosterRequired"));
                vaccinationRecords.add(vaccinationRecord);
            }
        } catch (SQLException e) {
            throw new UncategorizedScriptException("Error fetching vaccination records", e);
        }
        return vaccinationRecords;
    }

    @Override
    public void addVaccinationRecord(VaccinationRecord vaccinationRecord) {
        String query = "INSERT INTO VaccinationRecords (PetID, VaccineName, AdministeredDate, NextDueDate, BoosterRequired) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, vaccinationRecord.getPetID());
            stmt.setString(2, vaccinationRecord.getVaccineName());
            stmt.setDate(3, java.sql.Date.valueOf(vaccinationRecord.getAdministeredDate()));
            stmt.setDate(4, vaccinationRecord.getNextDueDate() != null ? java.sql.Date.valueOf(vaccinationRecord.getNextDueDate()) : null);
            stmt.setString(5, vaccinationRecord.getBoosterRequired());
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
                VaccinationRecord vaccinationRecord = new VaccinationRecord();
                vaccinationRecord.setVaccinationID(rs.getLong("VaccinationID"));
                vaccinationRecord.setVaccineName(rs.getString("VaccineName"));
                vaccinationRecord.setAdministeredDate(rs.getDate("AdministeredDate").toLocalDate());
                vaccinationRecord.setNextDueDate(rs.getDate("NextDueDate") != null ? rs.getDate("NextDueDate").toLocalDate() : null);
                vaccinationRecord.setBoosterRequired(rs.getString("BoosterRequired"));
                vaccinationRecords.add(vaccinationRecord);
            }
        } catch (SQLException e) {
            throw new UncategorizedScriptException("Error fetching all vaccination records", e);
        }
        return vaccinationRecords;
    }
}
