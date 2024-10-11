package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.PetInformation;
import au.edu.rmit.sept.webapp.models.Prescription;
import au.edu.rmit.sept.webapp.models.PrescriptionRefillRequest;
import au.edu.rmit.sept.webapp.models.User;
import au.edu.rmit.sept.webapp.services.PetInformationService;
import au.edu.rmit.sept.webapp.services.PrescriptionRefillService;
import au.edu.rmit.sept.webapp.services.PrescriptionService;
import au.edu.rmit.sept.webapp.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PrescriptionController {

    private final PrescriptionService prescriptionService;
    private final PetInformationService petInformationService;
    private final UserService userService;
    private final PrescriptionRefillService prescriptionRefillService;


    @Autowired
    public PrescriptionController(PrescriptionService prescriptionService, PetInformationService petInformationService, UserService userService, PrescriptionRefillService prescriptionRefillService) {
        this. prescriptionRefillService =  prescriptionRefillService;
        this.prescriptionService = prescriptionService;
        this.petInformationService = petInformationService;
        this.userService = userService;
    }

    private void addPetSelectionToModel(Model model, HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId != null) {
            List<PetInformation> pets = petInformationService.getPetByUserId(userId);
            if (pets != null && !pets.isEmpty()) {
                model.addAttribute("pets", pets);
            } else {
                model.addAttribute("errorMessage", "No pets found for the logged-in user.");
            }
        } else {
            model.addAttribute("errorMessage", "You must be logged in to view your pets.");
        }
    }

    @GetMapping("/view-prescription")
    public String viewPrescriptionPage(Model model, HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");

        if (userId == null) {
            model.addAttribute("errorMessage", "You must be logged in to view prescriptions.");
            return "viewPrescription";
        }
        addPetSelectionToModel(model, request);
        return "viewPrescription";
    }

    @GetMapping("/prescription")
    public String viewPrescription(@RequestParam("petId") Long petId, Model model, HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId == null) {
            model.addAttribute("errorMessage", "You must be logged in to view prescriptions.");
            addPetSelectionToModel(model, request);
            return "viewPrescription";
        }

        PetInformation pet = petInformationService.getPetById(petId);
        if (pet != null) {
            if (pet.getOwnerId().equals(userId)) {
                List<Prescription> prescriptions = prescriptionService.getPrescriptionsByPetId(petId);

                if (prescriptions != null && !prescriptions.isEmpty()) {
                    model.addAttribute("pet", pet);
                    model.addAttribute("prescriptions", prescriptions);
                } else {
                    model.addAttribute("errorMessage", "No prescriptions found for this pet.");
                }
            } else {
                model.addAttribute("errorMessage", "You are not authorized to view this pet's prescriptions.");
            }
        } else {
            model.addAttribute("errorMessage", "Pet not found.");
        }
        addPetSelectionToModel(model, request);
        return "viewPrescription";
    }

    @GetMapping("/prescription/order")
    public String orderPrescriptionPage(@RequestParam(value = "petId", required = false) Long petId, Model model, HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");

        if (userId == null) {
            model.addAttribute("errorMessage", "You must be logged in to order prescriptions.");
            return "refillPrescription";
        }

        List<PetInformation> pets = petInformationService.getPetByUserId(userId);
        model.addAttribute("pets", pets);

        if (petId != null) {
            PetInformation selectedPet = petInformationService.getPetById(petId);
            if (selectedPet != null && selectedPet.getOwnerId().equals(userId)) {
                List<Prescription> prescriptions = prescriptionService.getPrescriptionsByPetId(petId);
                model.addAttribute("selectedPet", selectedPet);
                model.addAttribute("prescriptions", prescriptions);
            } else {
                model.addAttribute("errorMessage", "Pet not found or you do not have permission to order prescriptions for this pet.");
            }
        }

        return "refillPrescription";
    }


    @PostMapping("/prescription/refill")
    public String submitRefillRequest(@ModelAttribute PrescriptionRefillRequest refillRequest, Model model) {
        try {
            System.out.println("Pet ID: " + refillRequest.getPetID());
            System.out.println("Medications: " + refillRequest.getMedications());
            System.out.println("Address: " + refillRequest.getAddress());

            prescriptionRefillService.saveRefillRequest(refillRequest);
            model.addAttribute("successMessage", "Your prescription refill request has been successfully submitted.");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred while submitting your refill request. Please try again.");
            e.printStackTrace();
        }
        return "refillPrescription";
    }

    @GetMapping("/prescription/orderdetails")
    public String getRefillOrders(Model model, HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");

        if (userId == null) {
            model.addAttribute("errorMessage", "You must be logged in to view your order details.");
            return "orderPrescription";
        }

        List<Long> userPetIds = petInformationService.getPetByUserId(userId)
                .stream()
                .map(PetInformation::getPetID)
                .toList();

        List<PrescriptionRefillRequest> allOrders = prescriptionRefillService.getAllRefillRequests();
        List<PrescriptionRefillRequest> userOrders = allOrders.stream()
                .filter(order -> userPetIds.contains(order.getPetID()))
                .toList();

        if (userOrders.isEmpty()) {
            System.out.println("No orders found for the user's pets.");
            model.addAttribute("errorMessage", "No orders found for your pets.");
        } else {
            System.out.println("Orders fetched: " + userOrders.size());
            for (PrescriptionRefillRequest order : userOrders) {
                System.out.println("Order details: " + order.getMedications() + ", " + order.getAddress());
            }
            model.addAttribute("orders", userOrders);
        }

        return "orderPrescription";
    }


    @GetMapping("/edit-prescription")
    public String editPrescriptionForm(Model model, HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        String userType = (String) request.getSession().getAttribute("userType");

        List<User> users = userService.getAllUsers().stream()
                .filter(user -> "User".equals(user.getUserType()))
                .collect(Collectors.toList());
        model.addAttribute("users", users);

        model.addAttribute("selectedUser", null);
        model.addAttribute("pets", null);
        model.addAttribute("prescription", new Prescription());

        return "prescriptionForm";
    }


    @GetMapping("/prescription/selectUser")
    public String selectUserForPrescription(@RequestParam("userId") Long userId, Model model) {
        User selectedUser = userService.findByUser(userId);
        List<PetInformation> pets = petInformationService.getPetByUserId(userId);

        if (pets.isEmpty()) {
            model.addAttribute("errorMessage", "No pets found for this user.");
        }

        model.addAttribute("selectedUser", selectedUser);
        model.addAttribute("pets", pets);
        model.addAttribute("users", List.of(selectedUser));
        model.addAttribute("prescription", new Prescription());

        return "prescriptionForm";
    }


    @GetMapping("/prescription/selectPet")
    public String selectPetForPrescription(@RequestParam("petId") Long petId, Model model) {
        PetInformation selectedPet = petInformationService.getPetById(petId);
        User selectedUser = userService.findByUser(selectedPet.getOwnerId());

        if (selectedPet == null) {
            model.addAttribute("errorMessage", "Pet not found.");
        }

        model.addAttribute("selectedPet", selectedPet);
        model.addAttribute("selectedUser", selectedUser);
        model.addAttribute("pets", List.of(selectedPet));
        model.addAttribute("users", List.of(selectedUser));
        model.addAttribute("prescription", new Prescription());

        return "prescriptionForm";
    }

    @PostMapping("/save-prescription")
    public String savePrescription(@ModelAttribute("prescription") Prescription prescription,
                                   @RequestParam("petId") Long petId, HttpServletRequest request, Model model) {

        PetInformation pet = petInformationService.getPetById(petId);

        if (pet != null) {
            prescription.setPet(pet);

            if (prescription.getPrescriptionID() == null) {
                prescriptionService.createPrescription(prescription);
            }

            model.addAttribute("successMessage", "Prescription saved successfully.");
        } else {
            model.addAttribute("errorMessage", "Pet not found.");
            return "prescriptionForm";
        }

        return "HomePage";
    }

}
























//package au.edu.rmit.sept.webapp.controllers;
//
//import au.edu.rmit.sept.webapp.models.PetInformation;
//import au.edu.rmit.sept.webapp.models.Prescription;
//import au.edu.rmit.sept.webapp.services.PetInformationService;
//import au.edu.rmit.sept.webapp.services.PrescriptionService;
//import au.edu.rmit.sept.webapp.services.UserService;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//
//@Controller
//public class PrescriptionController {
//
//    private final PrescriptionService prescriptionService;
//    private final PetInformationService petInformationService;
//    private final UserService userService;
//
//    @Autowired
//    public PrescriptionController(PrescriptionService prescriptionService, PetInformationService petInformationService, UserService userService) {
//        this.prescriptionService = prescriptionService;
//        this.petInformationService = petInformationService;
//        this.userService = userService;
//    }
//
//    private void addPetSelectionToModel(Model model, HttpServletRequest request) {
//        Long userId = (Long) request.getSession().getAttribute("userId");
//        if (userId != null) {
//            List<PetInformation> pets = petInformationService.getPetByUserId(userId);
//            if (pets != null && !pets.isEmpty()) {
//                model.addAttribute("pets", pets);
//            } else {
//                model.addAttribute("errorMessage", "No pets found for the logged-in user.");
//            }
//        } else {
//            model.addAttribute("errorMessage", "You must be logged in to view your pets.");
//        }
//    }
//
//    @GetMapping("/prescription-management")
//    public String viewPrescriptionPage(Model model, HttpServletRequest request) {
//        Long userId = (Long) request.getSession().getAttribute("userId");
//
//        if (userId == null) {
//            model.addAttribute("errorMessage", "No prescriptions available for the user.");
//            System.out.println("No prescriptions available for the user.");
//            return "Prescription";
//        }
//        List<PetInformation> pets = petInformationService.getPetByUserId(userId);
//
//        if (pets == null || pets.isEmpty()) {
//            model.addAttribute("errorMessage", "No prescriptions found for the logged-in user.");
//            System.out.println("No prescriptions found for the logged-in user.");
//            return "Prescription";
//        } else {
//            model.addAttribute("pets", pets);
//        }
//
//        return "Prescription";
//    }
//
//    @GetMapping("/prescription")
//    public String viewPrescription(@RequestParam("petId") Long petId, Model model, HttpServletRequest request) {
//        Long userId = (Long) request.getSession().getAttribute("userId");
//        if (userId == null) {
//            model.addAttribute("errorMessage", "You must be logged in to view prescriptions.");
//            addPetSelectionToModel(model, request);
//            return "Prescription";
//        }
//
//        PetInformation pet = petInformationService.getPetById(petId);
//        if (pet != null) {
//            if (pet.getOwnerId().equals(userId)) {
//                List<Prescription> prescriptions = prescriptionService.getPrescriptionsByPetId(petId);
//
//                if (prescriptions != null && !prescriptions.isEmpty()) {
//                    model.addAttribute("pet", pet);
//                    model.addAttribute("prescriptions", prescriptions);
//                } else {
//                    model.addAttribute("errorMessage", "No prescriptions found for this pet.");
//                }
//            } else {
//                model.addAttribute("errorMessage", "You are not authorized to view this pet's prescriptions.");
//            }
//        } else {
//            model.addAttribute("errorMessage", "Pet not found.");
//        }
//        addPetSelectionToModel(model, request);
//        return "Prescription";
//    }
//}
