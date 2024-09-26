INSERT INTO VaccinationRecords (PetID, VaccineName, AdministeredDate, NextDueDate, BoosterRequired, Dosage, VeterinarianName, ClinicName, Status, AdditionalNotes)
VALUES
    (1, 'Rabies', '2023-01-15', '2024-01-15', 'Yes', 0.50, 'Dr. John Doe', 'Happy Pets Clinic', 'Completed', 'Annual rabies booster required'),
    (1, 'Distemper', '2022-06-15', '2023-06-15', 'Yes', 0.70, 'Dr. Jane Smith', 'VetCare Clinic', 'Completed', 'Booster required yearly'),
    (1, 'Parainfluenza', '2023-09-10', '2024-09-10', 'Yes', 0.65, 'Dr. William Harris', 'Happy Pets Clinic', 'Completed', 'Annual parainfluenza booster required'),

    (2, 'Parvovirus', '2023-05-22', '2024-05-22', 'Yes', 0.80, 'Dr. Michael Green', 'Healthy Paws Clinic', 'Completed', 'Booster due next year'),
    (2, 'Bordetella', '2022-12-10', '2023-12-10', 'Yes', 0.60, 'Dr. Sarah Brown', 'Paws & Claws Clinic', 'Pending', 'Booster needed in 6 months'),
    (2, 'Adenovirus', '2023-07-01', '2024-07-01', 'Yes', 0.75, 'Dr. Emily Davis', 'VetCare Clinic', 'Completed', 'Adenovirus booster required yearly'),

    (3, 'Leptospirosis', '2023-10-10', '2024-10-10', 'No', 0.60, 'Dr. Emily Davis', 'All Paws Clinic', 'Completed', 'No annual booster required'),
    (3, 'Rabies', '2023-02-25', '2024-02-25', 'Yes', 0.75, 'Dr. John White', 'Happy Pets Clinic', 'Completed', 'Booster required annually'),
    (3, 'Canine Influenza', '2023-11-05', '2024-11-05', 'Yes', 0.85, 'Dr. Sarah Johnson', 'Healthy Paws Clinic', 'Completed', 'Booster needed yearly'),

    (4, 'Canine Influenza', '2023-06-12', '2024-06-12', 'Yes', 0.55, 'Dr. James Lee', 'VetCare Clinic', 'Completed', 'Next due in a year'),
    (4, 'Hepatitis', '2023-09-01', '2024-09-01', 'Yes', 0.65, 'Dr. Sarah Smith', 'VetCare Clinic', 'Completed', 'Booster needed in a year'),
    (4, 'Coronavirus', '2023-08-15', '2024-08-15', 'Yes', 0.90, 'Dr. Laura Black', 'Paws & Claws Clinic', 'Completed', 'Booster needed yearly'),

    (5, 'Lyme Disease', '2022-03-08', '2023-03-08', 'Yes', 0.80, 'Dr. Andrew Grey', 'Healthy Paws Clinic', 'Completed', 'Booster required yearly'),
    (5, 'Parvovirus', '2023-07-15', '2024-07-15', 'Yes', 0.90, 'Dr. Laura Black', 'VetCare Clinic', 'Pending', 'Booster due in a year'),
    (5, 'Canine Influenza', '2024-01-20', '2025-01-20', 'Yes', 0.85, 'Dr. Emily Davis', 'VetCare Clinic', 'Completed', 'Next due in a year'),

    (6, 'Distemper', '2020-09-04', '2021-09-04', 'Yes', 0.60, 'Dr. Emily Davis', 'All Paws Clinic', 'Completed', 'Booster required every year'),
    (6, 'Rabies', '2022-11-10', '2023-11-10', 'Yes', 0.70, 'Dr. Jane Green', 'Happy Pets Clinic', 'Completed', 'Next booster in a year'),
    (6, 'Bordetella', '2023-06-25', '2024-06-25', 'Yes', 0.60, 'Dr. Sarah Johnson', 'All Paws Clinic', 'Completed', 'Booster required yearly'),

    (7, 'Coronavirus', '2023-11-20', '2024-11-20', 'Yes', 0.85, 'Dr. Michael White', 'Healthy Paws Clinic', 'Completed', 'Next due next year'),
    (7, 'Leptospirosis', '2023-03-15', '2024-03-15', 'No', 0.70, 'Dr. Sarah Johnson', 'VetCare Clinic', 'Pending', 'No booster required'),
    (7, 'Rabies', '2023-12-10', '2024-12-10', 'Yes', 0.75, 'Dr. James Lee', 'VetCare Clinic', 'Completed', 'Annual rabies booster required'),

    (8, 'Canine Influenza', '2022-08-30', '2023-08-30', 'Yes', 0.50, 'Dr. Andrew Grey', 'Paws & Claws Clinic', 'Completed', 'Booster needed yearly'),
    (8, 'Rabies', '2023-05-05', '2024-05-05', 'Yes', 0.55, 'Dr. Emily Davis', 'All Paws Clinic', 'Completed', 'Next booster in a year'),
    (8, 'Lyme Disease', '2024-02-28', '2025-02-28', 'Yes', 0.80, 'Dr. Laura Black', 'Paws & Claws Clinic', 'Completed', 'Booster needed yearly'),

    (9, 'Distemper', '2023-03-11', '2024-03-11', 'Yes', 0.60, 'Dr. Laura Black', 'VetCare Clinic', 'Completed', 'Next booster due in a year'),
    (9, 'Hepatitis', '2022-10-01', '2023-10-01', 'Yes', 0.65, 'Dr. Jane Green', 'Happy Pets Clinic', 'Pending', 'Follow-up required next month'),
    (9, 'Parvovirus', '2023-12-15', '2024-12-15', 'Yes', 0.80, 'Dr. Emily Davis', 'All Paws Clinic', 'Completed', 'Booster required yearly'),

    (10, 'Lyme Disease', '2023-07-25', '2024-07-25', 'Yes', 0.80, 'Dr. John Doe', 'Paws & Claws Clinic', 'Completed', 'Booster due next year'),
    (10, 'Rabies', '2023-12-25', '2024-12-25', 'Yes', 0.75, 'Dr. Sarah Brown', 'VetCare Clinic', 'Completed', 'Booster needed every year'),
    (10, 'Distemper', '2024-03-05', '2025-03-05', 'Yes', 0.60, 'Dr. James Lee', 'VetCare Clinic', 'Completed', 'Next booster due in a year');
