package org.example.bookshop.dao;

import org.example.bookshop.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookDao extends JpaRepository<Book,Integer> {
    @Query("""
select b.price from Book b where b.id = ?1
""")
    double getPriceByBookId(int bookId);
}
