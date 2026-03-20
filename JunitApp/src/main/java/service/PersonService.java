package service;

import exception.InvalidPersonException;
import model.Person;


import java.util.ArrayList;
import java.util.List;

public class PersonService {
    //private PersonService personService = new PersonService();
    List<Person> list ;

    {
        list = registerPersons();
    }
    public int countNumberOfPerson(List<Person> list){
        if(list == null)
            throw new RuntimeException("List cannot be null");

        return list.size();
    }
    /* this method validates Person Object */
    public void validatePerson(Person person){
        if(person == null)
            throw new NullPointerException("Person ref cannot be null");

        if(person.getName().length() < 2)
            throw new InvalidPersonException("Person name should be more than 1 char");

        if(person.getAge() < 18)
            throw new InvalidPersonException("You are under age for this op");
    }

    public List<Person> registerPersons(){
        Person p1 = new Person(1,"harry", 23, "london");
        Person p2 = new Person(2,"ronald", 13, "surrey"); //minor
        Person p3 = new Person(1,"draco", 24, "mumbai");
        return List.of(p1,p2,p3);
    }

    public List<Person> getAdultPersons(){
        return list.stream()
                .filter(person -> person.getAge() > 18)
                .toList();

    }



}
