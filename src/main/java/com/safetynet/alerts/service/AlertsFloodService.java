package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.FireStationFullDto;
import com.safetynet.alerts.dto.MedicalRecordFullDto;
import com.safetynet.alerts.dto.PersonFireDto;
import com.safetynet.alerts.dto.PersonFloodDto;
import com.safetynet.alerts.dto.PersonFullDto;
import com.safetynet.alerts.dto.PersonLightFireDto;
import com.safetynet.alerts.dto.PersonLightFireStationDto;
import com.safetynet.alerts.util.Calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Service
public class AlertsFloodService {

    @Autowired
    FireStationService fireStationService;

    @Autowired
    PersonService personService;

    @Autowired
    MedicalRecordService medicalRecordService;

    Calculator calculator = new Calculator();

    public Set<PersonLightFireDto> getFamilyFromAddress(String address) {
        Set<PersonFullDto> personFullDtoSet = personService.getPersonByAddress(address);
        Set<MedicalRecordFullDto> medicalRecordFullDtoSet = medicalRecordService.getMedicalRecords();
        Set<PersonLightFireDto> personLightFireDtoSet = new HashSet<>();
        for (PersonFullDto p : personFullDtoSet) {
            PersonLightFireDto personLightFireDto = new PersonLightFireDto();
            personLightFireDto.setLastName(p.getLastName());
            personLightFireDto.setPhone(p.getPhone());

            for (MedicalRecordFullDto m : medicalRecordFullDtoSet) {
                if (m.getFirstName().equals(p.getFirstName()) && m.getLastName().equals(p.getLastName())) {
                    personLightFireDto.setAge(calculator.calculateAge(m.getBirthdate()));
                    personLightFireDto.setMedications(m.getMedications());
                    personLightFireDto.setAllergies(m.getAllergies());
                }
            }
            personLightFireDtoSet.add(personLightFireDto);

        }


        return personLightFireDtoSet;
    }

    public PersonFloodDto getFamilyByListOfStation(Set<Integer> stations) {
        PersonFloodDto personFloodDto = new PersonFloodDto();
        Iterator<Integer> i = stations.iterator();

        Set<Set<PersonLightFireDto>> setPersonLightFireDtoSet = new HashSet<>();

        while (i.hasNext()) {
            Set<FireStationFullDto> fireStationSet = fireStationService.getFireStationsByStation(i.next());
            for (FireStationFullDto f  : fireStationSet) {
                Set<PersonLightFireDto> personLightFireDtoSet;
                String address = f.getAddress();
                personLightFireDtoSet = getFamilyFromAddress(address);
                setPersonLightFireDtoSet.add(personLightFireDtoSet);
            }
        }
        personFloodDto.setSetPersonLightFireDtoSet(setPersonLightFireDtoSet);
        return personFloodDto;

       /* Set<FireStationFullDto> fireStationSet = fireStationService.getFireStationsByStation();
        Set<PersonFullDto> personSet = personService.getPersons();
        Set<MedicalRecordFullDto> medicalRecordSet = medicalRecordService.getMedicalRecords();


        Set<Set<PersonLightFireDto>> setPersonLightFireDtoSet = new HashSet<>();

        for (FireStationFullDto fireStation : fireStationSet) {
            for (PersonFullDto p : personSet) {
                if (p.getAddress().equals(fireStation.getAddress())) {
                    PersonLightFireDto personLightFireDto = new PersonLightFireDto();
                    personLightFireDto.setLastName(p.getLastName());
                    personLightFireDto.setPhone(p.getPhone());
                    for (MedicalRecordFullDto m : medicalRecordSet) {
                        if (m.getFirstName().equals(p.getFirstName()) && m.getLastName().equals(p.getLastName())) {
                            personLightFireDto.setAge(calculator.calculateAge(m.getBirthdate()));
                            personLightFireDto.setMedications(m.getMedications());
                            personLightFireDto.setAllergies(m.getAllergies());
                            personLightFireDtoSet.add(personLightFireDto);
                        }
                    }
                    setPersonLightFireDtoSet.add(personLightFireDtoSet);

                }

            }
            personFloodDto.setSetPersonLightFireDtoSet(setPersonLightFireDtoSet);
        }


        return personFloodDto;
    }*/


    }
}