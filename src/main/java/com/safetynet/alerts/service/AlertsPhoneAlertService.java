package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.FireStationFullDto;
import com.safetynet.alerts.dto.PersonFullDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AlertsPhoneAlertService {

@Autowired
FireStationService fireStationService;

@Autowired
    PersonService personService;

public Set<String> getPhoneNumberFromStation (int station) {
    Set<FireStationFullDto> fireStationFullDtoSet = fireStationService.getFireStationsByStation(station);
    Set<PersonFullDto> personFullDtoSet = personService.getPersons();
    Set<String> phoneNumbers = new HashSet<>();

    for (PersonFullDto p : personFullDtoSet) {
        for (FireStationFullDto f : fireStationFullDtoSet) {
            if (f.getAddress().equals(p.getAddress())) {
                phoneNumbers.add(p.getPhone());
            }
        }
    }
    return phoneNumbers;
}

}
