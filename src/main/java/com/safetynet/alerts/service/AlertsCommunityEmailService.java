package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.PersonFullDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AlertsCommunityEmailService implements IAlertsCommunityEmailService {

    @Autowired
    private IPersonService personService;

    public Set<String> getEmailFromCity(final String city) {
        Set<PersonFullDto> persons = personService.getPersons();
        Set<String> personsEmail = new HashSet<>();

        for (PersonFullDto person : persons) {
            if (person.getCity().equals(city)) {
                String personEmail = person.getEmail();
                personsEmail.add(personEmail);

            }
        }
        return personsEmail;
    }

}
