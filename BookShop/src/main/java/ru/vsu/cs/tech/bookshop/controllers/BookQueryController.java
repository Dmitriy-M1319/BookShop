package ru.vsu.cs.tech.bookshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.tech.bookshop.models.Book;
import ru.vsu.cs.tech.bookshop.models.BooksQuery;
import ru.vsu.cs.tech.bookshop.repositories.BookRepository;
import ru.vsu.cs.tech.bookshop.repositories.QueryRepository;

import java.util.List;

@RestController
public class BookQueryController {
    @Autowired
    private QueryRepository repository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/queries")
    public List<BooksQuery> getAllQueries() {
        return repository.findAll();
    }

    @GetMapping("/queries/{id}")
    public BooksQuery getQueryById(@PathVariable Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Такого запроса не существует"));
    }

    @GetMapping("/books/{id}/queries")
    public List<BooksQuery> getQueriesByBook(@PathVariable Long id) {
        return repository.findQueriesByBookId(id);
    }

    @GetMapping("/queries/status/{status}")
    public List<BooksQuery> getQueriesByStatus(@PathVariable String status) {
        return repository.findQueriesByStatus(status);
    }

    @PostMapping("/books/{id}/queries/create")
    public BooksQuery createQuery(@PathVariable Long id, @RequestBody BooksQuery query) throws Exception{
        Book book = bookRepository.findById(id).orElseThrow(() -> new Exception("Такой книги не существует"));
        query.setBook(book);
        return repository.save(query);
    }

    @PutMapping("/queries/{id}/update")
    public BooksQuery updateQuery(@PathVariable Long id, @RequestBody BooksQuery query) throws Exception {
        return repository.findById(id).map(q -> {
            q.setBooksCount(query.getBooksCount());
            q.setPublishingHouse(query.getPublishingHouse());
            q.setStatus(query.getStatus());
            return repository.save(q);
        }).orElseThrow(() -> new Exception("Такого запроса не существует"));
    }

    @DeleteMapping("/queries/{id}/delete")
    public ResponseEntity<?> deleteQuery(@PathVariable Long id) throws Exception {
        return repository.findById(id).map(c -> {
            repository.delete(c);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new Exception("Такого запроса не существует"));
    }


}
