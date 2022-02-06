package com.safetynet.alerts.dto;

import java.util.Set;

import lombok.Data;

@Data
public class PersonChildAlertDto {

   Set<PersonLightChildAlertDto> children;
   Set<PersonLightChildAlertDto> familyMembers;

}
