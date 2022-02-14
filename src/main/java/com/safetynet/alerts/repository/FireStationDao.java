package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.FireStation;


import org.springframework.stereotype.Repository;

import java.util.HashSet;

import java.util.Set;
import java.util.stream.Collectors;


@Repository
public class FireStationDao {

    Set<FireStation> fireStations = new HashSet<>();

    public Set<FireStation> getFireStations() {
        return fireStations;
    }

    public void setFireStations(final Set<FireStation> fireStationsParam) {
        this.fireStations = fireStationsParam;
    }

    public Set<FireStation> findAll() {
        return this.fireStations;
    }

    public Set<FireStation> findByStation(final int station) {
          return fireStations.stream().filter(fireStation -> fireStation
                .getStation() == station).collect(Collectors.toSet());
    }

    public Set<FireStation> findByAddress(final String address) {
        return fireStations.stream().filter(fireStation -> fireStation
                .getAddress().equals(address)).collect(Collectors.toSet());

    }

    public void update(final FireStation fireStationParam) {
        deleteByAddress(fireStationParam.getAddress());
        FireStation firestation = new FireStation();
        firestation.setStation(fireStationParam.getStation());
        firestation.setAddress(fireStationParam.getAddress());
        fireStations.add(firestation);

    }

    public void save(final FireStation fireStation) {
        FireStation fireStationResult = null;
        for (FireStation f : fireStations) {
            if (f.getAddress().equals(fireStation.getAddress())
                    && (f.getStation() == fireStation.getStation())) {
                fireStationResult = f;
                break;
            }

        }
        if (fireStationResult == null) {
            fireStations.add(fireStation);
        } else {
            throw new IllegalArgumentException();
        }

    }

    public void deleteByStation(final int station) {
        Set<FireStation> firestationsResult = findByStation(station);

        if (!firestationsResult.isEmpty()) {
            fireStations.removeAll(firestationsResult);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void deleteByAddress(final String address) {
        Set<FireStation> firestationsResult = findByAddress(address);
        if (!firestationsResult.isEmpty()) {
            fireStations.removeAll(firestationsResult);
        } else {
            throw new IllegalArgumentException();
        }
    }
}


