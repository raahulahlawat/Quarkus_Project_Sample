package org.test;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DoctorService {

    private final List<Doctor> doctors = new ArrayList<>();

    public List<Doctor> getAllDoctors() {
        return doctors;
    }

    public Doctor createDoctor(Doctor doctor) {
        doctors.add(doctor);
        return doctor;
    }

    public Optional<Doctor> getDoctorById(Long id) {
        return doctors.stream().filter(d -> d.getId().equals(id)).findFirst();
    }

    public Doctor updateDoctor(Long id, Doctor updatedDoctor) {
        return getDoctorById(id).map(existingDoctor -> {
            existingDoctor.setName(updatedDoctor.getName());
            existingDoctor.setSpecialty(updatedDoctor.getSpecialty());
            return existingDoctor;
        }).orElse(null);
    }

    public boolean deleteDoctor(Long id) {
        return doctors.removeIf(d -> d.getId().equals(id));
    }
}
