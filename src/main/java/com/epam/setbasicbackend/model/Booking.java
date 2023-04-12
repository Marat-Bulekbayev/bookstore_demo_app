package com.epam.setbasicbackend.model;

import com.epam.setbasicbackend.model.enums.BookingStatus;
import javax.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "BOOKINGS")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "date")
    private Date date;

    @Column(name = "time")
    private Time time;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BookingStatus status;

    @Column(name = "quantity")
    private Integer quantity;

    public Booking() {
    }

    public Booking(Product product, User user, String deliveryAddress, Date date, Time time, BookingStatus status, Integer quantity) {
        this.product = product;
        this.user = user;
        this.deliveryAddress = deliveryAddress;
        this.date = date;
        this.time = time;
        this.status = status;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Booking booking = (Booking) o;

        if (product != null ? !product.equals(booking.product) : booking.product != null) return false;
        if (user != null ? !user.equals(booking.user) : booking.user != null) return false;
        if (deliveryAddress != null ? !deliveryAddress.equals(booking.deliveryAddress) : booking.deliveryAddress != null)
            return false;
        if (date != null ? !date.equals(booking.date) : booking.date != null) return false;
        if (time != null ? !time.equals(booking.time) : booking.time != null) return false;
        if (status != booking.status) return false;
        return quantity != null ? quantity.equals(booking.quantity) : booking.quantity == null;
    }

    @Override
    public int hashCode() {
        int result = product != null ? product.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (deliveryAddress != null ? deliveryAddress.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", product=" + product +
                ", user=" + user +
                ", delivery_address='" + deliveryAddress + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", status=" + status +
                ", quantity=" + quantity +
                '}';
    }
}
