package com.safetynet.alerts.dto;

import java.util.Set;


public class PersonFireStationDto {
    /**
     * A set of person.
     *
     * @see PersonLightFireStationDto
     */
    private Set<PersonLightFireStationDto> personLightFireStationDtoSet;
    /**
     * Number of adult present in personLightFireStationDtoSet.
     */
    private int numberAdult;
    /**
     * Number of child present in personLightFireStationDtoSet.
     */
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
