package org.test;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.graphql.*;

import java.util.List;
import java.util.Optional;

@GraphQLApi
public class DoctorResource {

    @Inject
    DoctorService doctorService;

    @Query("allDoctors")
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @Mutation
    @Transactional
    public Doctor createDoctor(Doctor doctor) {
        return doctorService.createDoctor(doctor);
    }

    @Query
    public Optional<Doctor> getDoctor(@Name("id") Long id) {
        return doctorService.getDoctorById(id);
    }

    @Mutation
    @Transactional
    public Doctor updateDoctor(@Name("id") Long id, Doctor updatedDoctor) {
        return doctorService.updateDoctor(id, updatedDoctor);
    }

    @Mutation
    @Transactional
    public boolean deleteDoctor(@Name("id") Long id) {
        return doctorService.deleteDoctor(id);
    }
}
