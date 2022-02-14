package com.safetynet.alerts.dto;

public class FireStationFullDto {
    private String address;
    private int station;

    public String getAddress() {
        return address;
    }

    public void setAddress(final String addressParam) {
        this.address = addressParam;
    }

    public int getStation() {
        return station;
    }

    public void setStation(final int stationParam) {
        this.station = stationParam;
    }
}
