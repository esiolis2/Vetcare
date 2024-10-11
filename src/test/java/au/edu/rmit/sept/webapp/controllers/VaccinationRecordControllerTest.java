package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.PetInformation;
import au.edu.rmit.sept.webapp.models.VaccinationRecord;
import au.edu.rmit.sept.webapp.services.PetInformationService;
import au.edu.rmit.sept.webapp.services.VaccinationRecordService;
import au.edu.rmit.sept.webapp.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
public class VaccinationRecordControllerTest {

    private MockMvc mockMvc;


    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private PetInformationService petInformationService;

    @MockBean
    private VaccinationRecordService vaccinationRecordService;

    @MockBean
    private UserService userService; // Ensure UserService is mocked

    private PetInformation pet;
    private VaccinationRecord record;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        pet = new PetInformation(1L, "Max", 3, "Male", 5.0, "Labrador", null, 1L);

        record = new VaccinationRecord(1L, 1L, "Rabies", LocalDate.now(),
                LocalDate.now().plusYears(1), "Yes", new BigDecimal("2.5"), "Dr. Smith",
                "Clinic A", "Completed", "All good");
    }

    @Test
    public void testViewVaccinationRecords() throws Exception {
        when(petInformationService.getPetByUserId(1L)).thenReturn(List.of(pet));

        mockMvc.perform(get("/view-vaccination-records").sessionAttr("userId", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("ViewVaccinationRecords"))
                .andExpect(model().attributeExists("pets"))
                .andExpect(model().attribute("pets", List.of(pet)));
    }

    @Test
    public void testShowVaccinationDetails_ShouldReturnVaccinationRecords() throws Exception {
        when(petInformationService.getPetById(1L)).thenReturn(pet);
        when(vaccinationRecordService.getVaccinationRecordByPetId(1L)).thenReturn(List.of(record));

        mockMvc.perform(get("/vaccination-record-details?petId=1").sessionAttr("userId", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("ViewVaccinationRecords"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("vaccinationRecords"))
                .andExpect(model().attribute("pet", pet))
                .andExpect(model().attribute("vaccinationRecords", List.of(record)));
    }

    @Test
    public void testShowVaccinationDetails_PetNotFound() throws Exception {
        when(petInformationService.getPetById(999L)).thenReturn(null);

        mockMvc.perform(get("/vaccination-record-details?petId=999").sessionAttr("userId", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("ViewVaccinationRecords"))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(model().attribute("errorMessage", "No pets found for the logged-in user."));
    }

    @Test
    public void testShowVaccinationDetails_UnauthorizedAccess() throws Exception {
        mockMvc.perform(get("/vaccination-record-details?petId=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("ViewVaccinationRecords"))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(model().attribute("errorMessage", "You must be logged in to view your pets."));
    }

    @Test
    public void testShowVaccinationDetails_NoVaccinationRecordsFound() throws Exception {
        when(petInformationService.getPetById(1L)).thenReturn(pet);
        when(vaccinationRecordService.getVaccinationRecordByPetId(1L)).thenReturn(List.of());

        mockMvc.perform(get("/vaccination-record-details?petId=1").sessionAttr("userId", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("ViewVaccinationRecords"))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(model().attribute("errorMessage", "No pets found for the logged-in user."));
    }
}
