package javaBasic;

public class Person {
    private String firstName;
    private String lastName;
    private int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public void introduce() {
        System.out.println("Привет, меня зовут " + firstName + " " + lastName + ". Мне " + age + " лет");
    }

}
