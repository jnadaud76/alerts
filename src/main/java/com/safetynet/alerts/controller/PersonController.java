package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Objects;
import java.util.Set;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/person")
    public Set<Person> getPersons() {
        return personService.getPersons();
    }

    @GetMapping(value = "person/{firstName}/{lastName}")
    public ResponseEntity<Person> getPerson(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
        if (personService.getPerson(firstName, lastName)!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(personService.getPerson(firstName, lastName));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @PostMapping(value="/person")
    public ResponseEntity<String> createPerson (@RequestBody Person person){
        personService.createPerson(person);
        return new ResponseEntity<>("Person created", HttpStatus.CREATED);
    }

    @PutMapping(value="/person")
    public void updatePerson (@RequestBody Person person) throws Exception {
        personService.updatePerson(person);
    }

    @DeleteMapping(value = "person/{firstName}/{lastName}")
    public ResponseEntity<?> deletePerson (@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
        try {
            personService.deletePerson(firstName, lastName);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully Deleted");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cant delete! Entity not exist");
        }

    }
    }


