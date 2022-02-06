package com.safetynet.alerts.service;


import com.safetynet.alerts.dto.PersonChildAlertDto;
import com.safetynet.alerts.dto.PersonFireStationDto;
import com.safetynet.alerts.dto.PersonFullDto;
import com.safetynet.alerts.dto.PersonInfoDto;
import com.safetynet.alerts.dto.PersonLightChildAlertDto;
import com.safetynet.alerts.dto.PersonLightFireStationDto;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.FireStationDao;
import com.safetynet.alerts.repository.MedicalRecordDao;
import com.safetynet.alerts.repository.PersonDao;
import com.safetynet.alerts.util.Calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class PersonService {

    @Autowired
    PersonDao personDao;

    @Autowired
    FireStationDao fireStationDao;

    @Autowired
    MedicalRecordDao medicalRecordDao;

    Calculator calculator = new Calculator();

    public Set<PersonFullDto> getPersons() {
        Set<Person> persons = personDao.findAll();
        Set<PersonFullDto> personFullDtoSet = new HashSet<>();

        for (Person person : persons) {
            PersonFullDto personFullDto = new PersonFullDto();
            personFullDto.setFirstName(person.getFirstName());
            personFullDto.setLastName(person.getLastName());
            personFullDto.setAddress(person.getAddress());
            personFullDto.setCity(person.getCity());
            personFullDto.setZip(person.getZip());
            personFullDto.setPhone(person.getPhone());
            personFullDto.setEmail(person.getEmail());
            personFullDtoSet.add(personFullDto);
        }
        return personFullDtoSet;

    }

    public PersonFullDto getPerson(final String firstname, final String lastName) {
        Person person = personDao.findById(firstname, lastName);
        PersonFullDto personFullDto = new PersonFullDto();
        if (person != null) {
            personFullDto.setFirstName(person.getFirstName());
            personFullDto.setLastName(person.getLastName());
            personFullDto.setAddress(person.getAddress());
            personFullDto.setCity(person.getCity());
            personFullDto.setZip(person.getZip());
            personFullDto.setPhone(person.getPhone());
            personFullDto.setEmail(person.getEmail());

        } else {
            personFullDto = null;
        }
       return personFullDto;

    }

    public void createPerson (final Person personParam){
        personDao.save(personParam);
    }

    public void updatePerson (final Person personParam) throws Exception {
        personDao.update(personParam);
    }

    public void deletePerson(final String firstname, final String lastName) {
        personDao.deleteById(firstname, lastName);
    }

    public Set<String> getEmailFromCity (final String city) {
        Set<Person> persons = personDao.findAll();
        Set<String> personsEmail = new HashSet<>();

        for (Person person : persons) {
            if (person.getCity().equals(city)) {
                String personEmail = person.getEmail();
                personsEmail.add(personEmail);

            }
        }
        return personsEmail;
    }

    public PersonFireStationDto getPersonFromFireStation (final int station) {
        PersonFireStationDto personFireStationDto = new PersonFireStationDto();
        Set<FireStation> fireStationSet = fireStationDao.findByStation(station);
        Set<Person> personSet = personDao.findAll();
        Set<MedicalRecord> medicalRecordSet = medicalRecordDao.findAll();
        Set<PersonLightFireStationDto> personLightFireStationDtoSet = new HashSet<>();
        int numberAdult = 0;
        int numberChild = 0;
        for (FireStation fireStation : fireStationSet) {
            for (Person p : personSet) {
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
                for (MedicalRecord medicalRecord : medicalRecordSet) {
                    if (medicalRecord.getFirstName().equals(p.getFirstName())&&medicalRecord.getLastName().equals(p.getLastName())){
                       long age = calculator.calculateAge(medicalRecord.getBirthdate());
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

    public PersonChildAlertDto getPersonFromAddress (final String address) {
        Set<Person> persons = personDao.findByAddress(address);
        Set<MedicalRecord> medicalRecordSet = medicalRecordDao.findAll();
        PersonChildAlertDto personChildAlertDto = new PersonChildAlertDto();
        Set<PersonLightChildAlertDto> children = new HashSet<>();
        Set<PersonLightChildAlertDto> familyMembers = new HashSet<>();
        for (Person p : persons) {
            for (MedicalRecord m : medicalRecordSet) {
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

    public PersonInfoDto getPersonInfo (final String firstname, final String lastName) {
        PersonInfoDto personInfoDto = new PersonInfoDto();
        Person person = personDao.findById(firstname, lastName);
        MedicalRecord medicalRecord = medicalRecordDao.findById(firstname, lastName);

        personInfoDto.setFirstName(person.getFirstName());
        personInfoDto.setAddress(person.getLastName());
        personInfoDto.setAddress(person.getAddress());
        personInfoDto.setAge(calculator.calculateAge(medicalRecord.getBirthdate()));
        personInfoDto.setMedications(medicalRecord.getMedications());
        personInfoDto.setAllergies(medicalRecord.getAllergies());

        return personInfoDto;

    }
}
