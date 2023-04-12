package com.epam.setbasicbackend.service;

import com.epam.setbasicbackend.exception.NoSuchBookingException;
import com.epam.setbasicbackend.model.Booking;
import java.util.List;

public interface BookingService {

    Booking createBooking(Booking booking);

    Booking findBookingById(Long id) throws NoSuchBookingException;

    List<Booking> findAllBookings();

    Booking editBooking(Long id, Booking booking) throws NoSuchBookingException;

    void deleteBooking(Long id);
}
