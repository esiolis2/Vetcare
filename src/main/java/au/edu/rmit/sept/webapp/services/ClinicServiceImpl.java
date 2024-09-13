package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.Clinic;
import au.edu.rmit.sept.webapp.repositories.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicServiceImpl implements ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    public ClinicServiceImpl(ClinicRepository clinicRepository){
        this.clinicRepository = clinicRepository;
    }
    @Override
    public List<Clinic> getAllClinics(){
       return clinicRepository.findAll();
    }

    @Override
    public Clinic getClinicById(Long id){
        return clinicRepository.findClinicById(id);
    }





}
