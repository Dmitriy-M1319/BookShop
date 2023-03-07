 package ru.vsu.cs.tech.bookshop.controllers;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.tech.bookshop.dto.BookGetDto;
import ru.vsu.cs.tech.bookshop.dto.BookPostDto;
import ru.vsu.cs.tech.bookshop.models.Book;
import ru.vsu.cs.tech.bookshop.services.BookService;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping("/books")
    public List<BookGetDto> getAllBooks() {
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
    public List<BookGetDto> getBooksByCategoryId(@PathVariable Long id) {
        return service.getBooksByCategory(id);
    }

    @GetMapping("/books/available")
    public List<BookGetDto> getAvailableBooks() {
        return service.getAvailableBooks();
    }

    @GetMapping("/books/new")
    public List<BookGetDto> getNewBooks() {
        return service.getBooksByStatus("новая");
    }

    @GetMapping("/books/common")
    public List<BookGetDto> getCommonBooks() {
        return service.getBooksByStatus("известная");
    }

    @PostMapping("/books/create")
    public ResponseEntity<?> createBook(@RequestBody BookPostDto book) {
        try {
            return ResponseEntity.ok(service.addBook(book));
        } catch (IllegalArgumentException e) {
            JSONObject resp = new JSONObject();
            resp.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(resp);
        }
    }

    @PutMapping("/books/{id}/update")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody BookPostDto book) {
        try {
            return ResponseEntity.ok(service.updateExistingBook(id, book));
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
