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
class PersonControllerIT {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetPersons() throws Exception {
        mockMvc.perform(get("/persons"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetPerson() throws Exception {
        mockMvc.perform(get("/person/")
                        .queryParam("firstName", "Allison")
                        .queryParam("lastName", "Boyd"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Allison")));
    }

    @Test
    void testGetPersonWithGoodFirstNameAndBadLastName() throws Exception {
        mockMvc.perform(get("/person/")
                        .queryParam("firstName", "Lily")
                        .queryParam("lastName", "Crooper"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetPersonWithBadFirstNameAnGoodLastName() throws Exception {
        mockMvc.perform(get("/person/")
                        .queryParam("firstName", "Lilo")
                        .queryParam("lastName", "Cooper"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetPersonWithBadFirstNameAnBadLastName() throws Exception {
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
        mockMvc.perform(delete("/person/")
                        .queryParam("firstName", "Brian")
                        .queryParam("lastName", "Stelzor"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDeletePersonWithBadFirstNameAnGoodLastName() throws Exception {
        mockMvc.perform(delete("/person/")
                        .queryParam("firstName", "Briun")
                        .queryParam("lastName", "Stelzer"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDeletePersonWithBadFirstNameAnBadLastName() throws Exception {
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

        mockMvc.perform(put("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personAsString))
                .andExpect(status().isBadRequest());
    }
}

