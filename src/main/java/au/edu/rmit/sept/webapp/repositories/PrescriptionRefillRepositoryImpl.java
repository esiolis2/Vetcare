package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.PrescriptionRefillRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PrescriptionRefillRepositoryImpl implements PrescriptionRefillRepository {

    private final DataSource dataSource;

    @Autowired
    public PrescriptionRefillRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void saveRefillRequest(PrescriptionRefillRequest refillRequest) {
        String query = "INSERT INTO PrescriptionRefillRequests (PetID, Medications, Address, City, State, Postcode, Notes) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setLong(1, refillRequest.getPetID());
            stmt.setString(2, String.join(",", refillRequest.getMedications())); // Convert List<String> to comma-separated string
            stmt.setString(3, refillRequest.getAddress());
            stmt.setString(4, refillRequest.getCity());
            stmt.setString(5, refillRequest.getState());
            stmt.setString(6, refillRequest.getPostcode());
            stmt.setString(7, refillRequest.getNotes());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving prescription refill request", e);
        }
    }

    @Override
    public List<PrescriptionRefillRequest> findAllRefillRequests() {
        String query = "SELECT * FROM PrescriptionRefillRequests";
        List<PrescriptionRefillRequest> requests = new ArrayList<>();

        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Convert comma-separated string back to List<String>
                List<String> medications = List.of(rs.getString("Medications").split(","));

                PrescriptionRefillRequest request = new PrescriptionRefillRequest(
                        rs.getLong("RefillRequestID"),
                        rs.getLong("PetID"),
                        medications, // List<String> of medications
                        rs.getString("Address"),
                        rs.getString("City"),
                        rs.getString("State"),
                        rs.getString("Postcode"),
                        rs.getString("Notes"),
                        rs.getTimestamp("RequestDate").toLocalDateTime()
                );

                System.out.println("Fetched Request ID: " + request.getRequestID() +
                        ", Medications: " + request.getMedications());

                requests.add(request);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving prescription refill requests", e);
        }

        return requests;
    }

}

