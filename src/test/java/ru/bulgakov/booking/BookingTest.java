package ru.bulgakov.booking;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.bulgakov.booking.dto.AuthResponse;
import ru.bulgakov.booking.dto.BookingDto;
import ru.bulgakov.booking.dto.CreatesBookingResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookingTest {
    private static final String BOOKING_URL = "https://restful-booker.herokuapp.com";
    private static final Faker faker = new Faker();
    private static final String USER = "admin";
    private static final String PASSWORD = "password123";

    private final BookingApiClient bookingApiClient = new BookingApiClient();

    @BeforeAll
    static void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.filters(new AllureRestAssured());
    }

    @Test
    void authTest() {
        Response response = bookingApiClient.auth(USER, PASSWORD);

        assertNotNull(response.as(AuthResponse.class).getToken());
        assertThat(response.as(AuthResponse.class).getToken()).isNotNull();
    }

    @Test
    void createBookingTest() {
        Response response = bookingApiClient.createBooking(buildBookingRequest());

        assertThat(response.getStatusCode()).isEqualTo(200);

        CreatesBookingResponse createsBookingResponse = response.as(CreatesBookingResponse.class);
        assertThat(createsBookingResponse.getBooking().getTotalprice()).isEqualTo(111);
    }

    @Test
    void updateBookingTest() {
        Response createResponse = bookingApiClient.createBooking(buildBookingRequest());
        assertThat(createResponse.getStatusCode()).isEqualTo(200);

        BookingDto bookingDto = buildBookingRequest();
        Response updateResponse = bookingApiClient.updateBooking(bookingDto, createResponse.as(CreatesBookingResponse.class).getBookingid());
        assertThat(updateResponse.getStatusCode()).isEqualTo(200);

        BookingDto updatedBookingDto = updateResponse.as(BookingDto.class);
        assertThat(updatedBookingDto.equals(bookingDto)).isTrue();
    }

    private static BookingDto buildBookingRequest() {
        return BookingDto.builder()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .totalprice(faker.number().numberBetween(1000, 10000))
                .depositpaid(faker.bool().bool())
                .bookingdates(BookingDto.BookingDates.builder()
                        .checkin("2018-01-01")
                        .checkout("2019-01-01")
                        .build())
                .additionalneeds(faker.videoGame().title())
                .build();
    }
}
