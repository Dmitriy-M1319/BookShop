 package ru.vsu.cs.tech.bookshop.controllers;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.tech.bookshop.models.Book;
import ru.vsu.cs.tech.bookshop.services.BookService;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getBookById(id));
        } catch (IllegalArgumentException e) {
            JSONObject resp = new JSONObject();
            resp.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(resp);
        }
    }

    @GetMapping("/category/{id}/books")
    public List<Book> getBooksByCategoryId(@PathVariable Long id) {
        return service.getBooksByCategory(id);
    }

    @GetMapping("/books/available")
    public List<Book> getAvailableBooks() {
        return service.getAvailableBooks();
    }

    @GetMapping("/books/new")
    public List<Book> getNewBooks() {
        return service.getBooksByStatus("новая");
    }

    @GetMapping("/books/common")
    public List<Book> getCommonBooks() {
        return service.getBooksByStatus("известная");
    }

    @PostMapping("/categories/{id}/books/create")
    public ResponseEntity<?> createBook(@PathVariable Long id, @RequestBody Book book) {
        try {
            return ResponseEntity.ok(service.addBook(book, id));
        } catch (IllegalArgumentException e) {
            JSONObject resp = new JSONObject();
            resp.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(resp);
        }
    }

    @PutMapping("/books/{id}/update")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestParam(value = "category_id") Long categoryId, @RequestBody Book book) {
        try {
            return ResponseEntity.ok(service.updateExistingBook(id, book, categoryId));
        } catch (IllegalArgumentException e) {
            JSONObject resp = new JSONObject();
            resp.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(resp);
        }
    }

    @DeleteMapping("/books/{id}/delete")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.deleteExistingBook(id));
        } catch (IllegalArgumentException e) {
            JSONObject resp = new JSONObject();
            resp.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(resp);
        }
    }
}
