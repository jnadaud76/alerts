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

/**
 * Endpoint : ApiUrl/person
 * <p>
 * This endpoint will perform the following actions via
 * Post/Put/Delete with HTTP:
 * <ul>
 * <li>add a new person;</li>
 * <li>update an existing person (for now, let's assume that the first and last name of
 * family do not change, but that the other fields can be modified);</li>
 * <li>delete a person (use a combination of first and last name as an identifier
 * unique).</li>
 * </ul>
 */
@RestController
public class PersonController {
    /**
     * @see Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);
    /**
     * @see IPersonService
     */
    @Autowired
    private IPersonService personService;

    /**
     * Access URL=ApiUrl/persons
     * <p>
     * Get all person.
     * </p>
     *
     * @return all person (JSON format).
     */
    @GetMapping("/persons")
    public Set<PersonFullDto> getPersons() {
        LOGGER.info("Persons successfully found - code : {}", HttpStatus.OK);
        return personService.getPersons();
    }

    /**
     * Access URL=ApiUrl/person?firstName=firstName&amp;lastName=lastName
     * <p>
     * Get a person by unique id.
     * </p>
     *
     * @param firstName first part of unique id.
     * @param lastName  second part of unique id.
     * @return the person sought if founded (JSON format) with HttpStatus ok or
     * null with HttpStatus not found otherwise.
     */
    @GetMapping(value = "/person")
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

    /**
     * Create a person.
     *
     * @param person a person (JSON format).
     * @return HttpStatus created if request is successful or HttpStatus bad
     * request otherwise.
     */
    @PostMapping(value = "/person")
    public ResponseEntity<String> createPerson(@RequestBody final Person person) {
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

    /**
     * Update a person.
     *
     * @param person a person to modify (JSON format).
     * @return HttpStatus ok if request is successful or HttpStatus bad
     * request otherwise.
     */
    @PutMapping(value = "/person")
    public ResponseEntity<String> updatePerson(@RequestBody final Person person) {
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

    /**
     * Delete a person by unique id.
     *
     * @param firstName first part of unique id to delete.
     * @param lastName  second part of unique id to delete.
     * @return HttpStatus ok if request is successful or HttpStatus bad
     * request otherwise.
     */
    @DeleteMapping(value = "/person")
    public ResponseEntity<String>
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


