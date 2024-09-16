package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.VaccinationRecord;

import java.util.List;

public interface VaccinationRecordService {
    List<VaccinationRecord> getVaccinationRecordByPetId(Long petId);
    void createVaccinationRecord(VaccinationRecord vaccinationRecord);
    List<VaccinationRecord> getAllVaccinationRecords();


}
