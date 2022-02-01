package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public Person getPerson(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
        return personService.getPerson(firstName, lastName);
    }

    @PostMapping(value="/person")
    public void createPerson (@RequestBody Person person){
        personService.createPerson(person);
    }

    @PutMapping(value="/person")
    public void updatePerson (@RequestBody Person person){
        personService.updatePerson(person);
    }

    @DeleteMapping(value = "person/{firstName}/{lastName}")
    public void deletePerson (@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
        personService.deletePerson(firstName, lastName);
    }

}
