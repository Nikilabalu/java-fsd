package com.api.hex.repository;
import com.api.hex.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);

    @Query("""
            SELECT b
            FROM Book b
            WHERE (?1 IS NULL OR b.title LIKE %?1%)
            AND (?2 IS NULL OR b.author LIKE %?2%)
            AND (?3 IS NULL OR b.publicationYear = ?3)
            """)
    List<Book> filterBooks(String title, String author, Integer publicationYear);
}
