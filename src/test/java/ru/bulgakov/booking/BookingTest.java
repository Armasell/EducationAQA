package ru.bulgakov.booking;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.bulgakov.booking.dto.AuthRequest;
import ru.bulgakov.booking.dto.AuthResponse;
import ru.bulgakov.booking.dto.CreateBookingDto;
import ru.bulgakov.booking.dto.CreatesBookingResponse;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookingTest {

    private static final String bookingUrl = "https://restful-booker.herokuapp.com";

    @BeforeAll
    static void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.filters(new AllureRestAssured());
    }

    @Test
    void authTest() {
        String user = "admin";
        String password = "password123";

        AuthResponse response = given()
                .contentType(ContentType.JSON)
                .body(new AuthRequest(user, password))
                .when()
                .post(bookingUrl + "/auth")
                .then()
                .statusCode(200)
                .extract().as(AuthResponse.class);

        assertNotNull(response.getToken());
        assertThat(response.getToken()).isNotNull();
    }

    @Test
    void createBookingTest() {
        CreatesBookingResponse response = given()
                .contentType(ContentType.JSON)
                .body(buildBookingRequest())
                .when()
                .post(bookingUrl + "/booking")
                .then()
                .statusCode(200)
                .extract().as(CreatesBookingResponse.class);

        assertThat(response.getBookingid()).isNotNull();
        assertThat(response.getBooking().getTotalprice()).isEqualTo(111);
    }

    private static CreateBookingDto buildBookingRequest() {
        return CreateBookingDto.builder()
                .firstname("Jim")
                .lastname("Brown")
                .totalprice(111)
                .depositpaid(true)
                .bookingdates(CreateBookingDto.BookingDates.builder()
                        .checkin("2018-01-01")
                        .checkout("2019-01-01")
                        .build())
                .additionalneeds("Breakfast")
                .build();
    }
}
