package com.safetynet.alerts.service.impl;

import com.safetynet.alerts.dto.FireStationFullDto;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.repository.IFireStationDao;
import com.safetynet.alerts.service.IFireStationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

import java.util.Set;


@Service
public class FireStationService implements IFireStationService {
    /**
     * @see IFireStationDao
     */
    @Autowired
    private IFireStationDao fireStationDao;

    /**
     * Transform fireStation object in fireStationFullDto.
     *
     * @param fireStation a fireStation to transform.
     * @return a fireStation transformed.
     */
    private FireStationFullDto fireStationToFireStationFullDto(final FireStation fireStation) {
        FireStationFullDto firestationFullDto = new FireStationFullDto();
        firestationFullDto.setStation(fireStation.getStation());
        firestationFullDto.setAddress(fireStation.getAddress());
        return firestationFullDto;
    }

    /**
     * Get all fireStation.
     *
     * @return a set of fireStation.
     */
    public Set<FireStationFullDto> getFireStations() {
        Set<FireStation> fireStations = fireStationDao.findAll();
        Set<FireStationFullDto> fireStationFullDtoSet = new HashSet<>();

        for (FireStation f : fireStations) {
            fireStationFullDtoSet.add(fireStationToFireStationFullDto(f));
        }
        return fireStationFullDtoSet;
    }

    /**
     * Get a set of fireStation by station number.
     *
     * @param station a station number.
     * @return a set of fireStation sought if present in the set or empty set
     * otherwise.
     */
    public Set<FireStationFullDto> getFireStationsByStation(final int station) {
        Set<FireStation> fireStations = fireStationDao.findByStation(station);
        Set<FireStationFullDto> fireStationFullDtoSet = new HashSet<>();

        for (FireStation f : fireStations) {
            fireStationFullDtoSet.add(fireStationToFireStationFullDto(f));
        }
        return fireStationFullDtoSet;
    }

    /**
     * Get a set of fireStation by address.
     *
     * @param address a fireStation address.
     * @return a set of fireStation sought if present in the set or empty set
     * otherwise.
     */
    public Set<FireStationFullDto> getFireStationsByAddress(final String address) {
        Set<FireStation> fireStations = fireStationDao.findByAddress(address);
        Set<FireStationFullDto> fireStationFullDtoSet = new HashSet<>();

        for (FireStation f : fireStations) {
            fireStationFullDtoSet.add(fireStationToFireStationFullDto(f));
        }
        return fireStationFullDtoSet;
    }

    /**
     * Create a fireStation.
     *
     * @param fireStation a fireStation to add to the set.
     */
    public void createFireStation(final FireStation fireStation) {
        fireStationDao.save(fireStation);
    }

    /**
     * Update a fireStation.
     *
     * @param fireStation a fireStation to modify.
     */
    public void updateFirestation(final FireStation fireStation) {
        fireStationDao.update(fireStation);
    }

    /**
     * Delete a fireStation by station number.
     *
     * @param station station number to delete.
     */
    public void deleteFireStationByStation(final int station) {
        fireStationDao.deleteByStation(station);
    }

    /**
     * Delete a fireStation by address.
     *
     * @param address fireStation address to delete.
     */
    public void deleteFireStationByAddress(final String address) {
        fireStationDao.deleteByAddress(address);
    }
}
