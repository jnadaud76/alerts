package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.Person;

import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import lombok.Data;

@Data
@Repository
public class PersonDao {

    private Set<Person> persons = new HashSet<>();

    /*public void getPersonsFromJson() {
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
   }*/
       /* ObjectMapper objectMapper = new ObjectMapper();


        try {
            InputStream input = new FileInputStream("src/main/resources/data.json");

            JsonNode jsonNode = objectMapper.readValue(input, JsonNode.class);
            JsonNode personsNode = jsonNode.get("persons");
            String personsAsString = personsNode.toString();
            persons = objectMapper.readValue(personsAsString, new TypeReference<HashSet<Person>>(){});
            //System.out.println("persons :" + persons);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


       public Set<Person> findAll() {
           return this.persons;
       }

       public Person findById (final String firstname, final String lastName){
           Person person = null;
           for (Person p : persons) {
               if (p.getFirstName().equals(firstname) && p.getLastName().equals(lastName)) {
                   person = p;
                   break;
               }
           }
           return person;
       }

       public void update(final Person personParam) throws Exception {
           deleteById(personParam.getFirstName(), personParam.getLastName());
           Person person = new Person();
           person.setFirstName(personParam.getFirstName());
           person.setLastName(personParam.getLastName());
           person.setEmail(personParam.getEmail());
           person.setPhone(personParam.getPhone());
           person.setZip(personParam.getZip());
           person.setCity(personParam.getCity());
           person.setAddress(personParam.getAddress());
           persons.add(person);

       }

       public void save (final Person personParam){
           Person person = findById(personParam.getFirstName(), personParam.getLastName());
        if (person==null) {
            persons.add(personParam);
        } else {
            throw new IllegalArgumentException();
            }

       }

       public void deleteById (final String firstname, final String lastName) throws Exception {
           Person person = findById(firstname, lastName);
           if (person !=null) {
               persons.remove(person);
           } else {
               throw new Exception();
           }
           //persons.removeIf(person -> Objects.equals(person.getFirstName(), firstname) && Objects.equals(person.getLastName(), lastName));
        }

}


