package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.MedicalRecordFullDto;
import com.safetynet.alerts.dto.PersonChildAlertDto;
import com.safetynet.alerts.dto.PersonFullDto;
import com.safetynet.alerts.dto.PersonLightChildAlertDto;
import com.safetynet.alerts.util.Calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AlertsChildAlertService {

    @Autowired
    PersonService personService;

    @Autowired
    MedicalRecordService medicalRecordService;

    Calculator calculator = new Calculator();

    public PersonChildAlertDto getPersonFromAddress (final String address) {
        Set<PersonFullDto> persons = personService.getPersonByAddress(address);
        Set<MedicalRecordFullDto> medicalRecordSet = medicalRecordService.getMedicalRecords();
        PersonChildAlertDto personChildAlertDto = new PersonChildAlertDto();
        Set<PersonLightChildAlertDto> children = new HashSet<>();
        Set<PersonLightChildAlertDto> familyMembers = new HashSet<>();
        for (PersonFullDto p : persons) {
            for (MedicalRecordFullDto m : medicalRecordSet) {
                if (m.getFirstName().equals(p.getFirstName()) && m.getLastName().equals(p.getLastName())) {
                    long age = calculator.calculateAge(m.getBirthdate());
                    PersonLightChildAlertDto personLightChildAlertDto = new PersonLightChildAlertDto();
                    personLightChildAlertDto.setFirstName(p.getFirstName());
                    personLightChildAlertDto.setLastName(p.getLastName());
                    personLightChildAlertDto.setAge(age);
                    if (age >= 18) {
                        familyMembers.add(personLightChildAlertDto);
                    } else {
                        children.add(personLightChildAlertDto);
                    }
                }

            }
        }
        personChildAlertDto.setChildren(children);
        personChildAlertDto.setFamilyMembers(familyMembers);
        return personChildAlertDto;
    }
}
