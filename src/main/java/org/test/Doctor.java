package org.test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Doctor {
    private Long id;
    private String name;
    private String specialty;
    private String email;
    private String phoneNumber;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String medicalLicenseNumber;
    private Integer yearsOfExperience;
    private List<String> specializations;
    private String education;
    private List<String> languagesSpoken;
    private String hospitalAffiliation;
    private String department;
    private String consultationHours;
    private String availability;
    private String biography;
    private List<String> awardsAndRecognitions;
    private List<String> researchAndPublications;

    public Doctor() {
    }

    public Doctor(Long id, String name, String specialty, String email, String phoneNumber, String addressLine1, 
                  String addressLine2, String city, String state, String postalCode, String country, 
                  String medicalLicenseNumber, Integer yearsOfExperience, List<String> specializations, 
                  String education, List<String> languagesSpoken, String hospitalAffiliation, String department, 
                  String consultationHours, String availability, String biography, 
                  List<String> awardsAndRecognitions, List<String> researchAndPublications) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.medicalLicenseNumber = medicalLicenseNumber;
        this.yearsOfExperience = yearsOfExperience;
        this.specializations = specializations;
        this.education = education;
        this.languagesSpoken = languagesSpoken;
        this.hospitalAffiliation = hospitalAffiliation;
        this.department = department;
        this.consultationHours = consultationHours;
        this.availability = availability;
        this.biography = biography;
        this.awardsAndRecognitions = awardsAndRecognitions;
        this.researchAndPublications = researchAndPublications;
    }

    // Getters and setters for all fields

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMedicalLicenseNumber() {
        return medicalLicenseNumber;
    }

    public void setMedicalLicenseNumber(String medicalLicenseNumber) {
        this.medicalLicenseNumber = medicalLicenseNumber;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public List<String> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<String> specializations) {
        this.specializations = specializations;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public List<String> getLanguagesSpoken() {
        return languagesSpoken;
    }

    public void setLanguagesSpoken(List<String> languagesSpoken) {
        this.languagesSpoken = languagesSpoken;
    }

    public String getHospitalAffiliation() {
        return hospitalAffiliation;
    }

    public void setHospitalAffiliation(String hospitalAffiliation) {
        this.hospitalAffiliation = hospitalAffiliation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getConsultationHours() {
        return consultationHours;
    }

    public void setConsultationHours(String consultationHours) {
        this.consultationHours = consultationHours;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public List<String> getAwardsAndRecognitions() {
        return awardsAndRecognitions;
    }

    public void setAwardsAndRecognitions(List<String> awardsAndRecognitions) {
        this.awardsAndRecognitions = awardsAndRecognitions;
    }

    public List<String> getResearchAndPublications() {
        return researchAndPublications;
    }

    public void setResearchAndPublications(List<String> researchAndPublications) {
        this.researchAndPublications = researchAndPublications;
    }
}
