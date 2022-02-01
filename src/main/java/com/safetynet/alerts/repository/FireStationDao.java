package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.FireStation;


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
}
