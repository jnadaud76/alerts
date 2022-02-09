package com.safetynet.alerts.model;


import java.util.Objects;

public class FireStation {

    private String address;
    private int station;

    public String getAddress() {
        return address;
    }

    public void setAddress(String addressParam) {
        this.address = addressParam;
    }

    public int getStation() {
        return station;
    }

    public void setStation(int stationParam) {
        this.station = stationParam;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FireStation that = (FireStation) o;
        return station == that.station && address.equals(that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, station);
    }
}

