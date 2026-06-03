package ru.bulgakov.booking;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.bulgakov.booking.config.BookingConfig;
import ru.bulgakov.booking.dto.AuthResponse;
import ru.bulgakov.booking.dto.BookingDto;
import ru.bulgakov.booking.dto.CreatesBookingResponse;
import ru.bulgakov.booking.steps.BookingSteps;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.bulgakov.booking.config.BookingApiConfig.getBookingConfig;
import static ru.bulgakov.booking.steps.BookingSteps.buildBookingRequest;

public class BookingTest extends BaseApiTest {
    private static final Faker faker = new Faker();
    private static final BookingConfig config = getBookingConfig();
    private final BookingApiClient bookingApiClient = new BookingApiClient();

    @BeforeAll
    static void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.filters(new AllureRestAssured());
    }

    @Test
    void authTest() {
        Response response = bookingApiClient.auth(config.username(), config.password());

        assertNotNull(response.as(AuthResponse.class).getToken());
        assertThat(response.as(AuthResponse.class).getToken()).isNotNull();
    }

    @Test
    void createBookingTest() {
        BookingDto bookingDto = buildBookingRequest();
        Response response = bookingApiClient.createBooking(bookingDto);

        assertThat(response.getStatusCode()).isEqualTo(200);

        CreatesBookingResponse createsBookingResponse = response.as(CreatesBookingResponse.class);
        assertThat(createsBookingResponse.getBookingid()).isNotNull();
        BookingSteps.bookingsShouldBeEqual(bookingDto, createsBookingResponse.getBooking());
    }

    @Test
    void updateBookingTest() {
        Response createResponse = bookingApiClient.createBooking(buildBookingRequest());
        assertThat(createResponse.getStatusCode()).isEqualTo(200);

        BookingDto bookingDto = buildBookingRequest();
        Response updateResponse = bookingApiClient.updateBooking(bookingDto, createResponse.as(CreatesBookingResponse.class).getBookingid());
        assertThat(updateResponse.getStatusCode()).isEqualTo(200);

        BookingDto updatedBookingDto = updateResponse.as(BookingDto.class);
        BookingSteps.bookingsShouldBeEqual(bookingDto, updatedBookingDto);
    }

    @Test
    void partialUpdateBookingTest() {
        Response createResponse = bookingApiClient.createBooking(buildBookingRequest());
        assertThat(createResponse.getStatusCode()).isEqualTo(200);

        BookingDto bookingDto = new BookingDto(faker.football().players(), faker.number().numberBetween(10001, 12000), "2026-02-01");

        Response updateResponse = bookingApiClient.partialUpdateBooking(bookingDto, createResponse.as(CreatesBookingResponse.class).getBookingid());
        assertThat(updateResponse.getStatusCode()).isEqualTo(200);

        BookingDto updatedBookingDto = updateResponse.as(BookingDto.class);
        assertThat(bookingDto.getFirstname()).isEqualTo(updatedBookingDto.getFirstname());
        assertThat(bookingDto.getTotalprice()).isEqualTo(updatedBookingDto.getTotalprice());
        assertThat(bookingDto.getBookingdates().getCheckin()).isEqualTo(updatedBookingDto.getBookingdates().getCheckin());
    }

    @Test
    void deleteBookingTest() {
        Integer bookingId = bookingApiClient.createBooking(buildBookingRequest()).as(CreatesBookingResponse.class).getBookingid();

        Response deleteResponse = bookingApiClient.deleteBooking(bookingId);
        assertThat(deleteResponse.getStatusCode()).isEqualTo(201);

        Response getResponse = bookingApiClient.getBooking(bookingId);
        assertThat(getResponse.getStatusCode()).isEqualTo(404);
    }
}
