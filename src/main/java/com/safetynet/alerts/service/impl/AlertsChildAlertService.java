package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.dto.MedicalRecordFullDto;
import com.safetynet.alerts.dto.PersonChildAlertDto;
import com.safetynet.alerts.dto.PersonFullDto;
import com.safetynet.alerts.dto.PersonLightChildAlertDto;
import com.safetynet.alerts.service.IAlertsChildAlertService;
import com.safetynet.alerts.service.IMedicalRecordService;
import com.safetynet.alerts.service.IPersonService;
import com.safetynet.alerts.util.Calculator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AlertsChildAlertService implements IAlertsChildAlertService {
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

    @Value("${MAJORITY}")
    private int MAJORITY;

    /**
     * Get children and adults living at address.
     *
     * @param address a home address.
     * @return a set of children and adults living at address.
     */
    public PersonChildAlertDto getPersonFromAddress(final String address) {
        Set<PersonFullDto> persons = personService.getPersonByAddress(address);
        Set<MedicalRecordFullDto> medicalRecordSet = medicalRecordService
                .getMedicalRecords();
        PersonChildAlertDto personChildAlertDto = new PersonChildAlertDto();
        Set<PersonLightChildAlertDto> children = new HashSet<>();
        Set<PersonLightChildAlertDto> familyMembers = new HashSet<>();
        for (PersonFullDto p : persons) {
            for (MedicalRecordFullDto m : medicalRecordSet) {
                if (m.getFirstName().equals(p.getFirstName())
                        && m.getLastName().equals(p.getLastName())) {
                    long age = calculator.calculateAge(m.getBirthdate());
                    PersonLightChildAlertDto personLightChildAlertDto
                            = new PersonLightChildAlertDto();
                    personLightChildAlertDto.setFirstName(p.getFirstName());
                    personLightChildAlertDto.setLastName(p.getLastName());
                    personLightChildAlertDto.setAge(age);
                    if (age >= MAJORITY) {
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
