package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.PersonFullDto;
import com.safetynet.alerts.model.Person;

import java.util.Set;

public interface IPersonService {

    Set<PersonFullDto> getPersons();

    Set<PersonFullDto> getPersonByAddress(String address);

    PersonFullDto getPerson(String firstname, String lastName);

    void createPerson(Person personParam);

    void updatePerson(Person personParam) throws Exception;

    void deletePerson(String firstname, String lastName);
}
