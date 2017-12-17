package Tests;

import Classes.LinkedList;
import Interfaces.ILinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class LinkedListTests {

    private class Person{

        public int age;

        public Person (int age) {
            this.age = age;
        }

    }

    @Test
    public void personsList() {

        //Initialization
        LinkedList<Person> linkedList = new LinkedList<>();
        ILinkedList<Person> iLinkedList = linkedList;
        Predicate<Person> predicate = (variable) -> variable.age > 0;
        Function<Person, String> function = variable -> String.valueOf(variable.age);
        Consumer<Person> consumer = variable -> variable.age++;



        //Adding
        linkedList.add(new Person(28));
        linkedList.add(new Person(18));
        linkedList.add(new Person(32));
        linkedList.add(new Person(39));
        linkedList.add(new Person(-12));
        linkedList.add(new Person(22));
        linkedList.add(new Person(-38));



        //count (age > 0)
        Assertions.assertEquals(5, iLinkedList.count(predicate));

        //first & last & single
        Assertions.assertEquals(28, iLinkedList.first(predicate).age);
        Assertions.assertEquals(22, iLinkedList.last(predicate).age);
        Assertions.assertNull(iLinkedList.single(predicate));

        //all & any
        Assertions.assertFalse(iLinkedList.all(predicate));
        Assertions.assertTrue(iLinkedList.any(predicate));


        //Filter the List [28, 18, 32, 39, -12, 22, -38]
        LinkedList<Person> filteredList = (LinkedList<Person>) iLinkedList.filter(predicate);

        for (Person person : filteredList) {
            Assertions.assertTrue(person.age > 0);
        }
        //Result: filteredList[28, 18, 32, 39, 22]



        //List function toString [28, 18, 32, 39, -12, 22, -38]
        LinkedList<String> functionList = (LinkedList<String>) iLinkedList.select(function);
        Assertions.assertEquals("28", functionList.first());
        Assertions.assertEquals("39", functionList.find(3));
        Assertions.assertEquals("-38", functionList.last());



        //Increment the age of Persons [28, 18, 32, 39, -12, 22, -38]
        LinkedList<Person> consumeredList = (LinkedList<Person>) iLinkedList.map(consumer);
        Assertions.assertEquals(29, consumeredList.first().age);
        Assertions.assertEquals(40, consumeredList.find(3).age);
        Assertions.assertEquals(-37, consumeredList.last().age);
        
        
        //Remove all Persons
        linkedList.removeAll();
        Assertions.assertTrue(linkedList.isEmpty());

    }

}
