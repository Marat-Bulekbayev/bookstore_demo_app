package com.epam.setbasicbackend.controller;

import com.epam.setbasicbackend.exception.NoSuchBookingException;
import com.epam.setbasicbackend.model.Booking;
import com.epam.setbasicbackend.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Operation(summary = "Get a booking by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking is found by id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Booking.class)) }),
            @ApiResponse(responseCode = "404", description = "No such booking have been found by id",
                    content = @Content) })
    @GetMapping("{id}")
    public ResponseEntity<Booking> getBooking(@Parameter(description = "Booking ID") @PathVariable Long id) throws NoSuchBookingException {
        return ResponseEntity.ok(bookingService.findBookingById(id));
    }

    @Operation(summary = "Get a list of bookings")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of bookings is returned",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Booking.class)) })})
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingService.findAllBookings());
    }

    @Operation(summary = "Create new booking")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New booking is created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Booking.class)) })})
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        return ResponseEntity.ok(bookingService.createBooking(booking));
    }

    @Operation(summary = "Edit booking")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking has been edited",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Booking.class)),
                    }),
            @ApiResponse(responseCode = "404", description = "No such booking have been found by id",
                    content = @Content)})
    @PutMapping("{id}")
    public ResponseEntity<Booking> editBooking(@Parameter(description = "Booking ID") @PathVariable Long id, @RequestBody Booking booking) throws NoSuchBookingException {
        return ResponseEntity.ok(bookingService.editBooking(id, booking));
    }

    @Operation(summary = "Delete a booking by id")
    @ApiResponse(responseCode = "204", description = "Booking has been deleted", content = @Content)
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBooking(@Parameter(description = "Booking ID") @PathVariable Long id) {
        bookingService.deleteBooking(id);
    }
}
