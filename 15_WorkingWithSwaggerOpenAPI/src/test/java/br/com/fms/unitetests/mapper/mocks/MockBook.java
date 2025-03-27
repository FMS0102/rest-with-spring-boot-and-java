package br.com.fms.unitetests.mapper.mocks;

import br.com.fms.data.dto.BookDTO;
import br.com.fms.model.Book;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockBook {

    public Book mockEntity() {
        return mockEntity(0);
    }

    public BookDTO mockEntityDTO() {
        return mockEntityDTO(0);
    }

    public List<Book> mockEntityList() {
        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookDTO> mockEntityListDTO() {
        List<BookDTO> books = new ArrayList<BookDTO>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntityDTO(i));
        }
        return books;
    }

    public Book mockEntity(Integer number) {
        Book book = new Book();
        book.setAuthor("Author Test" + number);
        book.setTitle("Title Test" + number);
        book.setLaunchDate(new Date());
        book.setPrice(25D);
        book.setId(number.longValue());
        return book;
    }

    public BookDTO mockEntityDTO(Integer number) {
        BookDTO book = new BookDTO();
        book.setAuthor("Author Test" + number);
        book.setTitle("Title Test" + number);
        book.setLaunchDate(new Date());
        book.setPrice(25D);
        book.setId(number.longValue());
        return book;
    }

    ;
}
