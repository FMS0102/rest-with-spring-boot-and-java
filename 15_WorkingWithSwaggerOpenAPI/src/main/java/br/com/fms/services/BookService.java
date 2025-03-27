package br.com.fms.services;

import br.com.fms.controllers.BookController;
import br.com.fms.data.dto.BookDTO;
import br.com.fms.exceptions.RequiredObjectIsNullException;
import br.com.fms.exceptions.ResourceNotFoundException;
import br.com.fms.model.Book;
import br.com.fms.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.fms.mapper.ObjectMapper.parseListObjects;
import static br.com.fms.mapper.ObjectMapper.parseObject;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    private final Logger logger = LoggerFactory.getLogger(BookService.class.getName());

    public List<BookDTO> findAll() {
        logger.info("Find all books");
        var books = parseListObjects(bookRepository.findAll(), BookDTO.class);
        books.forEach(this::addLinksHateoas);
        return books;
    }

    public BookDTO findById(long id) {
        logger.info("Find a one book");
        var entity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));
        var dto = parseObject(entity, BookDTO.class);
        addLinksHateoas(dto);
        return dto;
    }

    public BookDTO create(BookDTO book) {
        logger.info("Creating book");

        if (book == null) throw new RequiredObjectIsNullException();

        var entity = parseObject(book, Book.class);
        var dto = parseObject(bookRepository.save(entity), BookDTO.class);
        addLinksHateoas(dto);
        return dto;
    }

    public BookDTO update(BookDTO book) {
        logger.info("Updating book");

        if (book == null) throw new RequiredObjectIsNullException();

        var entity = bookRepository.findById(book.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));

        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setTitle(book.getTitle());
        entity.setPrice(book.getPrice());

        var dto = parseObject(bookRepository.save(entity), BookDTO.class);
        addLinksHateoas(dto);
        return dto;
    }

    public void delete(long id) {
        logger.info("Deleting one book!");
        var entity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id."));

        bookRepository.delete(entity);
    }

    private void addLinksHateoas(BookDTO dto) {
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(BookController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(BookController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }

}
