package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.dto.FireStationFullDto;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.repository.FireStationDao;
import com.safetynet.alerts.service.IFireStationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

import java.util.Set;


@Service
public class FireStationService implements IFireStationService {

    @Autowired
    private FireStationDao fireStationDao;

    private FireStationFullDto fireStationToFireStationFullDto(final FireStation fireStation) {
        FireStationFullDto firestationFullDto = new FireStationFullDto();
        firestationFullDto.setStation(fireStation.getStation());
        firestationFullDto.setAddress(fireStation.getAddress());
        return firestationFullDto;
    }

    public Set<FireStationFullDto> getFireStations() {
        Set<FireStation> fireStations = fireStationDao.findAll();
        Set<FireStationFullDto> fireStationFullDtoSet = new HashSet<>();

        for (FireStation f : fireStations) {
            fireStationFullDtoSet.add(fireStationToFireStationFullDto(f));
        }
        return fireStationFullDtoSet;
    }

    public Set<FireStationFullDto> getFireStationsByStation(final int station) {
        Set<FireStation> fireStations = fireStationDao.findByStation(station);
        Set<FireStationFullDto> fireStationFullDtoSet = new HashSet<>();

        for (FireStation f : fireStations) {
            fireStationFullDtoSet.add(fireStationToFireStationFullDto(f));
        }
        return fireStationFullDtoSet;
    }

    public Set<FireStationFullDto> getFireStationsByAddress(final String address) {
        Set<FireStation> fireStations = fireStationDao.findByAddress(address);
        Set<FireStationFullDto> fireStationFullDtoSet = new HashSet<>();

        for (FireStation f : fireStations) {
            fireStationFullDtoSet.add(fireStationToFireStationFullDto(f));
        }
        return fireStationFullDtoSet;
    }

    public void updateFirestation(final FireStation fireStation) {
        fireStationDao.update(fireStation);
    }

    public void createFireStation(final FireStation fireStation) {
        fireStationDao.save(fireStation);
    }

    public void deleteFireStationByStation(final int station) {
        fireStationDao.deleteByStation(station);
    }

    public void deleteFireStationByAddress(final String address) {
        fireStationDao.deleteByAddress(address);
    }
}
