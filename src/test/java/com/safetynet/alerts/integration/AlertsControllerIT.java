package com.safetynet.alerts.integration;


import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
class AlertsControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetEmailsFromCity() throws Exception {
        mockMvc.perform(get("/communityEmail").queryParam("city", "Culver"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetEmailsFromCityReturnEmptyList() throws Exception {
        mockMvc.perform(get("/communityEmail").queryParam("city", "Paris"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetPersonFromFireStation() throws Exception {
        mockMvc.perform(get("/firestation").queryParam("stationNumber", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetPersonFromFireStationWithBadStationNumber() throws Exception {
        mockMvc.perform(get("/firestation").queryParam("stationNumber", "9"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetPersonFromAddress() throws Exception {
        mockMvc.perform(get("/childAlert").queryParam("address", "1509 Culver St"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetPersonFromAddressWithBadAddress() throws Exception {
        mockMvc.perform(get("/childAlert").queryParam("address", "9999 Culver St"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetPersonInfo() throws Exception {
        mockMvc.perform(get("/personInfo").queryParam("firstName", "Lily").queryParam("lastName", "Cooper"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetPersonInfoWithUnknownPerson() throws Exception {
        mockMvc.perform(get("/personInfo").queryParam("firstName", "Lilo").queryParam("lastName", "Crooper"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getPhoneNumberFromStation() throws Exception {
        mockMvc.perform(get("/phoneAlert").queryParam("firestation", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void getPhoneNumberFromStationWithBadStationNumber() throws Exception {
        mockMvc.perform(get("/phoneAlert").queryParam("firestation", "9"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getPersonFromAddressWithStation() throws Exception {
        mockMvc.perform(get("/fire").queryParam("address", "908 73rd St"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.station[0]", is(1)));
    }

    @Test
    void getPersonFromAddressWithStationWithBadAddress() throws Exception {
        mockMvc.perform(get("/fire").queryParam("address", "999 73rd St"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getFamilyByListOfStation() throws Exception {
        mockMvc.perform(get("/flood/stations").queryParam("stations", "1,2"))
                .andExpect(status().isOk());
    }

    @Test
    void getFamilyByListOfStationWithBadListOfStation() throws Exception {
        mockMvc.perform(get("/flood/stations").queryParam("stations", "7,8"))
                .andExpect(status().isNotFound());
    }
}
