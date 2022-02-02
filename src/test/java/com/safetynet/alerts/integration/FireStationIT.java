package com.safetynet.alerts.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class FireStationIT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testDeleteFireStationWithBadStationNumber() throws Exception {
        mockMvc.perform(delete("/firestation/station/12"))
                .andExpect(status().isNotFound());
    }
}
