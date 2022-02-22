package com.safetynet.alerts.unit;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.safetynet.alerts.controller.AlertsController;
import com.safetynet.alerts.dto.PersonChildAlertDto;
import com.safetynet.alerts.dto.PersonFireDto;
import com.safetynet.alerts.dto.PersonFireStationDto;
import com.safetynet.alerts.dto.PersonFloodDto;
import com.safetynet.alerts.dto.PersonInfoDto;
import com.safetynet.alerts.dto.PersonLightChildAlertDto;
import com.safetynet.alerts.dto.PersonLightFireDto;
import com.safetynet.alerts.dto.PersonLightFireStationDto;
import com.safetynet.alerts.service.impl.AlertsFireService;
import com.safetynet.alerts.service.impl.AlertsFloodService;
import com.safetynet.alerts.service.impl.AlertsPhoneAlertService;
import com.safetynet.alerts.service.impl.AlertsChildAlertService;
import com.safetynet.alerts.service.impl.AlertsCommunityEmailService;
import com.safetynet.alerts.service.impl.AlertsFireStationService;
import com.safetynet.alerts.service.impl.AlertsPersonInfoService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

@WebMvcTest(controllers = AlertsController.class)
class AlertsControllerTest {

    @MockBean
    AlertsFireStationService alertsFireStationService;
    @MockBean
    AlertsCommunityEmailService alertsCommunityEmailService;
    @MockBean
    AlertsChildAlertService alertsChildAlertService;
    @MockBean
    AlertsPersonInfoService alertsPersonInfoService;
    @MockBean
    AlertsPhoneAlertService alertPhoneAlertService;
    @MockBean
    AlertsFireService alertsFireService;
    @MockBean
    AlertsFloodService alertsFloodService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetEmailsFromCity() throws Exception {
        Set<String> emails = new HashSet<>();
        String email1 = "jojo@gmail.com";
        String email2 = "jiji@gmail.com";
        emails.add(email1);
        emails.add(email2);
        when(alertsCommunityEmailService.getEmailFromCity("Culver")).thenReturn(emails);
        mockMvc.perform(get("/communityEmail").queryParam("city", "Culver"))
                .andExpect(status().isOk());

    }

    @Test
    void testGetEmailsFromCityReturnEmptyList() throws Exception {
        Set<String> emails = new HashSet<>();
        when(alertsCommunityEmailService.getEmailFromCity("Paris")).thenReturn(emails);
        mockMvc.perform(get("/communityEmail").queryParam("city", "Paris"))
                .andExpect(status().isNotFound());

    }

    @Test
    void testGetPersonFromFireStation() throws Exception {
        PersonFireStationDto personFireStationDto = new PersonFireStationDto();
        Set<PersonLightFireStationDto> personLightFireStationDtoSet = new HashSet<>();
        PersonLightFireStationDto personLightFireStationDto = new PersonLightFireStationDto();
        personLightFireStationDto.setLastName("Test");
        personLightFireStationDtoSet.add(personLightFireStationDto);
        personFireStationDto.setPersonLightFireStationDtoSet(personLightFireStationDtoSet);
        when(alertsFireStationService.getPersonFromFireStation(1)).thenReturn(personFireStationDto);
        mockMvc.perform(get("/firestation").queryParam("stationNumber", "1"))
                .andExpect(status().isOk());

    }

    @Test
    void testGetPersonFromFireStationWithBadStationNumber() throws Exception {
        PersonFireStationDto personFireStationDto = new PersonFireStationDto();
        Set<PersonLightFireStationDto> personLightFireStationDtoSet = new HashSet<>();
        personFireStationDto.setPersonLightFireStationDtoSet(personLightFireStationDtoSet);
        when(alertsFireStationService.getPersonFromFireStation(9)).thenReturn(personFireStationDto);
        mockMvc.perform(get("/firestation").queryParam("stationNumber", "9"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetPersonFromAddress() throws Exception {
        PersonChildAlertDto personChildAlertDto = new PersonChildAlertDto();
        Set<PersonLightChildAlertDto> children = new HashSet<>();
        PersonLightChildAlertDto child = new PersonLightChildAlertDto();
        child.setLastName("Test");
        children.add(child);
        personChildAlertDto.setChildren(children);
        when(alertsChildAlertService.getPersonFromAddress("1509 Culver St")).thenReturn(personChildAlertDto);
        mockMvc.perform(get("/childAlert").queryParam("address", "1509 Culver St"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetPersonFromAddressWithBadAddress() throws Exception {
        PersonChildAlertDto personChildAlertDto = new PersonChildAlertDto();
        Set<PersonLightChildAlertDto> children = new HashSet<>();
        personChildAlertDto.setChildren(children);
        when(alertsChildAlertService.getPersonFromAddress("9999 Culver St")).thenReturn(personChildAlertDto);
        mockMvc.perform(get("/childAlert").queryParam("address", "9999 Culver St"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetPersonInfo() throws Exception {
        PersonInfoDto personInfoDto = new PersonInfoDto();
        Set<String> medications = new HashSet<>();
        Set<String> allergies = new HashSet<>();
        personInfoDto.setLastName("Lily");
        personInfoDto.setAddress("489 Manchester St");
        personInfoDto.setEmail("lily@email.com");
        personInfoDto.setAllergies(allergies);
        personInfoDto.setMedications(medications);
        when(alertsPersonInfoService.getPersonInfo("Lily", "Cooper")).thenReturn(personInfoDto);
        mockMvc.perform(get("/personInfo").queryParam("firstName", "Lily").queryParam("lastName", "Cooper"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetPersonInfoWithUnknownPerson() throws Exception {
        PersonInfoDto personInfoDto = new PersonInfoDto();
        when(alertsPersonInfoService.getPersonInfo("Lilo", "Crooper")).thenReturn(personInfoDto);
        mockMvc.perform(get("/personInfo").queryParam("firstName", "Lilo").queryParam("lastName", "Crooper"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getPhoneNumberFromStation() throws Exception {
        Set<String> phoneNumbers = new HashSet<>();
        String phoneNumber1 = "841-874-9888";
        String phoneNumber2 = "841-874-9887";
        phoneNumbers.add(phoneNumber1);
        phoneNumbers.add(phoneNumber2);
        when(alertPhoneAlertService.getPhoneNumberFromStation(1)).thenReturn(phoneNumbers);
        mockMvc.perform(get("/phoneAlert").queryParam("firestation", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void getPhoneNumberFromStationWithBadStationNumber() throws Exception {
        Set<String> phoneNumbers = new HashSet<>();
        when(alertPhoneAlertService.getPhoneNumberFromStation(9)).thenReturn(phoneNumbers);
        mockMvc.perform(get("/phoneAlert").queryParam("firestation", "9"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getPersonFromAddressWithStation() throws Exception {
        PersonFireDto personFireDto = new PersonFireDto();
        Set<PersonLightFireDto> personLightFireDtoSet = new HashSet<>();
        PersonLightFireDto personLightFireDto = new PersonLightFireDto();
        personLightFireDto.setLastName("Test");
        personLightFireDtoSet.add(personLightFireDto);
        personFireDto.setPersonLightFireDtoSet(personLightFireDtoSet);
        when(alertsFireService.getPersonFromAddressWithStation("908 73rd St")).thenReturn(personFireDto);
        mockMvc.perform(get("/fire").queryParam("address", "908 73rd St"))
                .andExpect(status().isOk());
    }

    @Test
    void getPersonFromAddressWithStationWithBadAddress() throws Exception {
        PersonFireDto personFireDto = new PersonFireDto();
        Set<PersonLightFireDto> personLightFireDtoSet = new HashSet<>();
        personFireDto.setPersonLightFireDtoSet(personLightFireDtoSet);
        when(alertsFireService.getPersonFromAddressWithStation("999 73rd St")).thenReturn(personFireDto);
        mockMvc.perform(get("/fire").queryParam("address", "999 73rd St"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getFamilyByListOfStation() throws Exception {
        PersonFloodDto personFloodDto = new PersonFloodDto();
        Set<Integer> stationsNumber = new HashSet<>();
        stationsNumber.add(1);
        stationsNumber.add(2);
        for (int i = 0; i < 2; i++) {
            Set<Set<PersonLightFireDto>> result = new HashSet<>();
            Set<PersonLightFireDto> personLightFireDtoSet = new HashSet<>();
            PersonLightFireDto personLightFireDto = new PersonLightFireDto();
            personLightFireDto.setLastName("Test");
            personLightFireDtoSet.add(personLightFireDto);
            result.add(personLightFireDtoSet);
            personFloodDto.setSetPersonLightFireDtoSet(result);
        }
        when(alertsFloodService.getFamilyByListOfStation(stationsNumber)).thenReturn(personFloodDto);
        mockMvc.perform(get("/flood/stations").queryParam("stations", "1,2"))
                .andExpect(status().isOk());
    }

    @Test
    void getFamilyByListOfStationWithBadListOfStation() throws Exception {
        PersonFloodDto personFloodDto = new PersonFloodDto();
        Set<Integer> stationsNumber = new HashSet<>();
        stationsNumber.add(7);
        stationsNumber.add(8);
        Set<Set<PersonLightFireDto>> result = new HashSet<>();
        personFloodDto.setSetPersonLightFireDtoSet(result);
        when(alertsFloodService.getFamilyByListOfStation(stationsNumber)).thenReturn(personFloodDto);
        mockMvc.perform(get("/flood/stations").queryParam("stations", "7,8"))
                .andExpect(status().isNotFound());
    }

}
