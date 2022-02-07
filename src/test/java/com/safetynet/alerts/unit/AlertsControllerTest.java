package com.safetynet.alerts.unit;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.safetynet.alerts.controller.AlertsController;
import com.safetynet.alerts.controller.FireStationController;
import com.safetynet.alerts.dto.PersonChildAlertDto;
import com.safetynet.alerts.dto.PersonFireStationDto;
import com.safetynet.alerts.dto.PersonInfoDto;
import com.safetynet.alerts.repository.MedicalRecordDao;
import com.safetynet.alerts.service.AlertsChildAlertService;
import com.safetynet.alerts.service.AlertsCommunityEmailService;
import com.safetynet.alerts.service.AlertsFireStationService;
import com.safetynet.alerts.service.AlertsPersonInfoService;
import com.safetynet.alerts.service.MedicalRecordService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

@WebMvcTest(controllers = AlertsController.class)
public class AlertsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AlertsFireStationService alertsFireStationService;

    @MockBean
    AlertsCommunityEmailService alertsCommunityEmailService;

    @MockBean
    AlertsChildAlertService alertsChildAlertService;

    @MockBean
    AlertsPersonInfoService alertsPersonInfoService;

    @Test
    public void testGetEmailsFromCity() throws Exception {
        Set<String> emails = new HashSet<>();
        String email1 = "jojo@gmail.com";
        String email2 = "jiji@gmail.com";
        emails.add(email1);
        emails.add(email2);
        when(alertsCommunityEmailService.getEmailFromCity("Culver")).thenReturn(emails);
        mockMvc.perform(get("/communityEmail").queryParam("city","Culver"))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetPersonFromFireStation() throws Exception {
        PersonFireStationDto personFireStationDto = new PersonFireStationDto();
        when(alertsFireStationService.getPersonFromFireStation(1)).thenReturn(personFireStationDto);
        mockMvc.perform(get("/firestation").queryParam("stationNumber","1"))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetPersonFromAddress() throws Exception {
        PersonChildAlertDto personChildAlertDto = new PersonChildAlertDto();
        when(alertsChildAlertService.getPersonFromAddress("908 73rd St")).thenReturn(personChildAlertDto);
        mockMvc.perform(get("/childAlert").queryParam("address","908 73rd St"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAddressByCity() throws Exception {
        PersonInfoDto personInfoDto = new PersonInfoDto();
        when(alertsPersonInfoService.getPersonInfo("Lily","Cooper")).thenReturn(personInfoDto);
        mockMvc.perform(get("/personInfo").queryParam("firstName","Lily").queryParam("lastName","Cooper"))
                .andExpect(status().isOk());
    }


}
