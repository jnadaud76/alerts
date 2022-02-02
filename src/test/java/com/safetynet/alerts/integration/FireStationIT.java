package com.safetynet.alerts.integration;

import static org.hamcrest.CoreMatchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.Person;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class FireStationIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetFireStations() throws Exception {
        mockMvc.perform(get("/firestation"))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetFireStationByStation() throws Exception {
        mockMvc.perform(get("/firestation/station/4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].address", is("489 Manchester St")));
    }

    @Test
    public void testGetFireStationByAddress() throws Exception {
        mockMvc.perform(get("/firestation/address/489 Manchester St"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].station", is(4)));
    }

    @Test
    public void testGetFireStationByStationWithBadStation() throws Exception {
        mockMvc.perform(get("/firestation/station/15"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testGetFireStationByAddressWithBadAddress() throws Exception {
        mockMvc.perform(get("/firestation/address/47 rue du pommier"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testDeleteFireStationByStation() throws Exception {
        mockMvc.perform(delete("/firestation/station/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteFireStationByAddress() throws Exception {
        mockMvc.perform(delete("/firestation/address/892 Downing Ct"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteFireStationWithBadStationNumber() throws Exception {
        mockMvc.perform(delete("/firestation/station/12"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteFireStationWithBadAddress() throws Exception {
        mockMvc.perform(delete("/firestation/address/47 rue du pommier"))
                .andExpect(status().isNotFound());
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

        mockMvc.perform(put("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fireStationAsString))
                .andExpect(status().isNotFound());
    }

}
