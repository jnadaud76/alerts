package com.safetynet.alerts.unit;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.repository.FireStationDao;

import org.junit.jupiter.api.Test;

import java.util.Set;

public class FireStationDaoTest {
    @Test
    public void getFireStationsTest() {
        //Given
        FireStationDao fireStationDao = new FireStationDao();

        //When
        Set<FireStation> fireStations = fireStationDao.getFireStations();
        System.out.println(fireStations);

        //Then
        assertNotNull(fireStations);

    }
}
