package com.safetynet.alerts.integration;


import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.safetynet.alerts.dto.PersonFireDto;
import com.safetynet.alerts.dto.PersonFloodDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;


@SpringBootTest
@AutoConfigureMockMvc
public class AlertsControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetEmailsFromCity() throws Exception {
        mockMvc.perform(get("/communityEmail").queryParam("city","Culver"))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetEmailsFromCityReturnEmptyList() throws Exception {
        mockMvc.perform(get("/communityEmail").queryParam("city","Paris"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testGetPersonFromFireStation() throws Exception {

        mockMvc.perform(get("/firestation").queryParam("stationNumber","1"))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetPersonFromAddress() throws Exception {

        mockMvc.perform(get("/childAlert").queryParam("address","1509 Culver St"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAddressByCity() throws Exception {

        mockMvc.perform(get("/personInfo").queryParam("firstName","Lily").queryParam("lastName","Cooper"))
                .andExpect(status().isOk());
    }

    @Test
    public void getPhoneNumberFromStation() throws Exception {
        mockMvc.perform(get("/phoneAlert").queryParam("firestation", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void getPersonFromAddressWithStation() throws Exception {
        mockMvc.perform(get("/fire").queryParam("address", "908 73rd St"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.station", is(1)));
    }

    @Test
    public void getFamilyByListOfStation() throws Exception {
        mockMvc.perform(get("/flood/stations").queryParam("stations", "1,2"))
                .andExpect(status().isOk());
    }

}
