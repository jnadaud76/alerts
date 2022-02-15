package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.dto.MedicalRecordFullDto;
import com.safetynet.alerts.dto.PersonFullDto;
import com.safetynet.alerts.dto.PersonInfoDto;
import com.safetynet.alerts.service.IAlertsPersonInfoService;
import com.safetynet.alerts.service.IMedicalRecordService;
import com.safetynet.alerts.service.IPersonService;
import com.safetynet.alerts.util.Calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertsPersonInfoService implements IAlertsPersonInfoService {
    /**
     * @see Calculator
     */
    private final Calculator calculator = new Calculator();
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
     * Get person's information by id.
     *
     * @param firstName first part of unique id.
     * @param lastName  second part of unique id.
     * @return information about a person.
     */
    public PersonInfoDto
    getPersonInfo(final String firstName, final String lastName) {
        PersonInfoDto personInfoDto = new PersonInfoDto();
        PersonFullDto person = personService.getPerson(firstName, lastName);
        MedicalRecordFullDto medicalRecord
                = medicalRecordService.getMedicalRecord(firstName, lastName);
        if (person != null) {
            personInfoDto.setLastName(person.getLastName());
            personInfoDto.setAddress(person.getAddress());
            personInfoDto
                    .setAge(calculator
                            .calculateAge(medicalRecord.getBirthdate()));
            personInfoDto.setEmail(person.getEmail());
            personInfoDto.setMedications(medicalRecord.getMedications());
            personInfoDto.setAllergies(medicalRecord.getAllergies());
        }

        return personInfoDto;

    }
}
