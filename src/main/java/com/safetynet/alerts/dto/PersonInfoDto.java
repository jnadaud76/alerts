package com.safetynet.alerts.dto;

import java.util.Set;

import lombok.Data;

@Data
public class PersonInfoDto {
    private String firstName;
    private String address;
    private long age;
    private Set<String> medications;
    private Set<String> allergies;

}
