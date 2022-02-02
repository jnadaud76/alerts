package com.safetynet.alerts.integration;


import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.Person;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetPersons() throws Exception {
        mockMvc.perform(get("/person"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("Lily")));
    }

    @Test
    public void testGetPerson() throws Exception {
        mockMvc.perform(get("/person/Allison/Boyd"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Allison")));
    }

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

        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personAsString))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeletePerson() throws Exception {
        mockMvc.perform(delete("/person/Brian/Stelzer"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletePersonWithGoodFirstNameAndBadLastName() throws Exception {
        mockMvc.perform(delete("/person/Brian/Stelzor"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeletePersonWithBadFirstNameAnGoodLastName() throws Exception {
        mockMvc.perform(delete("/person/Briun/Stelzer"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeletePersonWithBadFirstNameAnBadLastName() throws Exception {
        mockMvc.perform(get("/person/Briun/Stelzor"))
                .andExpect(status().isNotFound());
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

        mockMvc.perform(put("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personAsString))
                .andExpect(status().isNotFound());
    }
}

