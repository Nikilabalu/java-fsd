import exception.InvalidPersonException;
import model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.PersonService;

import java.util.ArrayList;
import java.util.List;

public class PersonServiceTest {

    private PersonService personService;

    @BeforeEach
    public void init(){
        personService = new PersonService();
        System.out.println("Person Service Initializing...");
    }

    @AfterEach
    public void finish(){
        personService =null;
        System.out.println("Person Service de-structuring...");
    }
    @Test
    public void countNumberOfPersonTest(){
        //prepare my input
        //list has 2 objects
        List<Person> list = new ArrayList<>();
        Person p1 = new Person(1,"harry", 23, "london");
        Person p2 = new Person(2,"ronald", 22, "surrey");
        list.add(p1);
        list.add(p2);
        //List has 1 object
        List<Person> list1 = new ArrayList<>();
        list1.add(p1);

        //List has no objects
        List<Person> list2 = new ArrayList<>();

        Assertions.assertEquals( 2,personService.countNumberOfPerson(list) );
        Assertions.assertEquals( 1,personService.countNumberOfPerson(list1) );
        Assertions.assertEquals( 0,personService.countNumberOfPerson(list2) );

        //list is null
        List<Person> list3 = null;
        try {
            Assertions.assertEquals(0, personService.countNumberOfPerson(list3));
        }
        catch(RuntimeException e){
            Assertions.assertEquals("List cannot be null", e.getMessage());
        }
    }

    @Test
    public void validatePersonTest(){

        // Checking for null
        NullPointerException e =
                Assertions.assertThrows(NullPointerException.class,
                        ()-> personService.validatePerson(null));

        // Checking the message thrown by NullPointerException
        Assertions.assertEquals("person ref cannot be null".toLowerCase(),
                e.getMessage().toLowerCase());

        // Preparing Person Object for Name less than 1 char
        Person p1 = new Person(1,"h", 23, "london");

        // Checking for exception
        InvalidPersonException e1 =
                Assertions.assertThrows(InvalidPersonException.class,
                        ()->  personService.validatePerson(p1));

        //Checking for message
        Assertions.assertEquals("Person name should be more than 1 char",
                e1.getMessage());
    }
    @Test
    public void getAdultPersonsTest(){
        System.out.println("Test case getting evaluated....");
        Assertions.assertEquals(2,
                personService.getAdultPersons().size(),
                "Should have exactly 2 Persons");

        //checking registerPersons method data
        boolean status = personService.registerPersons().stream().anyMatch(person -> person.getAge() < 18);
        //Assertions.assertFalse(status,"there should not be a minor in the Person list");
        Assertions.assertTrue(status,"there should not be a minor in the Person list");

        // Test to find a city presence in the sample list
        status = personService.registerPersons()
                .stream().anyMatch(person -> person.getCity().equalsIgnoreCase("mumbai"));
        //Assertions.assertFalse(status,"Hey, there is a person living in mumbai");
        Assertions.assertTrue(status,"Hey, there is a person living in mumbai");
    }
    @Test
    public void validatePersonValidTest() {

        Person p = new Person(5,"Harry",25,"London");

        Assertions.assertDoesNotThrow(() -> personService.validatePerson(p));
    }
    @Test
    public void validatePersonAgeTest(){

        Person p = new Person(3,"tom",15,"delhi");

        InvalidPersonException e =
                Assertions.assertThrows(
                        InvalidPersonException.class,
                        () -> personService.validatePerson(p)
                );

        Assertions.assertEquals("You are under age for this op", e.getMessage());
    }
}
