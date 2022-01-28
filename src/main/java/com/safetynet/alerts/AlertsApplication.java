package com.safetynet.alerts;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.FireStationFromJsonDao;
import com.safetynet.alerts.repository.MedicalRecordFromJsonDao;
import com.safetynet.alerts.repository.PersonFromJsonDao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;

@SpringBootApplication
public class AlertsApplication implements CommandLineRunner {

	@Autowired
	private PersonFromJsonDao personFromJsonDao;

	@Autowired
	private FireStationFromJsonDao fireStationFromJsonDao;

	@Autowired
	private MedicalRecordFromJsonDao medicalRecordFromJsonDao;

	public static void main(String[] args) {
		SpringApplication.run(AlertsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Set<Person> persons = personFromJsonDao.getPersons();
		Set<FireStation> fireStations = fireStationFromJsonDao.getFireStations();
		Set<MedicalRecord> medicalRecords = medicalRecordFromJsonDao.getMedicalRecords();

		System.out.println(persons);
		System.out.println(fireStations);
		System.out.println(medicalRecords);
	}
}
