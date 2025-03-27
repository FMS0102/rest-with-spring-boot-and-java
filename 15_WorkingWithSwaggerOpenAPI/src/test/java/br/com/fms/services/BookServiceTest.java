package br.com.fms.services;

import br.com.fms.data.dto.BookDTO;
import br.com.fms.data.dto.PersonDTO;
import br.com.fms.exceptions.RequiredObjectIsNullException;
import br.com.fms.model.Book;
import br.com.fms.model.Person;
import br.com.fms.repositories.BookRepository;
import br.com.fms.unitetests.mapper.mocks.MockBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    MockBook input;

    @InjectMocks
    private BookService service;

    @Mock
    BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        input = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Book book = input.mockEntity(1);
        book.setId(1L);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        var result = service.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertNotNull(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("self")
                                && link.getHref().endsWith("/api/book/v1/1")
                                && link.getType().equals("GET"))
        );

        assertNotNull(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("findAll")
                                && link.getHref().endsWith("/api/book/v1")
                                && link.getType().equals("GET"))
        );

        assertNotNull(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("create")
                                && link.getHref().endsWith("/api/book/v1")
                                && link.getType().equals("POST"))
        );

        assertNotNull(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("update")
                                && link.getHref().endsWith("/api/book/v1")
                                && link.getType().equals("PUT"))
        );

        assertNotNull(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("delete")
                                && link.getHref().endsWith("/api/book/v1/1")
                                && link.getType().equals("DELETE"))
        );

        assertEquals("Author Test1", result.getAuthor());
        assertEquals("Title Test1", result.getTitle());
        assertNotNull(result.getLaunchDate());
        assertEquals(25D, result.getPrice());
    }

    @Test
    void create() {
        var book = input.mockEntity(1);
        var persisted = book;
        persisted.setId(1L);

        BookDTO dto = input.mockEntityDTO(1);

        when(bookRepository.save(Mockito.any(Book.class))).thenReturn(persisted);
        var result = service.create(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertNotNull(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("self")
                                && link.getHref().endsWith("/api/book/v1/1")
                                && link.getType().equals("GET"))
        );

        assertNotNull(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("findAll")
                                && link.getHref().endsWith("/api/book/v1")
                                && link.getType().equals("GET"))
        );

        assertNotNull(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("create")
                                && link.getHref().endsWith("/api/book/v1")
                                && link.getType().equals("POST"))
        );

        assertNotNull(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("update")
                                && link.getHref().endsWith("/api/book/v1")
                                && link.getType().equals("PUT"))
        );

        assertNotNull(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("delete")
                                && link.getHref().endsWith("/api/book/v1/1")
                                && link.getType().equals("DELETE"))
        );

        assertEquals("Author Test1", result.getAuthor());
        assertEquals("Title Test1", result.getTitle());
        assertNotNull(result.getLaunchDate());
        assertEquals(25D, result.getPrice());

    }

    @Test
    void testCreateWithNullBook(){
        Exception exception = assertThrows(RequiredObjectIsNullException.class,
                () -> {
                    service.create(null);
                });
        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void update() {
        Book book = input.mockEntity(1);
        Book persisted = book;
        persisted.setId(1L);

        BookDTO dto = input.mockEntityDTO(1);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookRepository.save(book)).thenReturn(persisted);

        var result = service.update(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertNotNull(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("self")
                                && link.getHref().endsWith("/api/book/v1/1")
                                && link.getType().equals("GET"))
        );

        assertNotNull(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("findAll")
                                && link.getHref().endsWith("/api/book/v1")
                                && link.getType().equals("GET"))
        );

        assertNotNull(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("create")
                                && link.getHref().endsWith("/api/book/v1")
                                && link.getType().equals("POST"))
        );

        assertNotNull(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("update")
                                && link.getHref().endsWith("/api/book/v1")
                                && link.getType().equals("PUT"))
        );

        assertNotNull(
                result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("delete")
                                && link.getHref().endsWith("/api/book/v1/1")
                                && link.getType().equals("DELETE"))
        );

        assertEquals("Author Test1", result.getAuthor());
        assertEquals("Title Test1", result.getTitle());
        assertNotNull(result.getLaunchDate());
        assertEquals(25D, result.getPrice());
    }

    @Test
    void testUpdateWithNullBook() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class,
                () -> {
                    service.update(null);
                }
        );

        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    void delete() {
        Book book = input.mockEntity(1);
        book.setId(1L);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        service.delete(1L);

        verify(bookRepository, times(1)).findById(anyLong());
        verify(bookRepository, times(1)).delete(any(Book.class));
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    void findAll() {

        List<Book> list = input.mockEntityList();
        when(bookRepository.findAll()).thenReturn(list);

        List<BookDTO> books = service.findAll();

        assertNotNull(books);
        assertEquals(14, books.size());

        var bookOne = books.get(1);

        assertNotNull(bookOne);
        assertNotNull(bookOne.getId());
        assertNotNull(bookOne.getLinks());

        assertNotNull(bookOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/book/v1/1")
                        && link.getType().equals("GET"))
        );

        assertNotNull(bookOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("GET"))
        );

        assertNotNull(bookOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("POST"))
        );

        assertNotNull(bookOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("PUT"))
        );

        assertNotNull(bookOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/book/v1/1")
                        && link.getType().equals("DELETE"))
        );

        assertEquals("Author Test1", bookOne.getAuthor());
        assertEquals("Title Test1", bookOne.getTitle());
        assertNotNull(bookOne.getLaunchDate());
        assertEquals(25D, bookOne.getPrice());

        var bookFour = books.get(4);

        assertNotNull(bookFour);
        assertNotNull(bookFour.getId());
        assertNotNull(bookFour.getLinks());

        assertNotNull(bookFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/book/v1/4")
                        && link.getType().equals("GET"))
        );

        assertNotNull(bookFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("GET"))
        );

        assertNotNull(bookFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("POST"))
        );

        assertNotNull(bookFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("PUT"))
        );

        assertNotNull(bookFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/book/v1/4")
                        && link.getType().equals("DELETE"))
        );

        assertEquals("Author Test4", bookFour.getAuthor());
        assertEquals("Title Test4", bookFour.getTitle());
        assertNotNull(bookFour.getLaunchDate());
        assertEquals(25D, bookFour.getPrice());

        var bookSeven = books.get(7);

        assertNotNull(bookSeven);
        assertNotNull(bookSeven.getId());
        assertNotNull(bookSeven.getLinks());

        assertNotNull(bookSeven.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/book/v1/7")
                        && link.getType().equals("GET"))
        );

        assertNotNull(bookSeven.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("GET"))
        );

        assertNotNull(bookSeven.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("POST"))
        );

        assertNotNull(bookSeven.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("PUT"))
        );

        assertNotNull(bookSeven.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/book/v1/7")
                        && link.getType().equals("DELETE"))
        );

        assertEquals("Author Test7", bookSeven.getAuthor());
        assertEquals("Title Test7", bookSeven.getTitle());
        assertNotNull(bookSeven.getLaunchDate());
        assertEquals(25D, bookSeven.getPrice());

    }
}
