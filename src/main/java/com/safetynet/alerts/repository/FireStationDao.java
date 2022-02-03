package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.FireStation;


import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
@Repository
public class FireStationDao {

    Set<FireStation> fireStations = new HashSet<>();
    /*public Set<FireStation> getFireStations() {

        JSONParser jsonparser = new JSONParser();

        try {
            JSONObject jsonObject = (JSONObject) jsonparser
                    .parse(new FileReader("src/main/resources/data.json"));
            JSONArray jsonArray = (JSONArray) jsonObject.get("firestations");
            for (Object c : jsonArray) {
                JSONObject ObjectFireStation = (JSONObject) c;
                String address = (String) ObjectFireStation.get("address");
                String station = (String) ObjectFireStation.get("station");
                FireStation fireStation = new FireStation();
                fireStation.setAddress(address);
                fireStation.setStation(Integer.parseInt(station));
                fireStations.add(fireStation);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return fireStations;
    }*/
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

    public Set<FireStation> findByAddress (final String address){
        Set<FireStation> fireStationsResult = new HashSet<>();
        for (FireStation f : fireStations) {
            if (f.getAddress().equals(address)) {
                fireStationsResult.add(f);
            }
        }
        return fireStationsResult;
    }
    public void update(final FireStation fireStationParam) {
        deleteByAddress(fireStationParam.getAddress());
        FireStation firestation = new FireStation();
        firestation.setStation(fireStationParam.getStation());
        firestation.setAddress(fireStationParam.getAddress());
        fireStations.add(firestation);

    }

    public void save (final FireStation fireStationParam) {
        FireStation fireStation = null;
        for (FireStation f : fireStations) {
            if (f.getAddress().equals(fireStationParam.getAddress())
                    && (f.getStation() == fireStationParam.getStation())) {
                fireStation=f;
                break;
            }

        }
        if (fireStation==null) {
            fireStations.add(fireStationParam);
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


