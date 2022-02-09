package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.MedicalRecordFullDto;
import com.safetynet.alerts.dto.PersonFullDto;
import com.safetynet.alerts.dto.PersonInfoDto;
import com.safetynet.alerts.util.Calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertsPersonInfoService implements IAlertsPersonInfoService {

    private final Calculator calculator = new Calculator();
    @Autowired
    private IPersonService personService;
    @Autowired
    private IMedicalRecordService medicalRecordService;

    public PersonInfoDto
    getPersonInfo(final String firstname, final String lastName) {
        PersonInfoDto personInfoDto = new PersonInfoDto();
        PersonFullDto person = personService.getPerson(firstname, lastName);
        MedicalRecordFullDto medicalRecord
                = medicalRecordService.getMedicalRecord(firstname, lastName);
        if (person != null) {
            personInfoDto.setLastName(person.getLastName());
            personInfoDto.setAddress(person.getAddress());
            personInfoDto
                    .setAge(calculator.calculateAge(medicalRecord.getBirthdate()));
            personInfoDto.setEmail(person.getEmail());
            personInfoDto.setMedications(medicalRecord.getMedications());
            personInfoDto.setAllergies(medicalRecord.getAllergies());
        }

        return personInfoDto;

    }
}
