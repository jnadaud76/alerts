package com.safetynet.alerts.dto;

import java.util.Set;

import lombok.Data;

@Data
public class PersonFireStationDto {

    private Set<PersonLightFireStationDto> personLightFireStationDtoSet;
    private int numberAdult;
    private  int numberChild;
}
