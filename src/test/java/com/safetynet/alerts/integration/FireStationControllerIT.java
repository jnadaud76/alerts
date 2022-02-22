package com.safetynet.alerts.integration;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.FireStation;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class FireStationControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetFireStations() throws Exception {
        mockMvc.perform(get("/firestations"))
                .andExpect(status().isOk());

    }

    @Test
    void testGetFireStationByStation() throws Exception {
        mockMvc.perform(get("/firestation/station/").queryParam("station", "4"))
                .andExpect(status().isOk());

    }

    @Test
    void testGetFireStationByAddress() throws Exception {
        mockMvc.perform(get("/firestation/address/").queryParam("address", "489 Manchester St"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].station", is(4)));
    }

    @Test
    void testGetFireStationByStationWithBadStation() throws Exception {
        mockMvc.perform(get("/firestation/station/").queryParam("station", "15"))
                .andExpect(status().isNotFound());

    }

    @Test
    void testGetFireStationByAddressWithBadAddress() throws Exception {
        mockMvc.perform(get("/firestation/address/").queryParam("address", "47 rue du pommier"))
                .andExpect(status().isNotFound());

    }

    @Test
    void testDeleteFireStationByStation() throws Exception {
        mockMvc.perform(delete("/firestation/station/").queryParam("station", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteFireStationByAddress() throws Exception {
        mockMvc.perform(delete("/firestation/address/").queryParam("address", "892 Downing Ct"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteFireStationWithBadStationNumber() throws Exception {
        mockMvc.perform(delete("/firestation/station/").queryParam("station", "12"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDeleteFireStationWithBadAddress() throws Exception {
        mockMvc.perform(delete("/firestation/address/").queryParam("address", "47 rue du pommier"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCreateFireStation() throws Exception {
        FireStation fireStation = new FireStation();
        fireStation.setStation(2);
        fireStation.setAddress("Test");
        ObjectMapper objectMapper = new ObjectMapper();
        String fireStationAsString = objectMapper.writeValueAsString(fireStation);

        mockMvc.perform(post("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fireStationAsString))
                .andExpect(status().isCreated());
    }

    @Test
    void testCreateFireStationWhichAlreadyExist() throws Exception {
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
    void testUpdateFireStation() throws Exception {
        FireStation fireStation = new FireStation();
        fireStation.setStation(1);
        fireStation.setAddress("1509 Culver St");
        ObjectMapper objectMapper = new ObjectMapper();
        String fireStationAsString = objectMapper.writeValueAsString(fireStation);

        mockMvc.perform(put("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fireStationAsString))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateFireStationGivingBadAddress() throws Exception {
        FireStation fireStation = new FireStation();
        fireStation.setStation(4);
        fireStation.setAddress("47 rue du pommier");
        ObjectMapper objectMapper = new ObjectMapper();
        String fireStationAsString = objectMapper.writeValueAsString(fireStation);

        mockMvc.perform(put("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fireStationAsString))
                .andExpect(status().isBadRequest());
    }

}
