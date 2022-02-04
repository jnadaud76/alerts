package com.safetynet.alerts.unit;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.controller.FireStationController;
import com.safetynet.alerts.dto.FireStationFullDto;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.repository.FireStationDao;
import com.safetynet.alerts.service.FireStationService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

@WebMvcTest(controllers = FireStationController.class)
public class FireStationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FireStationService fireStationService;

    @MockBean
    private FireStationDao fireStationDao;

    @Test
    public void testGetFireStations() throws Exception {
        mockMvc.perform(get("/firestation"))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetFireStationByStation() throws Exception {
        Set <FireStation> fireStations = new HashSet<>();
        FireStation fireStation =new FireStation();
        fireStation.setStation(4);
        fireStation.setAddress("489 Manchester St");
        fireStations.add(fireStation);
        Set <FireStationFullDto> fireStationFullDtoSet = new HashSet<>();
        FireStationFullDto fireStationFullDto =new FireStationFullDto();
        fireStationFullDto.setStation(4);
        fireStationFullDto.setAddress("489 Manchester St");
        fireStationFullDtoSet.add(fireStationFullDto);
        when(fireStationDao.findByStation(4)).thenReturn(fireStations);
        when(fireStationService.getFireStationsByStation(4)).thenReturn(fireStationFullDtoSet);
        mockMvc.perform(get("/firestation/station/4"))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetFireStationByAddress() throws Exception {
        Set <FireStation> fireStations = new HashSet<>();
        FireStation fireStation =new FireStation();
        fireStation.setStation(4);
        fireStation.setAddress("489 Manchester St");
        fireStations.add(fireStation);
        Set <FireStationFullDto> fireStationFullDtoSet = new HashSet<>();
        FireStationFullDto fireStationFullDto =new FireStationFullDto();
        fireStationFullDto.setStation(4);
        fireStationFullDto.setAddress("489 Manchester St");
        fireStationFullDtoSet.add(fireStationFullDto);
        when(fireStationDao.findByAddress("489 Manchester St")).thenReturn(fireStations);
        when(fireStationService.getFireStationsByAddress("489 Manchester St")).thenReturn(fireStationFullDtoSet);
        mockMvc.perform(get("/firestation/address/489 Manchester St"))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetFireStationByStationWithBadStation() throws Exception {
        Set<FireStation> fireStations = new HashSet<>();
        Set <FireStationFullDto> fireStationFullDtoSet = new HashSet<>();
        when(fireStationDao.findByStation(15)).thenReturn(fireStations);
        when(fireStationService.getFireStationsByStation(15)).thenReturn(fireStationFullDtoSet);
        mockMvc.perform(get("/firestation/station/15"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testGetFireStationByAddressWithBadAddress() throws Exception {
        Set<FireStation> fireStations = new HashSet<>();
        Set <FireStationFullDto> fireStationFullDtoSet = new HashSet<>();
        when(fireStationDao.findByAddress("47 rue du pommier")).thenReturn(fireStations);
        when(fireStationService.getFireStationsByAddress("47 rue du pommier")).thenReturn(fireStationFullDtoSet);
        mockMvc.perform(get("/firestation/address/47 rue du pommier"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testDeleteFireStationByStation() throws Exception {
        Set <FireStation> fireStations = new HashSet<>();
        FireStation fireStation =new FireStation();
        fireStation.setStation(1);
        fireStation.setAddress("908 73rd St");
        FireStation fireStation2 =new FireStation();
        fireStation2.setStation(1);
        fireStation2.setAddress("947 E. Rose Dr");
        fireStations.add(fireStation);
        fireStations.add(fireStation2);
        when(fireStationDao.findByStation(1)).thenReturn(fireStations);
        mockMvc.perform(delete("/firestation/station/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteFireStationByAddress() throws Exception {
        Set <FireStation> fireStations = new HashSet<>();
        FireStation fireStation =new FireStation();
        fireStation.setStation(2);
        fireStation.setAddress("892 Downing Ct");
        when(fireStationDao.findByAddress("892 Downing Ct")).thenReturn(fireStations);
        mockMvc.perform(delete("/firestation/address/892 Downing Ct"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteFireStationWithBadStationNumber() throws Exception {
        doThrow(new IllegalArgumentException()).when(fireStationDao).deleteByStation(12);
        doThrow(new IllegalArgumentException()).when(fireStationService).deleteFireStationByStation(12);
        mockMvc.perform(delete("/firestation/station/12"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteFireStationWithBadAddress() throws Exception {
        doThrow(new IllegalArgumentException()).when(fireStationDao).deleteByAddress("47 rue du pommier");
        doThrow(new IllegalArgumentException()).when(fireStationService).deleteFireStationByAddress("47 rue du pommier");
        mockMvc.perform(delete("/firestation/address/47 rue du pommier"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateFireStation() throws Exception {
        FireStation fireStation = new FireStation();
        fireStation.setStation(4);
        fireStation.setAddress("Test");
        ObjectMapper objectMapper = new ObjectMapper();
        String fireStationAsString = objectMapper.writeValueAsString(fireStation);

        mockMvc.perform(post("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fireStationAsString))
                .andExpect(status().isCreated());
    }

    @Test
    public void testCreateFireStationWhichAlreadyExist() throws Exception {
        FireStation fireStation = new FireStation();
        fireStation.setStation(1);
        fireStation.setAddress("908 73rd St");
        ObjectMapper objectMapper = new ObjectMapper();
        String fireStationAsString = objectMapper.writeValueAsString(fireStation);
        doThrow(new IllegalArgumentException()).when(fireStationDao).save(fireStation);
        doThrow(new IllegalArgumentException()).when(fireStationService).createFireStation(fireStation);

        mockMvc.perform(post("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fireStationAsString))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateFireStation() throws Exception {
        FireStation fireStation = new FireStation();
        fireStation.setStation(4);
        fireStation.setAddress("1509 Culver St");
        ObjectMapper objectMapper = new ObjectMapper();
        String fireStationAsString = objectMapper.writeValueAsString(fireStation);

        mockMvc.perform(put("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fireStationAsString))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateFireStationGivingBadAddress() throws Exception {
        FireStation fireStation = new FireStation();
        fireStation.setStation(4);
        fireStation.setAddress("47 rue du pommier");
        ObjectMapper objectMapper = new ObjectMapper();
        String fireStationAsString = objectMapper.writeValueAsString(fireStation);
        doThrow(new IllegalArgumentException()).when(fireStationDao).update(fireStation);
        doThrow(new IllegalArgumentException()).when(fireStationService).updateFirestation(fireStation);

        mockMvc.perform(put("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fireStationAsString))
                .andExpect(status().isBadRequest());
    }

}
