package ru.vsu.cs.tech.bookshop.controllers;

import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.tech.bookshop.dto.BookQueryGetDto;
import ru.vsu.cs.tech.bookshop.dto.BookQueryPostDto;
import ru.vsu.cs.tech.bookshop.services.BookQueryService;

import java.util.List;

@RestController
public class BookQueryController {

    private final BookQueryService service;

    public BookQueryController(BookQueryService service) {
        this.service = service;
    }

    @GetMapping("/queries")
    public List<BookQueryGetDto> getAllQueries() {
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
    public List<BookQueryGetDto> getQueriesByBook(@PathVariable Long id) {
        return service.getBooksQueriesByBookId(id);
    }

    @GetMapping("/queries/status/{status}")
    public List<BookQueryGetDto> getQueriesByStatus(@PathVariable String status) {
        return service.getBooksQueriesByStatus(status);
    }

    @PostMapping("/queries/create")
    public ResponseEntity<?> createQuery(@RequestBody BookQueryPostDto query) {
        try {
            return ResponseEntity.ok(service.addNewQuery(query));
        } catch (IllegalArgumentException e) {
            JSONObject resp = new JSONObject();
            resp.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(resp);
        }
    }

    @PutMapping("/queries/{id}/update")
    public ResponseEntity<?> updateQuery(@PathVariable Long id, @RequestBody BookQueryPostDto query) {
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
