package ru.vsu.cs.tech.bookshop.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.tech.bookshop.models.Book;
import ru.vsu.cs.tech.bookshop.models.BookCategory;
import ru.vsu.cs.tech.bookshop.repositories.BookCategoryRepository;
import ru.vsu.cs.tech.bookshop.repositories.BookRepository;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookRepository repository;

    @Autowired
    private BookCategoryRepository categoryRepository;

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable Long id) throws Exception{
        return repository.findById(id).orElseThrow(() -> new Exception("Такой книги не существует в списках"));
    }

    @GetMapping("/books/category/{id}")
    public List<Book> getBooksByCategoryId(@PathVariable Long id) {
       return repository.findBooksByBookCategoryId(id);
    }

    @GetMapping("/books/available")
    public List<Book> getAvailableBooks() {
        return repository.findAvailableBooks();
    }

    @GetMapping("/books/new")
    public List<Book> getNewBooks() {
        return repository.findBooksByStatus("новая");
    }

    @GetMapping("/books/common")
    public List<Book> getCommonBooks() {
        return repository.findBooksByStatus("известная");
    }

    @PostMapping("/books/create")
    public Book createBook(@RequestBody Book book) throws Exception {
        return repository.save(book);
    }


}
