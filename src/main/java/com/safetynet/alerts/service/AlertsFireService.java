package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.FireStationFullDto;
import com.safetynet.alerts.dto.MedicalRecordFullDto;
import com.safetynet.alerts.dto.PersonFireDto;
import com.safetynet.alerts.dto.PersonFullDto;
import com.safetynet.alerts.dto.PersonLightFireDto;
import com.safetynet.alerts.util.Calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AlertsFireService implements IAlertsFireService {


    private final Calculator calculator = new Calculator();
    @Autowired
    private IFireStationService fireStationService;
    @Autowired
    private IPersonService personService;
    @Autowired
    private IMedicalRecordService medicalRecordService;

    public PersonFireDto getPersonFromAddressWithStation(final String address) {
        Set<FireStationFullDto> fireStationFullDtoSet
                = fireStationService.getFireStationsByAddress(address);
        Set<PersonFullDto> personFullDtoSet
                = personService.getPersonByAddress(address);
        Set<MedicalRecordFullDto> medicalRecordFullDtoSet
                = medicalRecordService.getMedicalRecords();
        PersonFireDto personFireDto = new PersonFireDto();
        Set<PersonLightFireDto> personLightFireDtoSet = new HashSet<>();
        int station = 0;
        for (FireStationFullDto f : fireStationFullDtoSet) {
            station = f.getStation();
            break;
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
        personFireDto.setStation(station);
        return personFireDto;
    }


}
