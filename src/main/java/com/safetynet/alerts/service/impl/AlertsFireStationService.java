package com.safetynet.alerts.service.impl;

import static com.safetynet.alerts.constants.Constants.MAJORITY;

import com.safetynet.alerts.dto.FireStationFullDto;
import com.safetynet.alerts.dto.MedicalRecordFullDto;
import com.safetynet.alerts.dto.PersonFireStationDto;
import com.safetynet.alerts.dto.PersonFullDto;
import com.safetynet.alerts.dto.PersonLightFireStationDto;
import com.safetynet.alerts.service.IAlertsFireStationService;
import com.safetynet.alerts.service.IFireStationService;
import com.safetynet.alerts.service.IMedicalRecordService;
import com.safetynet.alerts.service.IPersonService;
import com.safetynet.alerts.util.Calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AlertsFireStationService implements IAlertsFireStationService {
    /**
     * @see Calculator
     */
    private final Calculator calculator = new Calculator();
    /**
     * @see IFireStationService
     */
    @Autowired
    private IFireStationService fireStationService;
    /**
     * @see IPersonService
     */
    @Autowired
    private IPersonService personService;
    /**
     * @see IMedicalRecordService
     */
    @Autowired
    private IMedicalRecordService medicalRecordService;

    /**
     * Get all person who depend on the fire station provided.
     * Also gives a count of the number of adults and children.
     *
     * @param station a station number.
     * @return a set of person with a count of the number of adults and children.
     */
    public PersonFireStationDto getPersonFromFireStation(final int station) {
        PersonFireStationDto personFireStationDto = new PersonFireStationDto();
        Set<FireStationFullDto> fireStationSet
                = fireStationService.getFireStationsByStation(station);
        Set<PersonFullDto> personSet = personService.getPersons();
        Set<MedicalRecordFullDto> medicalRecordSet
                = medicalRecordService.getMedicalRecords();
        Set<PersonLightFireStationDto> personLightFireStationDtoSet
                = new HashSet<>();
        int numberAdult = 0;
        int numberChild = 0;
        for (FireStationFullDto fireStation : fireStationSet) {
            for (PersonFullDto p : personSet) {
                if (p.getAddress().equals(fireStation.getAddress())) {
                    PersonLightFireStationDto personLightFireStationDto
                            = new PersonLightFireStationDto();
                    personLightFireStationDto.setFirstName(p.getFirstName());
                    personLightFireStationDto.setLastName(p.getLastName());
                    personLightFireStationDto.setAddress(p.getAddress());
                    personLightFireStationDto.setPhone(p.getPhone());
                    personLightFireStationDtoSet.add(personLightFireStationDto);
                }
            }
        }
        for (PersonLightFireStationDto p : personLightFireStationDtoSet) {
            for (MedicalRecordFullDto m : medicalRecordSet) {
                if (m.getFirstName().equals(p.getFirstName())
                        && m.getLastName().equals(p.getLastName())) {
                    long age = calculator.calculateAge(m.getBirthdate());
                    if (age >= MAJORITY) {
                        numberAdult++;
                    } else {
                        numberChild++;
                    }

                }
            }
        }
        personFireStationDto
                .setPersonLightFireStationDtoSet(personLightFireStationDtoSet);
        personFireStationDto.setNumberAdult(numberAdult);
        personFireStationDto.setNumberChild(numberChild);
        return personFireStationDto;
    }
}
