package ru.bulgakov.webshop.tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

public class ApiTest {

    @Test
    @Tags({@Tag("API"), @Tag("positive")})
    void apiTest1() {
        System.out.println("API positive test");
    }

    @Test
    @Tags({@Tag("API"), @Tag("negative")})
    void apiTest2() {
        System.out.println("API negative test");
    }
}
