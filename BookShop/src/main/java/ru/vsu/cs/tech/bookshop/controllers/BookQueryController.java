package ru.vsu.cs.tech.bookshop.controllers;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.tech.bookshop.models.Book;
import ru.vsu.cs.tech.bookshop.models.BooksQuery;
import ru.vsu.cs.tech.bookshop.repositories.BookRepository;
import ru.vsu.cs.tech.bookshop.repositories.QueryRepository;
import ru.vsu.cs.tech.bookshop.services.BookQueryService;

import java.util.List;

@RestController
public class BookQueryController {
    @Autowired
    private BookQueryService service;

    @GetMapping("/queries")
    public List<BooksQuery> getAllQueries() {
        return service.getAllBookQueries();
    }

    @GetMapping("/queries/{id}")
    public ResponseEntity<?> getQueryById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getBookQueryById(id));
        } catch (IllegalArgumentException e) {
            JSONObject resp = new JSONObject();
            resp.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(resp);
        }
    }

    @GetMapping("/books/{id}/queries")
    public List<BooksQuery> getQueriesByBook(@PathVariable Long id) {
        return service.getBooksQueriesByBookId(id);
    }

    @GetMapping("/queries/status/{status}")
    public List<BooksQuery> getQueriesByStatus(@PathVariable String status) {
        return service.getBooksQueriesByStatus(status);
    }

    @PostMapping("/books/{id}/queries/create")
    public ResponseEntity<?> createQuery(@PathVariable Long id, @RequestBody BooksQuery query) {
        try {
            return ResponseEntity.ok(service.addNewQuery(id, query));
        } catch (IllegalArgumentException e) {
            JSONObject resp = new JSONObject();
            resp.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(resp);
        }
    }

    @PutMapping("/queries/{id}/update")
    public ResponseEntity<?> updateQuery(@PathVariable Long id, @RequestBody BooksQuery query) {
        try {
            return ResponseEntity.ok(service.updateExistingQuery(id, query));
        } catch (IllegalArgumentException e) {
            JSONObject resp = new JSONObject();
            resp.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(resp);
        }
    }

    @DeleteMapping("/queries/{id}/delete")
    public ResponseEntity<?> deleteQuery(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.deleteExistingQuery(id));
        } catch (IllegalArgumentException e) {
            JSONObject resp = new JSONObject();
            resp.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(resp);
        }
    }
}
