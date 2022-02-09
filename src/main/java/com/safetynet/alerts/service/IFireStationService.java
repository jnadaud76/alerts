package com.safetynet.alerts.service;

import com.safetynet.alerts.dto.FireStationFullDto;
import com.safetynet.alerts.model.FireStation;

import java.util.Set;

public interface IFireStationService {

    Set<FireStationFullDto> getFireStations();

    Set<FireStationFullDto> getFireStationsByStation(int station);

    Set<FireStationFullDto> getFireStationsByAddress(String address);

    void updateFirestation(FireStation fireStation);

    void createFireStation(FireStation fireStation);

    void deleteFireStationByStation(int station);

    void deleteFireStationByAddress(String address);

}
