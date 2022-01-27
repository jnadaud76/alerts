package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.Person;

import java.util.Set;

public interface IPersonDao {
    Set<Person> getPersons();
}
