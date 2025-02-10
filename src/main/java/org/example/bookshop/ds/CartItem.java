package org.example.bookshop.ds;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString
public class CartItem implements Comparable<CartItem>{
    private int bookId;
    private String title;
    private int quantity;
    private double price;

    @Override
    public int compareTo(CartItem obj) {
        return bookId - obj.bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CartItem cartItem)) return false;
        return getBookId() == cartItem.getBookId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getBookId());
    }
}
