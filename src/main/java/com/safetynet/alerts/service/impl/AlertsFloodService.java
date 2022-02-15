package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.dto.FireStationFullDto;
import com.safetynet.alerts.dto.MedicalRecordFullDto;
import com.safetynet.alerts.dto.PersonFloodDto;
import com.safetynet.alerts.dto.PersonFullDto;
import com.safetynet.alerts.dto.PersonLightFireDto;
import com.safetynet.alerts.service.IAlertsFloodService;
import com.safetynet.alerts.service.IFireStationService;
import com.safetynet.alerts.service.IMedicalRecordService;
import com.safetynet.alerts.service.IPersonService;
import com.safetynet.alerts.util.Calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Service
public class AlertsFloodService implements IAlertsFloodService {
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
     * Get a set of people by address.
     *
     * @param address a home address.
     * @return a set of person.
     */
    public Set<PersonLightFireDto> getFamilyFromAddress(final String address) {
        Set<PersonFullDto> personFullDtoSet
                = personService.getPersonByAddress(address);
        Set<MedicalRecordFullDto> medicalRecordFullDtoSet
                = medicalRecordService.getMedicalRecords();
        Set<PersonLightFireDto> personLightFireDtoSet = new HashSet<>();
        for (PersonFullDto p : personFullDtoSet) {
            PersonLightFireDto personLightFireDto = new PersonLightFireDto();
            personLightFireDto.setLastName(p.getLastName());
            personLightFireDto.setPhone(p.getPhone());

            for (MedicalRecordFullDto m : medicalRecordFullDtoSet) {
                if (m.getFirstName().equals(p.getFirstName())
                        && m.getLastName().equals(p.getLastName())) {
                    personLightFireDto
                            .setAge(calculator.calculateAge(m.getBirthdate()));
                    personLightFireDto.setMedications(m.getMedications());
                    personLightFireDto.setAllergies(m.getAllergies());
                }
            }
            personLightFireDtoSet.add(personLightFireDto);

        }
        return personLightFireDtoSet;
    }

    /**
     * Get a set of people served by the fire station, grouped by address.
     *
     * @param stations a set of station number.
     * @return a set of people served by the fire station, grouped by address.
     */
    public PersonFloodDto getFamilyByListOfStation(final Set<Integer> stations) {
        PersonFloodDto personFloodDto = new PersonFloodDto();
        Iterator<Integer> i = stations.iterator();

        Set<Set<PersonLightFireDto>> setPersonLightFireDtoSet = new HashSet<>();

        while (i.hasNext()) {
            Set<FireStationFullDto> fireStationSet
                    = fireStationService.getFireStationsByStation(i.next());
            for (FireStationFullDto f : fireStationSet) {
                Set<PersonLightFireDto> personLightFireDtoSet;
                String address = f.getAddress();
                personLightFireDtoSet = getFamilyFromAddress(address);
                setPersonLightFireDtoSet.add(personLightFireDtoSet);
            }
        }
        personFloodDto.setSetPersonLightFireDtoSet(setPersonLightFireDtoSet);
        return personFloodDto;
    }
}
