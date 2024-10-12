package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.VaccinationRecord;
import au.edu.rmit.sept.webapp.repositories.VaccinationRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccinationRecordServiceImpl implements VaccinationRecordService {

    private final VaccinationRecordRepository vaccinationRecordRepository;

    @Autowired
    public VaccinationRecordServiceImpl(VaccinationRecordRepository vaccinationRecordRepository) {
        this.vaccinationRecordRepository = vaccinationRecordRepository;
    }

    @Override
    public List<VaccinationRecord> getVaccinationRecordByPetId(Long petId) {
        return vaccinationRecordRepository.findVaccinationRecordByPetId(petId);
    }

    @Override
    public void createVaccinationRecord(VaccinationRecord vaccinationRecord) {
        vaccinationRecordRepository.addVaccinationRecord(vaccinationRecord);
    }

    @Override
    public List<VaccinationRecord> getAllVaccinationRecords() {
        return vaccinationRecordRepository.findAllVaccinationRecords();
    }

}



