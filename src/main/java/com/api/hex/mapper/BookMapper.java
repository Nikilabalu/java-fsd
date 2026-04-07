package com.api.hex.mapper;

import com.api.hex.dto.BookReqDto;
import com.api.hex.dto.BookRespDto;
import com.api.hex.model.Book;

public class BookMapper {
    public static Book mapToEntity(BookReqDto bookReqDto) {
        Book book = new Book();
        book.setTitle(bookReqDto.title());           // accessor
        book.setAuthor(bookReqDto.author());
        book.setIsbn(bookReqDto.isbn());
        book.setPublicationYear(bookReqDto.publicationYear());
        return book;
    }

    public static BookRespDto mapToDto(Book book) {
        return new BookRespDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getPublicationYear()
        );
    }
}
