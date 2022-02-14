package com.safetynet.alerts.dto;

import java.util.Set;


public class PersonFireStationDto {

    private Set<PersonLightFireStationDto> personLightFireStationDtoSet;
    private int numberAdult;
    private int numberChild;

    public Set<PersonLightFireStationDto> getPersonLightFireStationDtoSet() {
        return personLightFireStationDtoSet;
    }

    public void setPersonLightFireStationDtoSet(final Set<PersonLightFireStationDto> personLightFireStationDtoSetParam) {
        this.personLightFireStationDtoSet = personLightFireStationDtoSetParam;
    }

    public int getNumberAdult() {
        return numberAdult;
    }

    public void setNumberAdult(final int numberAdultParam) {
        this.numberAdult = numberAdultParam;
    }

    public int getNumberChild() {
        return numberChild;
    }

    public void setNumberChild(final int numberChildParam) {
        this.numberChild = numberChildParam;
    }
}
