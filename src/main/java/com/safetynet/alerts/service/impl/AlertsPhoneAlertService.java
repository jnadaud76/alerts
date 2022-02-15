package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.dto.FireStationFullDto;
import com.safetynet.alerts.dto.PersonFullDto;
import com.safetynet.alerts.service.IAlertsPhoneAlertService;
import com.safetynet.alerts.service.IFireStationService;
import com.safetynet.alerts.service.IPersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AlertsPhoneAlertService implements IAlertsPhoneAlertService {

    @Autowired
    private IFireStationService fireStationService;

    @Autowired
    private IPersonService personService;

    public Set<String> getPhoneNumberFromStation(final int station) {
        Set<FireStationFullDto> fireStationFullDtoSet
                = fireStationService.getFireStationsByStation(station);
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
