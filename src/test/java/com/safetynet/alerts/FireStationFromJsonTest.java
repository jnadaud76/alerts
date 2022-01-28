package com.safetynet.alerts;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.repository.FireStationFromJsonDao;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;

public class FireStationFromJsonTest {
    @Test
    public void getFireStationsTest() {
        //Given
        FireStationFromJsonDao fireStationFromJsonDao = new FireStationFromJsonDao();

        //When
        Set<FireStation> fireStations = fireStationFromJsonDao.getFireStations();

        //Then
        assertNotNull(fireStations);

    }
}
