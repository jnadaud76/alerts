package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.FireStationDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

import lombok.Data;

@Data
@Service
public class FireStationService {

    @Autowired
    FireStationDao fireStationDao;

    public Set<FireStation> getFireStations () {
    return fireStationDao.findAll();
    }

   public Set<FireStation> getFireStationsByStation(final int station){
        return fireStationDao.findByStation(station);
   }

    public Set<FireStation> getFireStationsByAddress(final String address){
        return fireStationDao.findByAddress(address);
    }

    public void updateFirestation (final FireStation fireStation) throws Exception {
        fireStationDao.updateFirestation(fireStation);

    }

    public void createFireStation (final FireStation fireStation) {
        fireStationDao.save(fireStation);
    }

    public void deleteFireStationByStation (final int station) throws Exception {
        fireStationDao.deleteByStation(station);
    }

    public void deleteFireStationByAddress (final String address) throws Exception {
        fireStationDao.deleteByAddress(address);
    }
}
