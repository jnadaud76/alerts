package com.safetynet.alerts.unit;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.controller.MedicalRecordController;
import com.safetynet.alerts.dto.MedicalRecordFullDto;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.MedicalRecordService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

@WebMvcTest(controllers = MedicalRecordController.class)
public class MedicalRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicalRecordService medicalRecordService;

    @Test
    public void testGetMedicalRecords() throws Exception {
        mockMvc.perform(get("/medicalrecords"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetMedicalRecord() throws Exception {
        MedicalRecordFullDto medicalRecordFullDto = new MedicalRecordFullDto();
        medicalRecordFullDto.setFirstName("Lily");
        medicalRecordFullDto.setLastName("Cooper");
        when(medicalRecordService.getMedicalRecord("Lily", "Cooper")).thenReturn(medicalRecordFullDto);
        mockMvc.perform(get("/medicalrecord/")
                        .queryParam("firstName", "Lily")
                        .queryParam("lastName", "Cooper"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetMedicalRecordWithGoodFirstNameAndBadLastName() throws Exception {
        when(medicalRecordService.getMedicalRecord("Lily", "Crooper")).thenReturn(null);
        mockMvc.perform(get("/medicalrecord/")
                        .queryParam("firstName", "Lily")
                        .queryParam("lastName", "Crooper"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetMedicalRecordWithBadFirstNameAnGoodLastName() throws Exception {
        when(medicalRecordService.getMedicalRecord("Lilo", "Cooper")).thenReturn(null);
        mockMvc.perform(get("/medicalrecord/")
                        .queryParam("firstName", "Lilo")
                        .queryParam("lastName", "Cooper"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetMedicalRecordWithBadFirstNameAnBadLastName() throws Exception {
        when(medicalRecordService.getMedicalRecord("Lilo", "Crooper")).thenReturn(null);
        mockMvc.perform(get("/medicalrecord/")
                        .queryParam("firstName", "Lilo")
                        .queryParam("lastName", "Crooper"))
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
    public void testCreateMedicalRecordWhichAlreadyExist() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord();
        Set<String> medications = new HashSet<>();
        Set<String> allergies = new HashSet<>();
        medicalRecord.setFirstName("Lily");
        medicalRecord.setLastName("Cooper");
        medicalRecord.setBirthdate("03/06/1994");
        medicalRecord.setMedications(medications);
        medicalRecord.setAllergies(allergies);
        doThrow(new IllegalArgumentException()).when(medicalRecordService).createMedicalRecord(medicalRecord);

        ObjectMapper objectMapper = new ObjectMapper();
        String medicalRecordAsString = objectMapper.writeValueAsString(medicalRecord);

        mockMvc.perform(post("/medicalrecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(medicalRecordAsString))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteMedicalRecord() throws Exception {
        MedicalRecordFullDto medicalRecordFullDto = new MedicalRecordFullDto();
        medicalRecordFullDto.setFirstName("Brian");
        medicalRecordFullDto.setLastName("Stelzer");
        when(medicalRecordService.getMedicalRecord("Brian", "Stelzer")).thenReturn(medicalRecordFullDto);
        mockMvc.perform(delete("/medicalrecord/")
                        .queryParam("firstName", "Brian")
                        .queryParam("lastName", "Stelzer"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteMedicalRecordWithGoodFirstNameAndBadLastName() throws Exception {
        doThrow(new IllegalArgumentException()).when(medicalRecordService).deleteMedicalRecord("Brian", "Stelzor");
        mockMvc.perform(delete("/medicalrecord/")
                        .queryParam("firstName", "Brian")
                        .queryParam("lastName", "Stelzor"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteMedicalRecordWithBadFirstNameAnGoodLastName() throws Exception {
        doThrow(new IllegalArgumentException()).when(medicalRecordService).deleteMedicalRecord("Briun", "Stelzer");
        mockMvc.perform(delete("/medicalrecord/")
                        .queryParam("firstName", "Briun")
                        .queryParam("lastName", "Stelzer"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteMedicalRecordWithBadFirstNameAnBadLastName() throws Exception {
        doThrow(new IllegalArgumentException()).when(medicalRecordService).deleteMedicalRecord("Briun", "Stelzor");
        mockMvc.perform(delete("/medicalrecord/")
                        .queryParam("firstName", "Briun")
                        .queryParam("lastName", "Stelzor"))
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
        medicalRecord.setAllergies(allergies);

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
        medicalRecord.setAllergies(allergies);
        doThrow(new IllegalArgumentException()).when(medicalRecordService).updateMedicalRecord(medicalRecord);

        ObjectMapper objectMapper = new ObjectMapper();
        String medicalRecordAsString = objectMapper.writeValueAsString(medicalRecord);

        mockMvc.perform(put("/medicalrecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(medicalRecordAsString))
                .andExpect(status().isBadRequest());
    }


}
