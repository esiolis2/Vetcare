package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.ClinicServicePricing;
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

public class ClinicServicePricingRepositoryImpl implements ClinicServicePricingRepository{

    @Autowired
    private final DataSource dataSource;
    public ClinicServicePricingRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<ClinicServicePricing> findAllById(int id) {
        List<ClinicServicePricing> pricingList = new ArrayList<>();
        try {
            Connection connection = this.dataSource.getConnection();
            PreparedStatement stm = connection.prepareStatement("SELECT CSP.*, C.name AS clinic_name \n" +
                    "FROM CLINIC_SERVICE_PRICING CSP\n" +
                    "JOIN CLINIC C ON CSP.clinic_id = C.id\n" +
                    "WHERE CSP.service_id = ?\n");
            ResultSet rs = stm.executeQuery();
            stm.setInt(1, id);
            while (rs.next()) {
                ClinicServicePricing pricing = new ClinicServicePricing();
                pricing.setClinicId(rs.getLong("clinic_id"));
                pricing.setServiceId(rs.getLong("service_id"));
                pricing.setPrice(rs.getFloat("price"));
                pricing.setClinicName(rs.getString("clinic_name"));
                pricingList.add(pricing);
            }
        }
        catch (SQLException e){
            throw new UncategorizedScriptException("Error in find all service and price", e);
        }
        return pricingList;
    }

    @Override
    public List<ClinicServicePricing> findAll() {
        List<ClinicServicePricing> pricingList = new ArrayList<>();
        try {
            Connection connection = this.dataSource.getConnection();
            PreparedStatement stm = connection.prepareStatement("SELECT CSP.*, C.name AS clinic_name \n" +
                    "FROM CLINIC_SERVICE_PRICING CSP\n" +
                    "JOIN CLINIC C ON CSP.clinic_id = C.id\n");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ClinicServicePricing pricing = new ClinicServicePricing();
                pricing.setClinicId(rs.getLong("clinic_id"));
                pricing.setServiceId(rs.getLong("service_id"));
                pricing.setPrice(rs.getFloat("price"));
                pricing.setClinicName(rs.getString("clinic_name"));
                pricingList.add(pricing);
            }
        }
        catch (SQLException e){
            throw new UncategorizedScriptException("Error in find all service and price", e);
        }
        return pricingList;

    }
}
