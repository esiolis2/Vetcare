package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.Clinic;
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
public class ClinicRepositoryImpl implements ClinicRepository{

    @Autowired
    private final DataSource dataSource;


    public ClinicRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Clinic> findAll(){
        try{
            Connection connection = this.dataSource.getConnection();
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM CLINIC;");
            List<Clinic> clinics = new ArrayList<>();
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                Clinic clinic = new Clinic(
                        rs.getLong(1), //id
                        rs.getString(2), //name
                        rs.getString(3), //address
                        rs.getString(4) //phone
                );
                clinics.add(clinic);
            }
            return  clinics;
        }
        catch (SQLException e){
            throw new UncategorizedScriptException("Error in findAll", e);
        }

    }

    @Override
    public Clinic findClinicById(Long id) {
        String query = "SELECT * FROM CLINIC WHERE id = ?";
        try{
            Connection connection = this.dataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
                stmt.setLong(1, id);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    // Extract data from result set
                    Long clinicId = rs.getLong("id");
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone");
                    String email = rs.getString("email");
                    // Create and return a Clinic object
                    return new Clinic(clinicId, name, address, phone, email);
                }
            }

            catch (SQLException e) {
                e.printStackTrace();
                throw new UncategorizedScriptException("Error in fetching clinic by id", e);
            }

            return null; // Return null if no clinic is found
        }

}
