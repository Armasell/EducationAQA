package ru.bulgakov.booking;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.bulgakov.booking.dto.AuthRequest;
import ru.bulgakov.booking.dto.AuthResponse;
import ru.bulgakov.booking.dto.BookingDto;
import ru.bulgakov.booking.dto.CreatesBookingResponse;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class BookingNegativeTest {

    private static final String bookingUrl = "https://restful-booker.herokuapp.com";

    @BeforeAll
    static void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.filters(new AllureRestAssured());
    }

    @ParameterizedTest
    @MethodSource("invalidCredentials")
    void negativeAuth(String user, String password) {
        AuthResponse response = given()
                .contentType(ContentType.JSON)
                .body(new AuthRequest(user, password))
                .when()
                .post(bookingUrl + "/auth")
                .then()
                .statusCode(200)
                .extract().as(AuthResponse.class);

        assertThat(response.getToken()).isNull();
        assertThat(response.getReason()).isNotNull().isEqualTo("Bad credentials");
    }

    @Test
    void emptyBodyAuthTest() {
        AuthResponse response = given()
                .contentType(ContentType.JSON)
                .body("{}")
                .when()
                .post(bookingUrl + "/auth")
                .then()
                .statusCode(200)
                .extract().as(AuthResponse.class);

        assertThat(response.getToken()).isNull();
    }

    @Test
    void noBodyAuthTest() {
        AuthResponse response = given()
                .contentType(ContentType.JSON)
                .when()
                .post(bookingUrl + "/auth")
                .then()
                .statusCode(200)
                .extract().as(AuthResponse.class);

        assertThat(response.getToken()).isNull();
    }

    @Test
    void noFirstnameBookingTest() {
        String response = given()
                .contentType(ContentType.JSON)
                .body(buildBookingRequest(
                        null,
                        "Brown",
                        111,
                        true,
                        "2018-01-01",
                        "2019-01-01",
                        "Breakfast"))
                .when()
                .post(bookingUrl + "/booking")
                .then()
                .statusCode(500)
                .extract().asString();

        assertThat(response).contains("Internal Server Error");
    }

    @Test
    void noLastnameBookingTest() {
        String response = given()
                .contentType(ContentType.JSON)
                .body(buildBookingRequest(
                        "Jim",
                        null,
                        111,
                        true,
                        "2018-01-01",
                        "2019-01-01",
                        "Breakfast"))
                .when()
                .post(bookingUrl + "/booking")
                .then()
                .statusCode(500)
                .extract().asString();

        assertThat(response).contains("Internal Server Error");
    }

    @Test
    void minusPriceBookingTest() {
        CreatesBookingResponse response = given()
                .contentType(ContentType.JSON)
                .body(buildBookingRequest(
                        "Jim",
                        "Brown",
                        -500,
                        true,
                        "2018-01-01",
                        "2019-01-01",
                        "Breakfast"))
                .when()
                .post(bookingUrl + "/booking")
                .then()
                .statusCode(200)
                .extract().as(CreatesBookingResponse.class);

        assertThat(response.getBooking().getTotalprice()).isNotNull().isEqualTo(-500);
    }

    @Test
    void invalidDateBookingTest() {
        CreatesBookingResponse response = given()
                .contentType(ContentType.JSON)
                .body(buildBookingRequest(
                        "Jim",
                        "Brown",
                        111,
                        true,
                        "privet",
                        "2019-01-01",
                        "Breakfast"))
                .when()
                .post(bookingUrl + "/booking")
                .then()
                .statusCode(200)
                .extract().as(CreatesBookingResponse.class);

        assertThat(response.getBooking().getBookingdates().getCheckin()).isNotNull();
    }

    @Test
    void checkoutEarlierCheckinBookingTest() {
        CreatesBookingResponse response = given()
                .contentType(ContentType.JSON)
                .body(buildBookingRequest(
                        "Jim",
                        "Brown",
                        111,
                        true,
                        "2018-01-01",
                        "2017-01-01",
                        "Breakfast"))
                .when()
                .post(bookingUrl + "/booking")
                .then()
                .statusCode(200)
                .extract().as(CreatesBookingResponse.class);

        assertThat(response.getBooking().getBookingdates().getCheckin()).isNotNull();
        assertThat(response.getBooking().getBookingdates().getCheckout()).isNotNull();
    }

    @Test
    void emptyBodyBookingTest() {
        String response = given()
                .contentType(ContentType.JSON)
                .body("{}")
                .when()
                .post(bookingUrl + "/booking")
                .then()
                .statusCode(500)
                .extract().asString();

        assertThat(response).contains("Internal Server Error");
    }


    static Stream<Arguments> invalidCredentials() {
        return Stream.of(
                Arguments.of("admin", "wrongPassword"),
                Arguments.of("wrongAdmin", "password123"),
                Arguments.of("admin", ""),
                Arguments.of("", "password123")
        );
    }


    private static BookingDto buildBookingRequest(String firstname,
                                                  String lastname,
                                                  Integer totalprice,
                                                  Boolean depositpaid,
                                                  String checkin,
                                                  String checkout,
                                                  String additionalneeds) {
        return BookingDto.builder()
                .firstname(firstname)
                .lastname(lastname)
                .totalprice(totalprice)
                .depositpaid(depositpaid)
                .bookingdates(BookingDto.BookingDates.builder()
                        .checkin(checkin)
                        .checkout(checkout)
                        .build())
                .additionalneeds(additionalneeds)
                .build();
    }
}
