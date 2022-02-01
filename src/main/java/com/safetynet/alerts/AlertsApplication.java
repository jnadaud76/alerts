package com.safetynet.alerts;

import com.safetynet.alerts.repository.PersonDao;
import com.safetynet.alerts.service.IReadData;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

public class AlertsApplication {

	/*@Autowired
	private PersonDao personDao;*/

	/*@Autowired
	private FireStationFromJsonDao fireStationFromJsonDao;

	@Autowired
	private MedicalRecordFromJsonDao medicalRecordFromJsonDao;*/

	public static void main(String[] args) {
		SpringApplication.run(AlertsApplication.class, args);
	}
}

	/*@Override
	public void run(String... args) throws Exception {
		personDao.getPersonsFromJson();
		//Set<FireStation> fireStations = fireStationFromJsonDao.getFireStations();
		//Set<MedicalRecord> medicalRecords = medicalRecordFromJsonDao.getMedicalRecords();
	}
}*/
