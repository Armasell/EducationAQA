package ru.bulgakov.booking;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import ru.bulgakov.booking.config.BookingConfig;
import ru.bulgakov.booking.dto.*;
import ru.bulgakov.booking.steps.BookingSteps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.bulgakov.booking.config.BookingApiConfig.getBookingConfig;
import static ru.bulgakov.booking.steps.BookingSteps.randomBooking;

public class BookingTest extends BaseApiTest {
    private static final Faker faker = new Faker();
    private static final BookingConfig config = getBookingConfig();

    private final BookingApiClient bookingApiClient = new BookingApiClient();
    private final BookingSteps bookingSteps = new BookingSteps();

    @Test
    void authTest() {
        Response response = bookingApiClient.auth(config.username(), config.password());

        assertNotNull(response.as(AuthResponse.class).getToken());
        assertThat(response.as(AuthResponse.class).getToken()).isNotNull();
    }

    @Test
    void getBookingTest() {
        CreatesBookingResponse expectedResponse = bookingSteps.createBooking();

        Response actualResponse = bookingApiClient.getBooking(expectedResponse.getBookingid());
        assertThat(actualResponse.getStatusCode()).isEqualTo(200);

        BookingSteps.bookingsShouldBeEqual(expectedResponse.getBooking(), actualResponse.as(BookingDto.class));
    }

    @Test
    void createBookingTest() {
        BookingDto bookingDto = randomBooking();
        Response response = bookingApiClient.createBooking(bookingDto);

        assertThat(response.getStatusCode()).isEqualTo(200);

        CreatesBookingResponse createsBookingResponse = response.as(CreatesBookingResponse.class);
        assertThat(createsBookingResponse.getBookingid()).isNotNull();
        BookingSteps.bookingsShouldBeEqual(bookingDto, createsBookingResponse.getBooking());
    }

    @Test
    void updateBookingTest() {
        Integer bookingId = bookingSteps.createBooking().getBookingid();

        BookingDto bookingDto = randomBooking();
        Response updateResponse = bookingApiClient.updateBooking(bookingDto, bookingId);
        assertThat(updateResponse.getStatusCode()).isEqualTo(200);

        BookingDto updatedBookingDto = updateResponse.as(BookingDto.class);
        BookingSteps.bookingsShouldBeEqual(bookingDto, updatedBookingDto);
    }

    @Test
    void partialUpdateBookingTest() {
        Integer bookingId = bookingSteps.createBooking().getBookingid();

        BookingDto bookingDto = new BookingDto(faker.football().players(), faker.number().numberBetween(10001, 12000), "2026-02-01");

        Response updateResponse = bookingApiClient.partialUpdateBooking(bookingDto, bookingId);
        assertThat(updateResponse.getStatusCode()).isEqualTo(200);

        BookingDto updatedBookingDto = updateResponse.as(BookingDto.class);
        assertThat(bookingDto.getFirstname()).isEqualTo(updatedBookingDto.getFirstname());
        assertThat(bookingDto.getTotalprice()).isEqualTo(updatedBookingDto.getTotalprice());
        assertThat(bookingDto.getBookingdates().getCheckin()).isEqualTo(updatedBookingDto.getBookingdates().getCheckin());
    }

    @Test
    void deleteBookingTest() {
        Integer bookingId = bookingSteps.createBooking().getBookingid();

        Response deleteResponse = bookingApiClient.deleteBooking(bookingId);
        assertThat(deleteResponse.getStatusCode()).isEqualTo(201);

        Response getResponse = bookingApiClient.getBooking(bookingId);
        assertThat(getResponse.getStatusCode()).isEqualTo(404);
    }

    @Test
    void getBookingsByLastName() {
        String lastName = faker.name().lastName();
        int bookingQuantity = 5;

        List<Integer> bookingIds = new ArrayList<>();
        for (int i = 0; i < bookingQuantity; i++) {
            BookingDto bookingDto = randomBooking();
            bookingDto.setLastname(lastName);
            Integer bookingId = bookingSteps.createBooking(bookingDto).getBookingid();
            bookingIds.add(bookingId);
        }

        Response response = bookingApiClient.getBookings(Map.of("lastname", lastName));
        assertThat(response.getStatusCode()).isEqualTo(200);

        List<BookingId> bookings = response.as(new TypeRef<List<BookingId>>() {});
        assertThat(bookings)
                .hasSize(bookingQuantity)
                .doesNotContainNull()
                .extracting(booking -> booking.bookingid())
                .containsExactlyElementsOf(bookingIds);
    }
}
