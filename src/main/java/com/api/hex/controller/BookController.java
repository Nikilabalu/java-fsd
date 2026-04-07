package com.api.hex.controller;
import com.api.hex.dto.BookFilterReqDto;
import com.api.hex.dto.BookPageRespDto;
import com.api.hex.dto.BookReqDto;
import com.api.hex.dto.BookRespDto;
import com.api.hex.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/book")
@AllArgsConstructor
public class BookController {
    private final BookService bookService;
    @PostMapping("/add")
    public ResponseEntity<?> addBook(@Valid @RequestBody BookReqDto bookReqDto) {
        bookService.addBook(bookReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/get-all")
    public ResponseEntity<BookPageRespDto> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.getAllBooks(page, size));
    }

    @GetMapping("/get/{isbn}")
    public ResponseEntity<BookRespDto> getByIsbn(@PathVariable String isbn) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getByIsbn(isbn));
    }
    @PutMapping("/update/{isbn}")
    public ResponseEntity<?> updateBook(@PathVariable String isbn,
                                        @Valid @RequestBody BookReqDto bookReqDto) {
        bookService.updateBook(isbn, bookReqDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @PostMapping("/filter")
    public ResponseEntity<List<BookRespDto>> getByFilter(@RequestBody BookFilterReqDto bookFilterReqDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.getByFilter(bookFilterReqDto));
    }
    @DeleteMapping("/delete/{isbn}")
    public ResponseEntity<?> deleteBook(@PathVariable String isbn) {
        bookService.deleteByIsbn(isbn);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
