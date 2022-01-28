package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.Person;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Repository
public class PersonFromJsonDao implements IPersonDao {

   @Override
    public Set<Person> getPersons() {
       Set<Person> persons = new HashSet<>();
       JSONParser jsonparser = new JSONParser();

       try {
           JSONObject jsonObject = (JSONObject) jsonparser
                   .parse(new FileReader("src/main/resources/data.json"));
           JSONArray jsonArray = (JSONArray) jsonObject.get("persons");
           for (Object c : jsonArray) {
               JSONObject ObjectPerson = (JSONObject) c;
               String firstName = (String) ObjectPerson.get("firstName");
               String lastName = (String) ObjectPerson.get("lastName");
               String address = (String) ObjectPerson.get("address");
               String city = (String) ObjectPerson.get("city");
               String zip = (String) ObjectPerson.get("zip");
               String phone = (String) ObjectPerson.get("phone");
               String email = (String) ObjectPerson.get("email");
               Person person = new Person();
               person.setFirstName(firstName);
               person.setLastName(lastName);
               person.setAddress(address);
               person.setCity(city);
               person.setZip(Integer.parseInt(zip));
               person.setPhone(phone);
               person.setEmail(email);
               persons.add(person);
           }

       } catch (IOException | ParseException e) {
           e.printStackTrace();
       }

       return persons;
   }
}

