package javaBasic;

public class Task1 {

    public static void main(String[] args) {

        Person person1 = new Person("Николай", "Басков", 25);
        Person person2 = new Person("Александр", "Пушкин", 32);
        Person person3 = new Person("Владимир", "Путин", 66);

        person1.introduce();
        person2.introduce();
        person3.introduce();
    }
}
