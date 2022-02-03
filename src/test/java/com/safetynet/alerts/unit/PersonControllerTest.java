package com.safetynet.alerts.unit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.controller.PersonController;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;
import com.safetynet.alerts.repository.PersonDao;
import com.safetynet.alerts.service.ReadDataFromJson;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @MockBean
    private PersonDao personDao;

    @Test
    public void testGetPersons() throws Exception {
        mockMvc.perform(get("/person"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPerson() throws Exception {
        Person person = new Person();
        person.setFirstName("Lily");
        person.setLastName("Cooper");
        when(personDao.findById("Lily","Cooper")).thenReturn(person);
        when(personService.getPerson("Lily","Cooper")).thenReturn(person);
        mockMvc.perform(get("/person/Lily/Cooper"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPersonWithGoodFirstNameAndBadLastName() throws Exception {
        when(personDao.findById("Lily","Crooper")).thenReturn(null);
        when(personService.getPerson("Lily","Crooper")).thenReturn(null);
        mockMvc.perform(get("/person/Lily/Crooper"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetPersonWithBadFirstNameAnGoodLastName() throws Exception {
        when(personDao.findById("Lilo","Cooper")).thenReturn(null);
        when(personService.getPerson("Lilo","Cooper")).thenReturn(null);
        mockMvc.perform(get("/person/Lilo/Cooper"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetPersonWithBadFirstNameAnBadLastName() throws Exception {
        when(personDao.findById("Lilo","Crooper")).thenReturn(null);
        when(personService.getPerson("Lilo","Crooper")).thenReturn(null);
        mockMvc.perform(get("/person/Lilo/Crooper"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreatePerson() throws Exception {
        Person person = new Person();
        person.setFirstName("Test");
        person.setLastName("Test");
        person.setAddress("test test test");
        person.setCity("Paris");
        person.setZip(75008);
        person.setPhone("0807060504");
        person.setEmail("test@email.com");
        ObjectMapper objectMapper = new ObjectMapper();
        String personAsString = objectMapper.writeValueAsString(person);

        mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(personAsString))
                .andExpect(status().isCreated());
    }

    @Test
    public void testCreatePersonWhichAlreadyExist() throws Exception {
        Person person = new Person();
        person.setFirstName("Lily");
        person.setLastName("Cooper");
        person.setAddress("489 Manchester St");
        person.setCity("Culver");
        person.setZip(97451);
        person.setPhone("841-874-9845");
        person.setEmail("lily@email.com");
        ObjectMapper objectMapper = new ObjectMapper();
        String personAsString = objectMapper.writeValueAsString(person);
        doThrow(new IllegalArgumentException()).when(personDao).save(person);
        doThrow(new IllegalArgumentException()).when(personService).createPerson(person);

        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personAsString))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeletePerson() throws Exception {
        Person person = new Person();
        person.setFirstName("Brian");
        person.setLastName("Stelzer");
        when(personDao.findById("Brian","Stelzer")).thenReturn(person);
        mockMvc.perform(delete("/person/Brian/Stelzer"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletePersonWithGoodFirstNameAndBadLastName() throws Exception {
        doThrow(new IllegalArgumentException()).when(personDao).deleteById("Brian","Stelzor");
        doThrow(new IllegalArgumentException()).when(personService).deletePerson("Brian","Stelzor");
        mockMvc.perform(delete("/person/Brian/Stelzor"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeletePersonWithBadFirstNameAnGoodLastName() throws Exception {
        doThrow(new IllegalArgumentException()).when(personDao).deleteById("Briun","Stelzer");
        doThrow(new IllegalArgumentException()).when(personService).deletePerson("Briun","Stelzer");
        mockMvc.perform(delete("/person/Briun/Stelzer"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeletePersonWithBadFirstNameAnBadLastName() throws Exception {
        doThrow(new IllegalArgumentException()).when(personDao).deleteById("Briun","Stelzor");
        doThrow(new IllegalArgumentException()).when(personService).deletePerson("Briun","Stelzor");
        mockMvc.perform(delete("/person/Briun/Stelzor"))
                .andExpect(status().isBadRequest());
    }



    @Test
    public void testUpdatePerson() throws Exception {
        Person person = new Person();
        person.setFirstName("Lily");
        person.setLastName("Cooper");
        person.setAddress("adresse modifiée");
        person.setCity("Culver");
        person.setZip(97451);
        person.setPhone("841-874-9845");
        person.setEmail("lily@email.com");

        ObjectMapper objectMapper = new ObjectMapper();
        String personAsString = objectMapper.writeValueAsString(person);


        mockMvc.perform(put("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personAsString))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdatePersonGivingBadLastName() throws Exception {
        Person person = new Person();
        person.setFirstName("Lily");
        person.setLastName("Crooper");
        person.setAddress("adresse modifiée");
        person.setCity("Culver");
        person.setZip(97451);
        person.setPhone("841-874-9845");
        person.setEmail("lily@email.com");

        ObjectMapper objectMapper = new ObjectMapper();
        String personAsString = objectMapper.writeValueAsString(person);

        doThrow(new IllegalArgumentException()).when(personDao).update(person);
        doThrow(new IllegalArgumentException()).when(personService).updatePerson(person);

        mockMvc.perform(put("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personAsString))
                .andExpect(status().isBadRequest());
    }


}
