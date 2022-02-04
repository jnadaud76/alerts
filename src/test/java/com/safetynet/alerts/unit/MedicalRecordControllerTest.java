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
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.MedicalRecordDao;
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

    @MockBean
    private MedicalRecordDao medicalRecordDao;

    @Test
    public void testGetMedicalRecords() throws Exception {
        mockMvc.perform(get("/medicalrecord"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetMedicalRecord() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("Lily");
        medicalRecord.setLastName("Cooper");
        MedicalRecordFullDto medicalRecordFullDto = new MedicalRecordFullDto();
        medicalRecordFullDto.setFirstName("Lily");
        medicalRecordFullDto.setLastName("Cooper");
        when(medicalRecordDao.findById("Lily","Cooper")).thenReturn(medicalRecord);
        when(medicalRecordService.getMedicalRecord("Lily","Cooper")).thenReturn(medicalRecordFullDto);
        mockMvc.perform(get("/medicalrecord/Lily/Cooper"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetMedicalRecordWithGoodFirstNameAndBadLastName() throws Exception {
        when(medicalRecordDao.findById("Lily","Crooper")).thenReturn(null);
        when(medicalRecordService.getMedicalRecord("Lily","Crooper")).thenReturn(null);
        mockMvc.perform(get("/medicalrecord/Lily/Crooper"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetMedicalRecordWithBadFirstNameAnGoodLastName() throws Exception {
        when(medicalRecordDao.findById("Lilo","Cooper")).thenReturn(null);
        when(medicalRecordService.getMedicalRecord("Lilo","Cooper")).thenReturn(null);
        mockMvc.perform(get("/medicalrecord/Lilo/Cooper"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetMedicalRecordWithBadFirstNameAnBadLastName() throws Exception {
        when(medicalRecordDao.findById("Lilo","Crooper")).thenReturn(null);
        when(medicalRecordService.getMedicalRecord("Lilo","Crooper")).thenReturn(null);
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
        doThrow(new IllegalArgumentException()).when(medicalRecordDao).save(medicalRecord);
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
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("Brian");
        medicalRecord.setLastName("Stelzer");
        MedicalRecordFullDto medicalRecordFullDto = new MedicalRecordFullDto();
        medicalRecordFullDto.setFirstName("Brian");
        medicalRecordFullDto.setLastName("Stelzer");
        when(medicalRecordDao.findById("Brian","Stelzer")).thenReturn(medicalRecord);
        when(medicalRecordService.getMedicalRecord("Brian","Stelzer")).thenReturn(medicalRecordFullDto);
        mockMvc.perform(delete("/medicalrecord/Brian/Stelzer"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteMedicalRecordWithGoodFirstNameAndBadLastName() throws Exception {
        doThrow(new IllegalArgumentException()).when(medicalRecordDao).deleteById("Brian","Stelzor");
        doThrow(new IllegalArgumentException()).when(medicalRecordService).deleteMedicalRecord("Brian","Stelzor");
        mockMvc.perform(delete("/medicalrecord/Brian/Stelzor"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteMedicalRecordWithBadFirstNameAnGoodLastName() throws Exception {
        doThrow(new IllegalArgumentException()).when(medicalRecordDao).deleteById("Briun","Stelzer");
        doThrow(new IllegalArgumentException()).when(medicalRecordService).deleteMedicalRecord("Briun","Stelzer");
        mockMvc.perform(delete("/medicalrecord/Briun/Stelzer"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteMedicalRecordWithBadFirstNameAnBadLastName() throws Exception {
        doThrow(new IllegalArgumentException()).when(medicalRecordDao).deleteById("Briun","Stelzor");
        doThrow(new IllegalArgumentException()).when(medicalRecordService).deleteMedicalRecord("Briun","Stelzor");
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
        doThrow(new IllegalArgumentException()).when(medicalRecordDao).update(medicalRecord);
        doThrow(new IllegalArgumentException()).when(medicalRecordService).updateMedicalRecord(medicalRecord);

        ObjectMapper objectMapper = new ObjectMapper();
        String medicalRecordAsString = objectMapper.writeValueAsString(medicalRecord);

        mockMvc.perform(put("/medicalrecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(medicalRecordAsString))
                .andExpect(status().isBadRequest());
    }


}
