package ru.vsu.cs.tech.bookshop.controllers;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.tech.bookshop.dto.BookCategoryDto;
import ru.vsu.cs.tech.bookshop.services.BookCategoryService;

import java.util.List;

@RestController
public class BookCategoryController {

    private final BookCategoryService service;

    public BookCategoryController(BookCategoryService service) {
        this.service = service;
    }

    @GetMapping("/categories")
    public List<BookCategoryDto> getAllBookCategories() {
        return service.getAllCategories();
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<?> getBookCategoryById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getCategoryById(id));
        } catch (IllegalArgumentException e) {
            JSONObject resp = new JSONObject();
            resp.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(resp);
        }
    }

    @GetMapping("/categories/name/{name}")
    public ResponseEntity<?> getBookCategoryByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(service.getCategoryByName(name));
        } catch (IllegalArgumentException e) {
            JSONObject resp = new JSONObject();
            resp.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(resp);
        }
    }

    @PostMapping("/categories/create")
    public BookCategoryDto createBookCategory(@RequestBody BookCategoryDto category) {
        return service.addNewCategory(category);
    }

    @PutMapping("/categories/{id}/update")
    public ResponseEntity<?> updateBookCategory(@PathVariable Long id, @RequestBody BookCategoryDto category) {
        try {
            return ResponseEntity.ok(service.updateExistingCategory(id, category));
        } catch (IllegalArgumentException e) {
            JSONObject resp = new JSONObject();
            resp.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(resp);
        }
    }

    @DeleteMapping("/categories/delete/{id}")
    public ResponseEntity<?> deleteBookCategory(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.deleteExistingCategory(id));
        } catch (IllegalArgumentException e) {
            JSONObject resp = new JSONObject();
            resp.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(resp);
        }
    }
}
