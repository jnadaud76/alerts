package com.safetynet.alerts.integration;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


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
    public void testGetPersonFromFireStation() throws Exception {

        mockMvc.perform(get("/firestation").queryParam("stationNumber","1"))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetPersonFromAddress() throws Exception {

        mockMvc.perform(get("/childAlert").queryParam("address","908 73rd St"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAddressByCity() throws Exception {

        mockMvc.perform(get("/personInfo").queryParam("firstName","Lily").queryParam("lastName","Cooper"))
                .andExpect(status().isOk());
    }

}
