package com.safetynet.alerts.unit;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.controller.PersonController;
import com.safetynet.alerts.dto.PersonFullDto;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.impl.PersonService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(controllers = PersonController.class)
class PersonControllerTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PersonService personService;

    @Test
    void testGetPersons() throws Exception {
        mockMvc.perform(get("/persons"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetPerson() throws Exception {
        PersonFullDto personFullDto = new PersonFullDto();
        personFullDto.setFirstName("Lily");
        personFullDto.setLastName("Cooper");
        when(personService.getPerson("Lily", "Cooper")).thenReturn(personFullDto);
        mockMvc.perform(get("/person/")
                        .queryParam("firstName", "Lily")
                        .queryParam("lastName", "Cooper"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetPersonWithGoodFirstNameAndBadLastName() throws Exception {
        when(personService.getPerson("Lily", "Crooper")).thenReturn(null);
        mockMvc.perform(get("/person/")
                        .queryParam("firstName", "Lily")
                        .queryParam("lastName", "Crooper"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetPersonWithBadFirstNameAnGoodLastName() throws Exception {
        when(personService.getPerson("Lilo", "Cooper")).thenReturn(null);
        mockMvc.perform(get("/person/")
                        .queryParam("firstName", "Lilo")
                        .queryParam("lastName", "Cooper"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetPersonWithBadFirstNameAnBadLastName() throws Exception {
        when(personService.getPerson("Lilo", "Crooper")).thenReturn(null);
        mockMvc.perform(get("/person/")
                        .queryParam("firstName", "Lilo")
                        .queryParam("lastName", "Crooper"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreatePerson() throws Exception {
        Person person = new Person();
        person.setFirstName("Test");
        person.setLastName("Test");
        person.setAddress("test test test");
        person.setCity("Paris");
        person.setZip(75008);
        person.setPhone("0807060504");
        person.setEmail("test@email.com");
        String personAsString = objectMapper.writeValueAsString(person);

        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personAsString))
                .andExpect(status().isCreated());
    }

    @Test
    void testCreatePersonWhichAlreadyExist() throws Exception {
        Person person = new Person();
        person.setFirstName("Lily");
        person.setLastName("Cooper");
        person.setAddress("489 Manchester St");
        person.setCity("Culver");
        person.setZip(97451);
        person.setPhone("841-874-9845");
        person.setEmail("lily@email.com");
        String personAsString = objectMapper.writeValueAsString(person);
        doThrow(new IllegalArgumentException()).when(personService).createPerson(person);

        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personAsString))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDeletePerson() throws Exception {
        mockMvc.perform(delete("/person/")
                        .queryParam("firstName", "Brian")
                        .queryParam("lastName", "Stelzer"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeletePersonWithGoodFirstNameAndBadLastName() throws Exception {
        doThrow(new IllegalArgumentException()).when(personService).deletePerson("Brian", "Stelzor");
        mockMvc.perform(delete("/person/")
                        .queryParam("firstName", "Brian")
                        .queryParam("lastName", "Stelzor"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDeletePersonWithBadFirstNameAnGoodLastName() throws Exception {
        doThrow(new IllegalArgumentException()).when(personService).deletePerson("Briun", "Stelzer");
        mockMvc.perform(delete("/person/")
                        .queryParam("firstName", "Briun")
                        .queryParam("lastName", "Stelzer"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDeletePersonWithBadFirstNameAnBadLastName() throws Exception {
        doThrow(new IllegalArgumentException()).when(personService).deletePerson("Briun", "Stelzor");
        mockMvc.perform(delete("/person/")
                        .queryParam("firstName", "Briun")
                        .queryParam("lastName", "Stelzor"))
                .andExpect(status().isBadRequest());
    }


    @Test
    void testUpdatePerson() throws Exception {
        Person person = new Person();
        person.setFirstName("Lily");
        person.setLastName("Cooper");
        person.setAddress("adresse modifiée");
        person.setCity("Culver");
        person.setZip(97451);
        person.setPhone("841-874-9845");
        person.setEmail("lily@email.com");
        String personAsString = objectMapper.writeValueAsString(person);

        mockMvc.perform(put("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personAsString))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdatePersonGivingBadLastName() throws Exception {
        Person person = new Person();
        person.setFirstName("Lily");
        person.setLastName("Crooper");
        person.setAddress("adresse modifiée");
        person.setCity("Culver");
        person.setZip(97451);
        person.setPhone("841-874-9845");
        person.setEmail("lily@email.com");
        String personAsString = objectMapper.writeValueAsString(person);
        doThrow(new IllegalArgumentException()).when(personService).updatePerson(person);

        mockMvc.perform(put("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personAsString))
                .andExpect(status().isBadRequest());
    }
}
