package ru.vsu.cs.tech.bookshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public Book createBook(@RequestParam(value = "category_id") Long categoryId, @RequestBody Book book) throws Exception {
        BookCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new Exception("Такой категории не существует"));
        book.setCategory(category);
        return repository.save(book);
    }

    @PutMapping("/books/{id}/update")
    public Book updateBook(@PathVariable Long id, @RequestParam(value = "category_id") Long categoryId, @RequestBody Book book) throws Exception {
        return repository.findById(id).map(b -> {
            if(categoryId != 0 && !categoryId.equals(b.getCategory().getCategoryId())) {
                BookCategory category = null;
                try {
                    category = categoryRepository.findById(categoryId)
                            .orElseThrow(() -> new Exception("Такой категории не существует"));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                book.setCategory(category);
            }
            b.setAuthor(book.getAuthor());
            b.setName(book.getName());
            b.setAvailability(book.getAvailability());
            b.setPublishingHouse(book.getPublishingHouse());
            b.setPublishYear(book.getPublishYear());
            b.setPagesCount(book.getPagesCount());
            b.setPrice(b.getPrice());
            b.setCountInShop(b.getCountInShop());
            b.setRetailMargin(b.getRetailMargin());
            b.setStatus(b.getStatus());
            return repository.save(b);
                }).orElseThrow(() -> new Exception("Такой книги не существует"));
    }

    @DeleteMapping("/books/{id}/delete")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) throws Exception{
        return repository.findById(id).map(c -> {
            repository.delete(c);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new Exception("Такой книги не существует"));
    }


}
