package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.VaccinationRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccinationRecordRepository {

    List<VaccinationRecord> findVaccinationRecordByPetId(Long petId);
    void addVaccinationRecord(VaccinationRecord vaccinationRecord);

    List<VaccinationRecord> findAllVaccinationRecords();
}
