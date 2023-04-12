package com.epam.setbasicbackend.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "STORE_ITEMS")
public class StoreItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "available_qty")
    private Integer available_qty;

    @Column(name = "booked_qty")
    private Integer booked_qty;

    @Column(name = "sold_qty")
    private Integer sold_qty;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StoreItem storeItem = (StoreItem) o;

        if (product != null ? !product.equals(storeItem.product) : storeItem.product != null) return false;
        if (available_qty != null ? !available_qty.equals(storeItem.available_qty) : storeItem.available_qty != null)
            return false;
        if (booked_qty != null ? !booked_qty.equals(storeItem.booked_qty) : storeItem.booked_qty != null) return false;
        return sold_qty != null ? sold_qty.equals(storeItem.sold_qty) : storeItem.sold_qty == null;
    }

    @Override
    public int hashCode() {
        int result = product != null ? product.hashCode() : 0;
        result = 31 * result + (available_qty != null ? available_qty.hashCode() : 0);
        result = 31 * result + (booked_qty != null ? booked_qty.hashCode() : 0);
        result = 31 * result + (sold_qty != null ? sold_qty.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StoreItem{" +
                "id=" + id +
                ", product=" + product +
                ", available_qty=" + available_qty +
                ", booked_qty=" + booked_qty +
                ", sold_qty=" + sold_qty +
                '}';
    }
}
