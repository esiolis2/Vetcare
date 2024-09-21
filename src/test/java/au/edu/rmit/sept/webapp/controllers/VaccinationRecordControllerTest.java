package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.PetInformation;
import au.edu.rmit.sept.webapp.models.VaccinationRecord;
import au.edu.rmit.sept.webapp.services.PetInformationService;
import au.edu.rmit.sept.webapp.services.VaccinationRecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VaccinationRecordController.class)
public class VaccinationRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetInformationService petInformationService;

    @MockBean
    private VaccinationRecordService vaccinationRecordService;

    private PetInformation pet;
    private VaccinationRecord record;

    @BeforeEach
    public void setUp() {
        pet = new PetInformation(1L, "Max", 3, "Male", 5.0, "Labrador",
                LocalDate.now(), "Mark smith", "123456789");
        record = new VaccinationRecord(1L, 1L, "Rabies", LocalDate.now(),
                LocalDate.now().plusYears(1), "Yes", new BigDecimal("2.5"), "Dr. Smith",
                "Clinic A", "Completed", "All good");
    }

    @Test
    public void testViewVaccinationRecords() throws Exception {
        when(petInformationService.getAllPets()).thenReturn(List.of(pet));

        mockMvc.perform(get("/view-vaccination-records"))
                .andExpect(status().isOk())
                .andExpect(view().name("ViewVaccinationRecords"))
                .andExpect(model().attributeExists("pets"));
    }

    @Test
    public void testShowVaccinationDetails_ShouldReturnVaccinationRecords() throws Exception {
        when(petInformationService.getPetById(1L)).thenReturn(pet);
        when(vaccinationRecordService.getVaccinationRecordByPetId(1L)).thenReturn(List.of(record));

        mockMvc.perform(get("/vaccination-record-details?petId=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("ViewVaccinationRecords"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("vaccinationRecords"))
                .andExpect(model().attribute("pet", pet))  // Additional assertion to check the pet object
                .andExpect(model().attribute("vaccinationRecords", List.of(record)));  // Additional assertion for vaccination records
    }



    @Test
    public void testShowVaccinationDetails_PetNotFound() throws Exception {
        when(petInformationService.getPetById(999L)).thenReturn(null);

        mockMvc.perform(get("/vaccination-record-details?petId=999"))
                .andExpect(status().isOk())
                .andExpect(view().name("ViewVaccinationRecords"))
                .andExpect(model().attributeExists("errorMessage"));
    }
}