package ru.bulgakov.booking.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import net.datafaker.Faker;
import ru.bulgakov.booking.BookingApiClient;
import ru.bulgakov.booking.dto.BookingDto;
import ru.bulgakov.booking.dto.CreatesBookingResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class BookingSteps {

    private static final Faker faker = new Faker();
    private final BookingApiClient bookingApiClient = new BookingApiClient();

    public CreatesBookingResponse createBooking() {
        return createBooking(randomBooking());
    }

    public CreatesBookingResponse createBooking(BookingDto booking) {
        Response createResponse = bookingApiClient.createBooking(booking);

        return createResponse.as(CreatesBookingResponse.class);
    }

    @Step("Проверить соответствие всех полей в ответе")
    public static void bookingsShouldBeEqual(BookingDto expected, BookingDto actual) {
        assertAll(
                () -> assertThat(actual.getFirstname())
                        .as("firstName отличается от ожидаемого")
                        .isEqualTo(expected.getFirstname()),
                () -> assertThat(actual.getLastname())
                        .as("lastName отличается от ожидаемого")
                        .isEqualTo(expected.getLastname()),
                () -> assertThat(actual.getTotalprice())
                        .as("totalPrice отличается от ожидаемого")
                        .isEqualTo(expected.getTotalprice()),
                () -> assertThat(actual.getDepositpaid())
                        .as("depositpaid отличается от ожидаемого")
                        .isEqualTo(expected.getDepositpaid()),
                () -> assertThat(actual.getBookingdates())
                        .as("bookingdates равен null")
                        .isNotNull(),
                () -> assertThat(actual.getBookingdates().getCheckin())
                        .as("checkin отличается от ожидаемого")
                        .isEqualTo(expected.getBookingdates().getCheckin()),
                () -> assertThat(actual.getBookingdates().getCheckout())
                        .as("checkout отличается от ожидаемого")
                        .isEqualTo(expected.getBookingdates().getCheckout()),
                () -> assertThat(actual.getAdditionalneeds())
                        .as("additionalneeds отличается от ожидаемого")
                        .isEqualTo(expected.getAdditionalneeds())
        );
    }

    public static BookingDto randomBooking() {
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
