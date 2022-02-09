package com.safetynet.alerts.dto;

import java.util.Set;


public class PersonChildAlertDto {

    Set<PersonLightChildAlertDto> children;
    Set<PersonLightChildAlertDto> familyMembers;


    public Set<PersonLightChildAlertDto> getChildren() {
        return children;
    }

    public void setChildren(Set<PersonLightChildAlertDto> childrenParam) {
        this.children = childrenParam;
    }

    public Set<PersonLightChildAlertDto> getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(Set<PersonLightChildAlertDto> familyMembersParam) {
        this.familyMembers = familyMembersParam;
    }
}
