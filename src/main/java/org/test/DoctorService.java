package org.test;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class DoctorService {

    private static final Logger LOGGER = Logger.getLogger(DoctorService.class.getName());
    private final CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper;
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "mrJaat@1222";

    public DoctorService() {
        this.httpClient = HttpClients.createDefault();
        this.objectMapper = new ObjectMapper();
    }

    private String getAuthHeader() {
        String auth = USERNAME + ":" + PASSWORD;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
        return "Basic " + new String(encodedAuth);
    }

    public Doctor createDoctor(Doctor doctor) {
        String json = String.format(
                "{\"name\":\"%s\",\"specialty\":\"%s\",\"email\":\"%s\",\"phoneNumber\":\"%s\",\"addressLine1\":\"%s\",\"addressLine2\":\"%s\",\"city\":\"%s\",\"state\":\"%s\",\"postalCode\":\"%s\",\"country\":\"%s\",\"medicalLicenseNumber\":\"%s\",\"yearsOfExperience\":%d,\"specializations\":%s,\"education\":\"%s\",\"languagesSpoken\":%s,\"hospitalAffiliation\":\"%s\",\"department\":\"%s\",\"consultationHours\":\"%s\",\"availability\":\"%s\",\"biography\":\"%s\",\"awardsAndRecognitions\":%s,\"researchAndPublications\":%s}",
                doctor.getName(), doctor.getSpecialty(), doctor.getEmail(), doctor.getPhoneNumber(),
                doctor.getAddressLine1(), doctor.getAddressLine2(), doctor.getCity(), doctor.getState(),
                doctor.getPostalCode(), doctor.getCountry(), doctor.getMedicalLicenseNumber(),
                doctor.getYearsOfExperience(), doctor.getSpecializations(), doctor.getEducation(),
                doctor.getLanguagesSpoken(), doctor.getHospitalAffiliation(), doctor.getDepartment(),
                doctor.getConsultationHours(), doctor.getAvailability(), doctor.getBiography(),
                doctor.getAwardsAndRecognitions(), doctor.getResearchAndPublications());

        HttpPost request = new HttpPost("https://localhost:9200/doctors/_doc/" + doctor.getId());
        request.setHeader("Content-Type", "application/json");
        request.setHeader("Authorization", getAuthHeader());
        LOGGER.info("Sending request to create doctor: " + request.getRequestLine());
        LOGGER.fine("Request payload: " + json);

        try {
            request.setEntity(new StringEntity(json));
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                int statusCode = response.getStatusLine().getStatusCode();
                String responseBody = EntityUtils.toString(response.getEntity());
                LOGGER.info("Response received for create doctor request. Status code: " + statusCode);
                LOGGER.fine("Response body: " + responseBody);
                System.out.println("Response body for createDoctor: " + responseBody);

                if (statusCode == 201 || statusCode == 200) {
                    LOGGER.info("Doctor created successfully: " + doctor);
                    return doctor;
                } else {
                    LOGGER.severe(
                            "Failed to create doctor. Status code: " + statusCode + ", Response: " + responseBody);
                    return null;
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IOException occurred while creating doctor: " + e.getMessage(), e);
            return null;
        }
    }

    public Optional<Doctor> getDoctorById(Long id) {
        HttpGet request = new HttpGet("https://localhost:9200/doctors/_doc/" + id);
        request.setHeader("Authorization", getAuthHeader());
        LOGGER.info("Sending request to fetch doctor by ID: " + request.getRequestLine());

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            int statusCode = response.getStatusLine().getStatusCode();
            String responseBody = EntityUtils.toString(response.getEntity());
            LOGGER.info("Response received for fetch doctor by ID request. Status code: " + statusCode);
            LOGGER.fine("Response body: " + responseBody);
            System.out.println("Response body for getDoctorById: " + responseBody);

            if (statusCode == 200) {
                @SuppressWarnings("unchecked")
                Map<String, Object> responseMap = objectMapper.readValue(responseBody, Map.class);
                @SuppressWarnings("unchecked")
                Map<String, Object> source = (Map<String, Object>) responseMap.get("_source");
                Doctor doctor = objectMapper.convertValue(source, Doctor.class);

                if (doctor != null) {
                    LOGGER.info("Fetched doctor by ID: " + id);
                    return Optional.of(doctor);
                } else {
                    LOGGER.warning("Doctor not found or error in parsing response for ID: " + id);
                    return Optional.empty();
                }
            } else {
                LOGGER.severe("Failed to fetch doctor. Status code: " + statusCode + ", Response: " + responseBody);
                return Optional.empty();
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IOException occurred while fetching doctor by ID: " + e.getMessage(), e);
            return Optional.empty();
        }
    }

    // public List<Doctor> getAllDoctors(int pageNumber, int pageSize) {
    //     long startTime = System.currentTimeMillis();
    //     int from = (pageNumber - 1) * pageSize;
    //     String url = String.format("https://localhost:9200/doctors/_search?from=%d&size=%d", from, pageSize);

    //     HttpGet request = new HttpGet(url);
    //     request.setHeader("Authorization", getAuthHeader());

    //     try {
    //         long requestSendTime = System.currentTimeMillis();
    //         try (var response = httpClient.execute(request)) {
    //             long responseReceivedTime = System.currentTimeMillis();

    //             int statusCode = response.getStatusLine().getStatusCode();
    //             String responseBody = EntityUtils.toString(response.getEntity());

    //             if (statusCode == 200) {
    //                 var responseMap = objectMapper.readValue(responseBody, Map.class);
    //                 @SuppressWarnings("unchecked")
    //                 var hits = (List<Map<String, Object>>) ((Map<String, Object>) responseMap.get("hits")).get("hits");
    //                 List<Doctor> doctors = new ArrayList<>();

    //                 for (Map<String, Object> hit : hits) {
    //                     @SuppressWarnings("unchecked")
    //                     var source = (Map<String, Object>) hit.get("_source");
    //                     Doctor doctor = objectMapper.convertValue(source, Doctor.class);
    //                     doctors.add(doctor);
    //                 }

    //                 long endTime = System.currentTimeMillis();
    //                 long requestTime = requestSendTime - startTime;
    //                 long responseTime = responseReceivedTime - requestSendTime;
    //                 long totalProcessingTime = endTime - startTime;

    //                 System.out.println("Request send time: " + requestTime + "ms");
    //                 System.out.println("Response receive time: " + responseTime + "ms");
    //                 System.out.println("Total processing time: " + totalProcessingTime + "ms");

    //                 return doctors;
    //             } else {
    //                 LOGGER.severe(
    //                         "Failed to fetch doctors. Status code: " + statusCode + ", Response: " + responseBody);
    //                 return List.of();
    //             }
    //         }
    //     } catch (IOException e) {
    //         LOGGER.log(Level.SEVERE, "IOException occurred while fetching doctors: " + e.getMessage(), e);
    //         return List.of();
    //     }
    // }

    public List<Doctor> getAllDoctors(int pageNumber, int pageSize) {
        long startTime = System.nanoTime();
        int from = (pageNumber - 1) * pageSize;
        String url = String.format("https://localhost:9200/doctors/_search?from=%d&size=%d", from, pageSize);
    
        HttpGet request = new HttpGet(url);
        request.setHeader("Authorization", getAuthHeader());
    
        try (var response = httpClient.execute(request)) {
            long responseTime = System.nanoTime();
            int statusCode = response.getStatusLine().getStatusCode();
            String responseBody = EntityUtils.toString(response.getEntity());
    
            if (statusCode == 200) {
                var responseMap = objectMapper.readValue(responseBody, Map.class);
                @SuppressWarnings("unchecked")
                var hits = (List<Map<String, Object>>) ((Map<String, Object>) responseMap.get("hits")).get("hits");
                List<Doctor> doctors = new ArrayList<>();
    
                for (Map<String, Object> hit : hits) {
                    @SuppressWarnings("unchecked")
                    var source = (Map<String, Object>) hit.get("_source");
                    Doctor doctor = objectMapper.convertValue(source, Doctor.class);
                    doctors.add(doctor);
                }
    
                long endTime = System.nanoTime();
                long responseReceiveTime = (responseTime - startTime) / 1_000_000;  // in milliseconds
                long totalProcessingTime = (endTime - startTime) / 1_000_000;  // in milliseconds
                long difference = totalProcessingTime - responseReceiveTime;
    
                System.out.println("Response receive time: " + responseReceiveTime + "ms");
                System.out.println("Total processing time: " + totalProcessingTime + "ms");
                System.out.println("Difference: " + difference + "ms");
    
                return doctors;
            } else {
                LOGGER.severe("Failed to fetch doctors. Status code: " + statusCode + ", Response: " + responseBody);
                return List.of();
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IOException occurred while fetching doctors: " + e.getMessage(), e);
            return List.of();
        }
    }
    
    
    public boolean deleteDoctor(Long id) {
        HttpDelete request = new HttpDelete("https://localhost:9200/doctors/_doc/" + id);
        request.setHeader("Authorization", getAuthHeader());
        LOGGER.info("Sending request to delete doctor: " + request.getRequestUri());

        try (CloseableHttpResponse response = httpClient.execute((HttpUriRequest) request)) {
            int statusCode = response.getStatusLine().getStatusCode();
            String responseBody = EntityUtils.toString(response.getEntity());
            LOGGER.info("Response received for delete doctor request. Status code: " + statusCode);
            LOGGER.fine("Response body: " + responseBody);
            System.out.println("Response body for deleteDoctor: " + responseBody);

            if (statusCode == 200) {
                LOGGER.info("Doctor deleted successfully: ID " + id);
                return true;
            } else {
                LOGGER.severe("Failed to delete doctor. Status code: " + statusCode + ", Response: " + responseBody);
                return false;
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IOException occurred while deleting doctor: " + e.getMessage(), e);
            return false;
        }
    }

    public Doctor updateDoctor(Long id, Doctor updatedDoctor) {
        throw new UnsupportedOperationException("Unimplemented method 'updateDoctor'");
    }
}
