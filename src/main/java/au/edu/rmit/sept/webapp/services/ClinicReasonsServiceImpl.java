package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.ClinicReasons;
import au.edu.rmit.sept.webapp.repositories.ClinicReasonsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicReasonsServiceImpl implements ClinicReasonsService{
    private ClinicReasonsRepository clinicReasonsRepository;

    public ClinicReasonsServiceImpl(ClinicReasonsRepository clinicReasonsRepository){
        this.clinicReasonsRepository = clinicReasonsRepository;
    }
    @Override
    public List<ClinicReasons> getAllClinicReasons() {
        return clinicReasonsRepository.findAll();
    }
}
