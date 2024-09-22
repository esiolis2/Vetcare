package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.Veterinarian;
import au.edu.rmit.sept.webapp.repositories.VeterinarianRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class VeterinarianServiceImplTest {

    private VeterinarianService veterinarianService;
    private VeterinarianRepository veterinarianRepository;

    @BeforeEach
    public void setUp() {
        veterinarianRepository = Mockito.mock(VeterinarianRepository.class);
        veterinarianService = new VeterinarianServiceImpl(veterinarianRepository);
    }

    @Test
    public void getAllVeterinarians_shouldReturnAllVeterinarians() {
        Veterinarian vet1 = new Veterinarian(1L, "Dr. Joseph", "john@example.com", 1L);
        Veterinarian vet2 = new Veterinarian(2L, "Dr. Smith", "smith@example.com", 2L);

        when(veterinarianRepository.findAllVeterinarians()).thenReturn(List.of(vet1, vet2));

        List<Veterinarian> veterinarians = veterinarianService.getAllVeterinarians();
        assertEquals(2, veterinarians.size());
        assertEquals("Dr. Joseph", veterinarians.get(0).getName());
        assertEquals("Dr. Smith", veterinarians.get(1).getName());
        verify(veterinarianRepository, times(1)).findAllVeterinarians();
    }

    @Test
    public void getVeterinarianById_shouldReturnVeterinariansForClinic() {
        Veterinarian vet1 = new Veterinarian(1L, "Dr. Joseph", "joseph@example.com", 1L);
        Veterinarian vet2 = new Veterinarian(2L, "Dr. Smith", "smith@example.com", 1L);

        when(veterinarianRepository.findAllVeterinariansById(1L)).thenReturn(List.of(vet1, vet2));

        List<Veterinarian> veterinarians = veterinarianService.getVeterinarianById(1L);
        assertEquals(2, veterinarians.size());
        assertEquals("Dr. Joseph", veterinarians.get(0).getName());
        verify(veterinarianRepository, times(1)).findAllVeterinariansById(1L);
    }

    @Test
    public void getVeterinarianById_shouldReturnEmptyListWhenNoVets() {
        when(veterinarianRepository.findAllVeterinariansById(999L)).thenReturn(List.of());

        List<Veterinarian> veterinarians = veterinarianService.getVeterinarianById(999L);
        assertEquals(0, veterinarians.size());
        verify(veterinarianRepository, times(1)).findAllVeterinariansById(999L);
    }
}
