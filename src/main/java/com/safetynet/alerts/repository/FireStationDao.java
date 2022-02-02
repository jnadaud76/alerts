package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.Person;


import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.IOException;

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

    public FireStation findByAddress (final String address){
        FireStation fireStationResult = null;
        for (FireStation f : fireStations) {
            if (f.getAddress().equals(address)) {
                fireStationResult = f;
                break;
            }
        }
        return fireStationResult;
    }
    /*public void updateFirestation (final int station){

    }*/

    public void deleteByStation (final int station) throws Exception {
        Set<FireStation> firestationsResult = findByStation(station);

        if (!firestationsResult.isEmpty()) {
            fireStations.removeAll(firestationsResult);
        } else {
            throw new Exception();
        }
    }

    /*public void deleteByAddress (final String address) {
        Person person = findById(firstname, lastName);
        if (person !=null) {
            persons.remove(person);
        } else {
            throw new Exception();
        }
    }*/
    }


