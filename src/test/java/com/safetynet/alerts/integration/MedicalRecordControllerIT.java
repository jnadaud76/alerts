package com.safetynet.alerts.integration;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.controller.MedicalRecordController;
import com.safetynet.alerts.controller.PersonController;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.MedicalRecordDao;
import com.safetynet.alerts.service.MedicalRecordService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetMedicalRecords() throws Exception {
        mockMvc.perform(get("/medicalrecords"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetMedicalRecord() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("Lily");
        medicalRecord.setLastName("Cooper");
        mockMvc.perform(get("/medicalrecord/Lily/Cooper"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetMedicalRecordWithGoodFirstNameAndBadLastName() throws Exception {
      mockMvc.perform(get("/medicalrecord/Lily/Crooper"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetMedicalRecordWithBadFirstNameAnGoodLastName() throws Exception {

        mockMvc.perform(get("/medicalrecord/Lilo/Cooper"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetMedicalRecordWithBadFirstNameAnBadLastName() throws Exception {

        mockMvc.perform(get("/medicalrecord/Lilo/Crooper"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateMedicalRecord() throws Exception {
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
    public void testCreateMedicalRecordnWhichAlreadyExist() throws Exception {
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
    public void testDeleteMedicalRecord() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("Brian");
        medicalRecord.setLastName("Stelzer");

        mockMvc.perform(delete("/medicalrecord/Brian/Stelzer"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteMedicalRecordWithGoodFirstNameAndBadLastName() throws Exception {

        mockMvc.perform(delete("/medicalrecord/Brian/Stelzor"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteMedicalRecordWithBadFirstNameAnGoodLastName() throws Exception {

        mockMvc.perform(delete("/medicalrecord/Briun/Stelzer"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteMedicalRecordWithBadFirstNameAnBadLastName() throws Exception {

        mockMvc.perform(delete("/medicalrecord/Briun/Stelzor"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateMedicalRecord() throws Exception {
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
    public void testUpdateMedicalRecordGivingBadLastName() throws Exception {
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
