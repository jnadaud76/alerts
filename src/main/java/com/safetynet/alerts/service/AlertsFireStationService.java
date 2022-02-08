package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.FireStationFullDto;
import com.safetynet.alerts.dto.MedicalRecordFullDto;
import com.safetynet.alerts.dto.PersonFireStationDto;
import com.safetynet.alerts.dto.PersonFullDto;
import com.safetynet.alerts.dto.PersonLightFireStationDto;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.util.Calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AlertsFireStationService {

    @Autowired
    FireStationService fireStationService;

    @Autowired
    PersonService personService;

    @Autowired
    MedicalRecordService medicalRecordService;

    Calculator calculator = new Calculator();


    public PersonFireStationDto getPersonFromFireStation (final int station) {
        PersonFireStationDto personFireStationDto = new PersonFireStationDto();
        Set<FireStationFullDto> fireStationSet = fireStationService.getFireStationsByStation(station);
        Set<PersonFullDto> personSet = personService.getPersons();
        Set<MedicalRecordFullDto> medicalRecordSet = medicalRecordService.getMedicalRecords();
        Set<PersonLightFireStationDto> personLightFireStationDtoSet = new HashSet<>();
        int numberAdult = 0;
        int numberChild = 0;
        for (FireStationFullDto fireStation : fireStationSet) {
            for (PersonFullDto p : personSet) {
                if (p.getAddress().equals(fireStation.getAddress())) {
                    PersonLightFireStationDto personLightFireStationDto = new PersonLightFireStationDto();
                    personLightFireStationDto.setFirstName(p.getFirstName());
                    personLightFireStationDto.setLastName(p.getLastName());
                    personLightFireStationDto.setAddress(p.getAddress());
                    personLightFireStationDto.setPhone(p.getPhone());
                    personLightFireStationDtoSet.add(personLightFireStationDto);
                }
            }
        }
        for (PersonLightFireStationDto p : personLightFireStationDtoSet)
            for (MedicalRecordFullDto m : medicalRecordSet) {
                if (m.getFirstName().equals(p.getFirstName())&&m.getLastName().equals(p.getLastName())){
                    long age = calculator.calculateAge(m.getBirthdate());
                    if (age>=18) {
                        numberAdult++;
                    }else {
                        numberChild++;
                    }

                }
            }



        personFireStationDto.setPersonLightFireStationDtoSet(personLightFireStationDtoSet);
        personFireStationDto.setNumberAdult(numberAdult);
        personFireStationDto.setNumberChild(numberChild);
        return personFireStationDto;
    }
}
