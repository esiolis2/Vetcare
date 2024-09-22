package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.ClinicReasons;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClinicReasonsService {
    public List<ClinicReasons> getAllClinicReasons();
}
