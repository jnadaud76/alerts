package com.safetynet.alerts.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.MedicalRecord;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
@AutoConfigureMockMvc
class MedicalRecordControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetMedicalRecords() throws Exception {
        mockMvc.perform(get("/medicalrecords"))
                .andExpect(status().isOk());
    }

    @Test
   void testGetMedicalRecord() throws Exception {
        mockMvc.perform(get("/medicalrecord/")
                        .queryParam("firstName", "Lily")
                        .queryParam("lastName", "Cooper"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetMedicalRecordWithGoodFirstNameAndBadLastName() throws Exception {
        mockMvc.perform(get("/medicalrecord/")
                        .queryParam("firstName", "Lily")
                        .queryParam("lastName", "Crooper"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetMedicalRecordWithBadFirstNameAnGoodLastName() throws Exception {
        mockMvc.perform(get("/medicalrecord/")
                        .queryParam("firstName", "Lilo")
                        .queryParam("lastName", "Cooper"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetMedicalRecordWithBadFirstNameAnBadLastName() throws Exception {
        mockMvc.perform(get("/medicalrecord/")
                        .queryParam("firstName", "Lilo")
                        .queryParam("lastName", "Crooper"))
                .andExpect(status().isNotFound());
    }

    @Test
   void testCreateMedicalRecord() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord();
        Set<String> medications = new HashSet<>();
        String medication = "dodoxadin:30mg";
        medications.add(medication);
        Set<String> allergies = new HashSet<>();
        String allergie = "peanuts";
        allergies.add(allergie);
        medicalRecord.setFirstName("Test");
        medicalRecord.setLastName("Test");
        medicalRecord.setBirthdate("11/11/2011");
        medicalRecord.setMedications(medications);
        medicalRecord.setMedications(allergies);

        ObjectMapper objectMapper = new ObjectMapper();
        String medicalRecordAsString = objectMapper.writeValueAsString(medicalRecord);

        mockMvc.perform(post("/medicalrecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(medicalRecordAsString))
                .andExpect(status().isCreated());
    }

    @Test
    void testCreateMedicalRecordnWhichAlreadyExist() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord();
        Set<String> medications = new HashSet<>();
        Set<String> allergies = new HashSet<>();
        medicalRecord.setFirstName("Lily");
        medicalRecord.setLastName("Cooper");
        medicalRecord.setBirthdate("03/06/1994");
        medicalRecord.setMedications(medications);
        medicalRecord.setMedications(allergies);


        ObjectMapper objectMapper = new ObjectMapper();
        String medicalRecordAsString = objectMapper.writeValueAsString(medicalRecord);

        mockMvc.perform(post("/medicalrecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(medicalRecordAsString))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDeleteMedicalRecord() throws Exception {
        mockMvc.perform(delete("/medicalrecord/")
                        .queryParam("firstName", "Brian")
                        .queryParam("lastName", "Stelzer"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteMedicalRecordWithGoodFirstNameAndBadLastName() throws Exception {
        mockMvc.perform(delete("/medicalrecord/")
                        .queryParam("firstName", "Brian")
                        .queryParam("lastName", "Stelzor"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDeleteMedicalRecordWithBadFirstNameAnGoodLastName() throws Exception {
        mockMvc.perform(delete("/medicalrecord/")
                        .queryParam("firstName", "Briun")
                        .queryParam("lastName", "Stelzer"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDeleteMedicalRecordWithBadFirstNameAnBadLastName() throws Exception {
        mockMvc.perform(delete("/medicalrecord/")
                        .queryParam("firstName", "Briun")
                        .queryParam("lastName", "Stelzor"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateMedicalRecord() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord();
        Set<String> medications = new HashSet<>();
        Set<String> allergies = new HashSet<>();
        medicalRecord.setFirstName("Lily");
        medicalRecord.setLastName("Cooper");
        medicalRecord.setBirthdate("03/06/2008");
        medicalRecord.setMedications(medications);
        medicalRecord.setMedications(allergies);

        ObjectMapper objectMapper = new ObjectMapper();
        String medicalRecordAsString = objectMapper.writeValueAsString(medicalRecord);

        mockMvc.perform(put("/medicalrecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(medicalRecordAsString))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateMedicalRecordGivingBadLastName() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord();
        Set<String> medications = new HashSet<>();
        Set<String> allergies = new HashSet<>();
        medicalRecord.setFirstName("Lily");
        medicalRecord.setLastName("Crooper");
        medicalRecord.setBirthdate("03/06/2008");
        medicalRecord.setMedications(medications);
        medicalRecord.setMedications(allergies);


        ObjectMapper objectMapper = new ObjectMapper();
        String medicalRecordAsString = objectMapper.writeValueAsString(medicalRecord);

        mockMvc.perform(put("/medicalrecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(medicalRecordAsString))
                .andExpect(status().isBadRequest());
    }

}
