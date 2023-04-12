package com.epam.setbasicbackend.service;

import com.epam.setbasicbackend.exception.NoSuchBookingException;
import com.epam.setbasicbackend.model.Booking;
import com.epam.setbasicbackend.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking findBookingById(Long id) throws NoSuchBookingException {
        return bookingRepository.findById(id).orElseThrow(() -> new NoSuchBookingException("No such booking have been found by id: " + id));
    }

    @Override
    public List<Booking> findAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking editBooking(Long id, Booking booking) throws NoSuchBookingException {
        Booking originalBooking = bookingRepository.findById(id).orElseThrow(() -> new NoSuchBookingException("No such booking have been found by id: " + id));

        if (!originalBooking.getProduct().equals(booking.getProduct())) {
            originalBooking.setProduct(booking.getProduct());
        }
        if (!originalBooking.getUser().equals(booking.getUser())) {
            originalBooking.setUser(booking.getUser());
        }
        if (!originalBooking.getDeliveryAddress().equals(booking.getDeliveryAddress())) {
            originalBooking.setDeliveryAddress(booking.getDeliveryAddress());
        }
        if (!originalBooking.getDate().equals(booking.getDate())) {
            originalBooking.setDate(booking.getDate());
        }
        if (!originalBooking.getTime().equals(booking.getTime())) {
            originalBooking.setTime(booking.getTime());
        }
        if (!originalBooking.getStatus().equals(booking.getStatus())) {
            originalBooking.setStatus(booking.getStatus());
        }
        if (!originalBooking.getQuantity().equals(booking.getQuantity())) {
            originalBooking.setQuantity(booking.getQuantity());
        }

        return bookingRepository.save(originalBooking);
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}
