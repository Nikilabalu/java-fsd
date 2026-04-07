package com.api.hex.service;
import com.api.hex.dto.BookRespDto;
import com.api.hex.exceptions.ResourceNotFoundException;
import com.api.hex.model.Book;
import com.api.hex.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import com.api.hex.dto.BookPageRespDto;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class ServiceTest {
    @InjectMocks
    private BookService bookService;
    @Mock
    private BookRepository bookRepository;
    @Test
    public void getByIsbnWhenExists() {
        Assertions.assertNotNull(bookService);
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Harry Potter");
        book.setAuthor("Rowling");
        book.setIsbn("94431782799");
        book.setPublicationYear(2008);
        Mockito.when(bookRepository.findByIsbn("94431782799")).thenReturn(Optional.of(book));
        BookRespDto dto = new BookRespDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getPublicationYear()
        );

        BookRespDto dtoWrongAuthor = new BookRespDto(
                book.getId(),
                book.getTitle(),
                "Someone Else",        // different author — should not match
                book.getIsbn(),
                book.getPublicationYear()
        );
        Assertions.assertEquals(dto, bookService.getByIsbn("94431782799"));
        Assertions.assertNotEquals(dtoWrongAuthor, bookService.getByIsbn("94431782799"));
        Mockito.verify(bookRepository, times(2)).findByIsbn("94431782799");
    }
    @Test
    public void getByIsbnWhenNotFound() {
        when(bookRepository.findByIsbn("123456789")).thenReturn(Optional.empty());
        Exception e = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            bookService.getByIsbn("123456789");
        });
        Assertions.assertEquals("Book not found with ISBN: 123456789", e.getMessage());
    }
    @Test
    public void getAllBooksWhenBooksExist() {
        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Harry Potter");
        book1.setAuthor("Rowling");
        book1.setIsbn("94431782799");
        book1.setPublicationYear(2008);

        Book book2 = new Book();
        book2.setId(2L);
        book2.setTitle("Rich dad poor dad");
        book2.setAuthor("Robert Kiyosaki");
        book2.setIsbn("9780201616224");
        book2.setPublicationYear(1999);

        Page<Book> bookPage = new PageImpl<>(List.of(book1, book2));
        Pageable pageable = PageRequest.of(0, 10);

        when(bookRepository.findAll(pageable)).thenReturn(bookPage);

        BookPageRespDto result = bookService.getAllBooks(0, 10);

        Assertions.assertEquals(2, result.data().size());
        Assertions.assertEquals("Harry Potter", result.data().get(0).title());
        Assertions.assertEquals("Rich dad poor dad", result.data().get(1).title());
        Assertions.assertEquals("94431782799", result.data().get(0).isbn());
    }

    @Test
    public void getAllBooksWhenNoBooksExist() {
        Page<Book> emptyPage = new PageImpl<>(List.of());
        Pageable pageable = PageRequest.of(0, 10);

        when(bookRepository.findAll(pageable)).thenReturn(emptyPage);

        BookPageRespDto result = bookService.getAllBooks(0, 10);

        Assertions.assertTrue(result.data().isEmpty());
    }
}
