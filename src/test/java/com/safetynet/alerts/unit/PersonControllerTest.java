package com.safetynet.alerts.unit;

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

    /* @BeforeEach
    public void setupTest() {

    }*/

    @Test
    public void testGetPersons() throws Exception {
        mockMvc.perform(get("/person"))
                .andExpect(status().isOk());
    }

    /*@Test
    public void testGetPerson() throws Exception {
        mockMvc.perform(get("/person/Lily/Cooper"))
                .andExpect(status().isOk());
    }*/

    @Test
    public void testGetPersonWithGoodFirstNameAndBadLastName() throws Exception {
        mockMvc.perform(get("/person/Lily/Crooper"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetPersonWithBadFirstNameAnGoodLastName() throws Exception {
        mockMvc.perform(get("/person/Lilo/Cooper"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetPersonWithBadFirstNameAnBadLastName() throws Exception {
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
    public void testDeletePerson() throws Exception {
        mockMvc.perform(delete("/person/Brian/Stelzer"))
                .andExpect(status().isOk());
    }

    /*@Test
    public void testDeletePersonWithGoodFirstNameAndBadLastName() throws Exception {
        mockMvc.perform(delete("/person/Brian/Stelzor"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeletePersonWithBadFirstNameAnGoodLastName() throws Exception {
        mockMvc.perform(delete("/person/Briun/Stelzer"))
                .andExpect(status().isNotFound());
    }*/

    /*@Test
    public void testDeletePersonWithBadFirstNameAnBadLastName() throws Exception {
        mockMvc.perform(delete("/person/Briun/Stelzor"))
                .andExpect(status().isNotFound());
    }*/



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


}
