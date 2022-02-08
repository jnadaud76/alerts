package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.FireStation;


import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;


@Repository
public class FireStationDao {

    Set<FireStation> fireStations = new HashSet<>();

    public Set<FireStation> getFireStations() {
        return fireStations;
    }

    public void setFireStations(Set<FireStation> fireStationsParam) {
        this.fireStations = fireStationsParam;
    }

    public Set<FireStation> findAll() {
        return this.fireStations;
    }

    public Set<FireStation> findByStation (final int station){
       Set<FireStation> fireStationsResult = new HashSet<>();
        for (FireStation f : fireStations) {
            if (f.getStation()==station) {
                fireStationsResult.add(f);
            }
        }
        return fireStationsResult;
    }

  /*  public Set<FireStation> findByStationSet (final Set<Integer> stations) {
        Set<FireStation> fireStationsResult = new HashSet<>();
        Iterator<Integer> i = stations.iterator();

            while (i.hasNext()) {
                int stationNumber = i.next();
                for (FireStation f : fireStations) {
                if (f.getStation() == stationNumber) {
                    fireStationsResult.add(f);
                }
            }

        }
        return fireStationsResult;
    }*/

    public Set<FireStation> findByAddress (final String address){
        //Set<FireStation> fireStationsResult = new HashSet<>();
        return fireStations.stream().filter(fireStation -> fireStation.getAddress().equals(address)).collect(Collectors.toSet());

        /*for (FireStation f : fireStations) {
            if (f.getAddress().equals(address)) {
                fireStationsResult.add(f);
            }
        }*/
        //return fireStationsResult;
    }
    public void update(final FireStation fireStationParam) {
        deleteByAddress(fireStationParam.getAddress());
        FireStation firestation = new FireStation();
        firestation.setStation(fireStationParam.getStation());
        firestation.setAddress(fireStationParam.getAddress());
        fireStations.add(firestation);

    }

    public void save (final FireStation fireStation) {
        FireStation fireStationResult = null;
        for (FireStation f : fireStations) {
            if (f.getAddress().equals(fireStation.getAddress())
                    && (f.getStation() == fireStation.getStation())) {
                fireStationResult=f;
                break;
            }

        }
        if (fireStationResult==null) {
            fireStations.add(fireStation);
        } else {
            throw new IllegalArgumentException();
        }

    }

    public void deleteByStation (final int station) {
        Set<FireStation> firestationsResult = findByStation(station);

        if (!firestationsResult.isEmpty()) {
            fireStations.removeAll(firestationsResult);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void deleteByAddress (final String address) {
        Set<FireStation> firestationsResult = findByAddress(address);
        if (!firestationsResult.isEmpty()) {
            fireStations.removeAll(firestationsResult);
        } else {
            throw new IllegalArgumentException();
        }
    }
    }


