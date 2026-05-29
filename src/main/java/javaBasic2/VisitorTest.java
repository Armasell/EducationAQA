package javaBasic2;

public class VisitorTest {

    public static void main(String[] args) {

        Visitor visitor1 = new Visitor();
        System.out.println("Количество посетителей: " + Visitor.getTotalVisitors());

        Visitor visitor2 = new Visitor();
        System.out.println("Количество посетителей: " + Visitor.getTotalVisitors());

        Visitor visitor3 = new Visitor();
        System.out.println("Количество посетителей: " + Visitor.getTotalVisitors());

        Visitor visitor4 = new Visitor();
        System.out.println("Количество посетителей: " + Visitor.getTotalVisitors());

        Visitor visitor5 = new Visitor();
        System.out.println("Количество посетителей: " + Visitor.getTotalVisitors());

//        getTotalVisitors() выдает статичную переменную totalVisitors, которая привязана не к какому то объекту, а к классу
    }
}
