package ru.bulgakov.booking;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ru.bulgakov.booking.config.BookingConfig;
import ru.bulgakov.booking.dto.AuthRequest;
import ru.bulgakov.booking.dto.AuthResponse;
import ru.bulgakov.booking.dto.BookingDto;

import static io.restassured.RestAssured.given;
import static ru.bulgakov.booking.config.BookingApiConfig.getBookingConfig;

public class BookingApiClient {
    private static final BookingConfig config = getBookingConfig();

    private static RequestSpecification spec = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .build();

    public Response auth(String user, String password) {
         return given(spec)
                .body(new AuthRequest(user, password))
                .when()
                .post(config.bookingUrl() + "/auth")
                .then()
                .extract().response();
    }

    public Response getBooking(Integer id) {
        return given(spec)
                .when()
                .pathParam("BOOKING_ID", id)
                .get(config.bookingUrl() + "/booking/{BOOKING_ID}")
                .then()
                .extract().response();
    }

    public Response createBooking(BookingDto booking) {
        return given(spec)
                .body(booking)
                .when()
                .post(config.bookingUrl() + "/booking")
                .then()
                .extract().response();
    }

    public Response updateBooking(BookingDto booking, Integer id) {
        return given(spec)
                .cookie("token", getToken())
                .body(booking)
                .pathParam("BOOKING_ID", id)
                .when()
                .put(config.bookingUrl() + "/booking/{BOOKING_ID}")
                .then()
                .extract().response();
    }

    public Response partialUpdateBooking(BookingDto booking, Integer id) {
        return given(spec)
                .cookie("token", getToken())
                .body(booking)
                .pathParam("BOOKING_ID", id)
                .when()
                .patch(config.bookingUrl() + "/booking/{BOOKING_ID}")
                .then()
                .extract().response();
    }

    public Response deleteBooking(Integer id) {
        return given(spec)
                .cookie("token", getToken())
                .pathParam("BOOKING_ID", id)
                .when()
                .delete(config.bookingUrl() + "/booking/{BOOKING_ID}")
                .then()
                .extract().response();
    }

    private String getToken() {
        return auth(config.username(), config.password()).as(AuthResponse.class).getToken();
    }
}
