package com.safetynet.alerts.repository.impl;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.repository.IFireStationDao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

import java.util.Set;
import java.util.stream.Collectors;


@Repository
public class FireStationDao implements IFireStationDao {
    /**
     * @see Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(FireStationDao.class);
    /**
     * Set of fireStation extract from data source.
     */
    private Set<FireStation> fireStations = new HashSet<>();

    public Set<FireStation> getFireStations() {
        return fireStations;
    }

    public void setFireStations(final Set<FireStation> fireStationsParam) {
        this.fireStations = fireStationsParam;
    }

    /**
     * CRUD method which find all fireStation.
     *
     * @return all fireStation.
     */
    public Set<FireStation> findAll() {
        return this.fireStations;
    }

    /**
     * CRUD method which find a set of fireStation from a station number.
     *
     * @param stationParam a station number.
     * @return a set of fireStation found. If no fireStation are found,
     * the set is empty.
     */
    public Set<FireStation> findByStation(final int stationParam) {
        return fireStations.stream().filter(fireStation -> fireStation
                .getStation() == stationParam).collect(Collectors.toSet());
    }

    /**
     * CRUD method which find a set of fireStation from an address.
     *
     * @param addressParam a fireStation address.
     * @return a set of fireStation found. If no fireStation are found,
     * the set is empty.
     */
    public Set<FireStation> findByAddress(final String addressParam) {
        return fireStations.stream().filter(fireStation -> fireStation
                .getAddress().equals(addressParam)).collect(Collectors.toSet());

    }

    /**
     * CRUD method which update a fireStation.
     * Delete by address the fireStation first before create it again with
     * parameters provided.
     * Address can't be modified.
     *
     * @param fireStationParam a fireStation to modify.
     */
    public void update(final FireStation fireStationParam) {
        deleteByAddress(fireStationParam.getAddress());
        FireStation firestation = new FireStation();
        firestation.setStation(fireStationParam.getStation());
        firestation.setAddress(fireStationParam.getAddress());
        fireStations.add(firestation);

    }

    /**
     * CRUD method which save a fireStation.
     * Try to find fireStation first. If already present in set,
     * throw new IllegalArgumentException().
     *
     * @param fireStationParam a fireStation to add to the set.
     */
    public void save(final FireStation fireStationParam) {
        FireStation fireStationResult = null;
        for (FireStation f : fireStations) {
            if (f.getAddress().equals(fireStationParam.getAddress())
                    && (f.getStation() == fireStationParam.getStation())) {
                fireStationResult = f;
                break;
            }

        }
        if (fireStationResult == null) {
            fireStations.add(fireStationParam);
        } else {
            LOGGER.error("Firestation already exist in Set", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }

    }

    /**
     * CRUD method which delete a fireStation from a station number.
     * Try to find by station, fireStation first. If not present in set,
     * throw new IllegalArgumentException().
     *
     * @param stationParam station number to delete.
     */
    public void deleteByStation(final int stationParam) {
        Set<FireStation> firestationsResult = findByStation(stationParam);

        if (!firestationsResult.isEmpty()) {
            fireStations.removeAll(firestationsResult);
        } else {
            LOGGER.error("Firestation doesn't exist in Set", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }
    }

    /**
     * CRUD method which delete a fireStation from address.
     * Try to find by address fireStation first. If not present in set,
     * throw new IllegalArgumentException().
     *
     * @param addressParam fireStation address to delete.
     */
    public void deleteByAddress(final String addressParam) {
        Set<FireStation> firestationsResult = findByAddress(addressParam);
        if (!firestationsResult.isEmpty()) {
            fireStations.removeAll(firestationsResult);
        } else {
            LOGGER.error("Firestation doesn't exist in Set", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }
    }
}


