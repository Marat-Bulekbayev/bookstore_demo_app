package com.epam.setbasicbackend.service;

import com.epam.setbasicbackend.exception.NoSuchBookingException;
import com.epam.setbasicbackend.model.Booking;
import com.epam.setbasicbackend.model.Product;
import com.epam.setbasicbackend.model.User;
import com.epam.setbasicbackend.model.enums.BookingStatus;
import com.epam.setbasicbackend.repository.BookingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Test
    void testCreateBooking() {
        Booking booking = new Booking();
        booking.setStatus(BookingStatus.CANCELLED);
        when(bookingRepository.save(ArgumentMatchers.any(Booking.class))).thenReturn(booking);
        Booking actualBooking = bookingService.createBooking(booking);
        Assertions.assertEquals(actualBooking.getStatus(), booking.getStatus());
        verify(bookingRepository).save(booking);
    }

    @Test
    void testFindBookingById() throws NoSuchBookingException {
        Booking booking = new Booking();
        booking.setId(1L);
        when(bookingRepository.findById(booking.getId())).thenReturn(Optional.of(booking));
        Booking actualBooking = bookingService.findBookingById(booking.getId());
        assertThat(actualBooking).isSameAs(booking);
        verify(bookingRepository).findById(booking.getId());
    }

    @Test
    void testFindAllBookings() {
        List<Booking> bookingList = new ArrayList<>();
        bookingList.add(new Booking());
        given(bookingRepository.findAll()).willReturn(bookingList);
        List<Booking> actualBookingList = bookingService.findAllBookings();
        Assertions.assertEquals(actualBookingList, bookingList);
        verify(bookingRepository).findAll();
    }

    @Test
    void testEditBooking() throws NoSuchBookingException {
        Booking booking = new Booking(new Product(),
                new User(),
                "Test_Delivery_Address",
                new Date(),
                new Time(1L),
                BookingStatus.CANCELLED,
                1);
        Booking updatedBooking = new Booking(new Product(),
                new User(),
                "Test_Delivery_Address_Updated",
                new Date(),
                new Time(2L),
                BookingStatus.SUBMITTED,
                2);
        given(bookingRepository.findById(booking.getId())).willReturn(Optional.of(booking));
        bookingService.editBooking(booking.getId(), updatedBooking);
        verify(bookingRepository).save(updatedBooking);
        verify(bookingRepository).findById(booking.getId());
    }

    @Test
    void testDeleteBooking() {
        Booking booking = new Booking();
        booking.setId(1L);
        bookingService.deleteBooking(booking.getId());
        verify(bookingRepository).deleteById(booking.getId());
    }
}