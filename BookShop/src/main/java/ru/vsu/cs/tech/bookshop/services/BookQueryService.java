package ru.vsu.cs.tech.bookshop.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.vsu.cs.tech.bookshop.models.BooksQuery;
import ru.vsu.cs.tech.bookshop.repositories.QueryRepository;

import java.util.List;

@Service
public class BookQueryService {

    private final QueryRepository repository;
    private final BookService service;

    public BookQueryService(QueryRepository repository, BookService service) {
        this.repository = repository;
        this.service = service;
    }

    public List<BooksQuery> getAllBookQueries() {
        return repository.findAll();
    }

    public BooksQuery getBookQueryById(Long id) throws IllegalArgumentException {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Такого запроса в издательство не существует"));
    }

    public List<BooksQuery> getBooksQueriesByBookId(Long id) {
        return repository.findQueriesByBookId(id);
    }

    public List<BooksQuery> getBooksQueriesByStatus(String status) {
        return repository.findQueriesByStatus(status);
    }

    public BooksQuery addNewQuery(Long bookId, BooksQuery query) throws IllegalArgumentException {
        query.setBook(service.getBookById(bookId));
        return repository.save(query);
    }

    public BooksQuery updateExistingQuery(Long id, BooksQuery query) throws IllegalArgumentException {
        return repository.findById(id).map(q -> {
            q.setBooksCount(query.getBooksCount());
            q.setPublishingHouse(query.getPublishingHouse());
            q.setStatus(query.getStatus());
            return repository.save(q);
        }).orElseThrow(() -> new IllegalArgumentException("Такого запроса в издательство не существует"));
    }

    public ResponseEntity<?> deleteExistingQuery(Long id) throws IllegalArgumentException {
        return repository.findById(id).map(c -> {
            repository.delete(c);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException("Такого запроса в издательство не существует"));
    }
}
