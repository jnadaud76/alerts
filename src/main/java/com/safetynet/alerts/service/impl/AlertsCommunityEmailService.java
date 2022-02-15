package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.dto.PersonFullDto;
import com.safetynet.alerts.service.IAlertsCommunityEmailService;
import com.safetynet.alerts.service.IPersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AlertsCommunityEmailService implements IAlertsCommunityEmailService {
    /**
     * @see IPersonService
     */
    @Autowired
    private IPersonService personService;

    /**
     * Get all email of all person living in a city.
     *
     * @param city a city.
     * @return a set of email.
     */
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
