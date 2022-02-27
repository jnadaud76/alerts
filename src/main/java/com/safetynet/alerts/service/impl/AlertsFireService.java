package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.dto.FireStationFullDto;
import com.safetynet.alerts.dto.MedicalRecordFullDto;
import com.safetynet.alerts.dto.PersonFireDto;
import com.safetynet.alerts.dto.PersonFullDto;
import com.safetynet.alerts.dto.PersonLightFireDto;
import com.safetynet.alerts.service.IAlertsFireService;
import com.safetynet.alerts.service.IFireStationService;
import com.safetynet.alerts.service.IMedicalRecordService;
import com.safetynet.alerts.service.IPersonService;
import com.safetynet.alerts.util.Calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AlertsFireService implements IAlertsFireService {

    /**
     * @see Calculator
     */
    @Autowired
    private Calculator calculator;
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
     * Get all person living at address provided and the number of the
     * fire station on which they depend.
     *
     * @param address a home address
     * @return a set of person and a station number.
     */
    public PersonFireDto getPersonFromAddressWithStation(final String address) {
        Set<FireStationFullDto> fireStationFullDtoSet
                = fireStationService.getFireStationsByAddress(address);
        Set<PersonFullDto> personFullDtoSet
                = personService.getPersonByAddress(address);
        Set<MedicalRecordFullDto> medicalRecordFullDtoSet
                = medicalRecordService.getMedicalRecords();
        PersonFireDto personFireDto = new PersonFireDto();
        Set<PersonLightFireDto> personLightFireDtoSet = new HashSet<>();
        Set<Integer> stationResult = new HashSet<>();
        for (FireStationFullDto f : fireStationFullDtoSet) {
            int station = f.getStation();
            stationResult.add(station);
        }
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
        personFireDto.setPersonLightFireDtoSet(personLightFireDtoSet);
        personFireDto.setStation(stationResult);
        return personFireDto;
    }
}
