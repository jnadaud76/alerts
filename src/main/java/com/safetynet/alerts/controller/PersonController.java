package com.safetynet.alerts.controller;


import com.safetynet.alerts.dto.PersonFullDto;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.IPersonService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class PersonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private IPersonService personService;

    @GetMapping("/persons")
    public Set<PersonFullDto> getPersons() {
        LOGGER.info("Persons successfully found - code : {}", HttpStatus.OK);
        return personService.getPersons();
    }

    @GetMapping(value = "person/")
    public ResponseEntity<PersonFullDto>
    getPerson(@RequestParam("firstName") final String firstName,
              @RequestParam("lastName") final String lastName) {
        if (personService.getPerson(firstName, lastName) != null) {
            LOGGER.info("Person successfully found - code : {}", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(personService.getPerson(firstName, lastName));
        } else {
            LOGGER.error("Person not found - code : {}", HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }


    @PostMapping(value = "/person")
    public ResponseEntity<?> createPerson(@RequestBody final Person person) {
        try {
            personService.createPerson(person);
            LOGGER.info("Person successfully created - code : {}", HttpStatus.CREATED);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Person created");
        } catch (IllegalArgumentException e) {
            LOGGER.error("Person can't be create - code : {}", HttpStatus.BAD_REQUEST, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Person can't be create. May already exist");
        }
    }

    @PutMapping(value = "/person")
    public ResponseEntity<?> updatePerson(@RequestBody final Person person) {
        try {
            personService.updatePerson(person);
            LOGGER.info("Person successfully updated - code : {}", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Successfully updated");

        } catch (Exception e) {
            LOGGER.error("Person can't be update - code : {}", HttpStatus.BAD_REQUEST, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Cant Update! Entity not exist");
        }
    }

    @DeleteMapping(value = "person/")
    public ResponseEntity<?>
    deletePerson(@RequestParam("firstName") final String firstName,
                 @RequestParam("lastName") final String lastName) {
        try {
            personService.deletePerson(firstName, lastName);
            LOGGER.info("Person successfully deleted - code : {}", HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Successfully deleted");

        } catch (Exception e) {
            LOGGER.error("Person can't be delete - code : {}", HttpStatus.BAD_REQUEST, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Cant delete! Entity not exist");
        }

    }
}


