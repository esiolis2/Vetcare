package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.PrescriptionRefillRequest;
import au.edu.rmit.sept.webapp.repositories.PrescriptionRefillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionRefillServiceImpl implements PrescriptionRefillService {

    private final PrescriptionRefillRepository prescriptionRefillRepository;

    @Autowired
    public PrescriptionRefillServiceImpl(PrescriptionRefillRepository prescriptionRefillRepository) {
        this.prescriptionRefillRepository = prescriptionRefillRepository;
    }

    @Override
    public void saveRefillRequest(PrescriptionRefillRequest refillRequest) {
        prescriptionRefillRepository.saveRefillRequest(refillRequest);
    }

    @Override
    public List<PrescriptionRefillRequest> getAllRefillRequests() {
        return prescriptionRefillRepository.findAllRefillRequests();
    }

}

