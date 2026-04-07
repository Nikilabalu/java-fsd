package com.api.hex.service;

import com.api.hex.dto.BookFilterReqDto;
import com.api.hex.dto.BookPageRespDto;
import com.api.hex.dto.BookReqDto;
import com.api.hex.dto.BookRespDto;
import com.api.hex.exceptions.ResourceNotFoundException;
import com.api.hex.mapper.BookMapper;
import com.api.hex.model.Book;
import com.api.hex.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public void addBook(BookReqDto bookReqDto) {
        // Step 1: Check if ISBN already exists
        if (bookRepository.existsByIsbn(bookReqDto.isbn())) {
            throw new ResourceNotFoundException("Book with this ISBN already exists");
        }
        // Step 2: Map dto to entity
        Book book = BookMapper.mapToEntity(bookReqDto);
        // Step 3: Save in DB
        bookRepository.save(book);
    }

    public BookPageRespDto getAllBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> bookPage = bookRepository.findAll(pageable);
        List<BookRespDto> data = bookPage.getContent()
                .stream()
                .map(BookMapper::mapToDto)
                .toList();
        return new BookPageRespDto(
                data,
                bookPage.getNumber(),
                bookPage.getTotalPages(),
                bookPage.getTotalElements()
        );
    }

    public BookRespDto getByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ISBN: " + isbn));
        return BookMapper.mapToDto(book);
    }

    public void updateBook(String isbn, BookReqDto bookReqDto) {
        // Step 1: Fetch existing book by ISBN
        Book existing = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ISBN: " + isbn));
        // Step 2: Update fields
        existing.setTitle(bookReqDto.title());
        existing.setAuthor(bookReqDto.author());
        existing.setIsbn(bookReqDto.isbn());
        existing.setPublicationYear(bookReqDto.publicationYear());
        // Step 3: Save updated entity
        bookRepository.save(existing);
    }
    public List<BookRespDto> getByFilter(BookFilterReqDto bookFilterReqDto) {
        return bookRepository.filterBooks(
                        bookFilterReqDto.title(),
                        bookFilterReqDto.author(),
                        bookFilterReqDto.publicationYear()
                )
                .stream()
                .map(BookMapper::mapToDto)
                .toList();
    }
    public void deleteByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ISBN: " + isbn));
        bookRepository.deleteById(book.getId());
    }
}
