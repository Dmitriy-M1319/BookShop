package ru.vsu.cs.tech.bookshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.tech.bookshop.models.BookCategory;
import ru.vsu.cs.tech.bookshop.repositories.BookCategoryRepository;

import java.util.List;

@RestController
public class BookCategoryController {

    @Autowired
    private BookCategoryRepository repository;

    @GetMapping("/categories")
    public List<BookCategory> getAllBookCategories() {
        return repository.findAll();
    }

    @GetMapping("/categories/{id}")
    public BookCategory getBookCategoryById(@PathVariable Long id) throws Exception{
        return repository.findById(id)
                .orElseThrow(() -> new Exception("Такой категории не существует"));
    }

    @GetMapping("/categories/name/{name}")
    public BookCategory getBookCategoryByName(@PathVariable String name) throws Exception{
        return repository.findBookCategoryByName(name)
                .orElseThrow(() -> new Exception("Такой категории не существует"));
    }

    @PostMapping("/categories/create")
    public BookCategory createBookCategory(@RequestBody BookCategory category) {
        return repository.save(category);
    }

    @PutMapping("/categories/update/{id}")
    public BookCategory updateBookCategory(@PathVariable Long id, @RequestBody BookCategory category) throws Exception{
        return repository.findById(id).map(c -> {
            c.setName(category.getName());
            return repository.save(c);
        }).orElseThrow(() -> new Exception("Категории с id " + id + "не существует"));
    }

    @DeleteMapping("/categories/delete/{id}")
    public ResponseEntity<?> deleteBookCategory(@PathVariable Long id) throws Exception {
        return repository.findById(id).map(c -> {
            repository.delete(c);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new Exception("Категории с id " + id + "не существует"));
    }
}
