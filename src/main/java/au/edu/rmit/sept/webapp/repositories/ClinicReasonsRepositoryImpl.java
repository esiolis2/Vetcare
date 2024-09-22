package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.ClinicReasons;
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
public class ClinicReasonsRepositoryImpl implements ClinicReasonsRepository{

    @Autowired
    private final DataSource dataSource;
    public ClinicReasonsRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public List<ClinicReasons> findAll() {
        List<ClinicReasons> reasonsList = new ArrayList<>();
        try {
            Connection connection = this.dataSource.getConnection();
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM CLINIC_SERVICE");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ClinicReasons reason = new ClinicReasons();
                reason.setId(rs.getInt("id"));
                reason.setServiceName(rs.getString("service_name"));
                reasonsList.add(reason);
            }
        }
        catch (SQLException e) {
            throw new UncategorizedScriptException("Error retrieving clinic reasons", e);
        }
        return reasonsList;

    }
}
