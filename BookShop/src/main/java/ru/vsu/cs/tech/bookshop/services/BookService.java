package ru.vsu.cs.tech.bookshop.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.vsu.cs.tech.bookshop.models.Book;
import ru.vsu.cs.tech.bookshop.models.BookCategory;
import ru.vsu.cs.tech.bookshop.repositories.BookCategoryRepository;
import ru.vsu.cs.tech.bookshop.repositories.BookRepository;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookCategoryRepository categoryRepository;

    public BookService(BookRepository repository, BookCategoryRepository categoryRepository) {
        this.bookRepository = repository;
        this.categoryRepository = categoryRepository;
    }

    public List<Book> getAllBooks() {
       return bookRepository.findAll();
    }

    public Book getBookById(Long id) throws IllegalArgumentException {
        return bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Такой книги не существует"));
    }

    public List<Book> getBooksByCategory(Long categoryId) {
        return bookRepository.findBooksByBookCategoryId(categoryId);
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.findAvailableBooks();
    }

    public List<Book> getBooksByStatus(String status) {
        return bookRepository.findBooksByStatus("новая");
    }

    public Book addBook(Book book, Long categoryId) throws IllegalArgumentException {
        BookCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Такой категории не существует"));
        book.setCategory(category);
        return bookRepository.save(book);
    }

    public Book updateExistingBook(Long bookId, Book newBook, Long categoryId) throws IllegalArgumentException {
        return bookRepository.findById(bookId).map(b -> {
            if(categoryId != 0 && !categoryId.equals(b.getCategory().getCategoryId())) {
                BookCategory category = null;
                try {
                    category = categoryRepository.findById(categoryId)
                            .orElseThrow(() -> new IllegalArgumentException("Такой категории не существует"));
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException(e);
                }
                newBook.setCategory(category);
            }
            b.setAuthor(newBook.getAuthor());
            b.setName(newBook.getName());
            b.setAvailability(newBook.getAvailability());
            b.setPublishingHouse(newBook.getPublishingHouse());
            b.setPublishYear(newBook.getPublishYear());
            b.setPagesCount(newBook.getPagesCount());
            b.setPrice(b.getPrice());
            b.setCountInShop(b.getCountInShop());
            b.setRetailMargin(b.getRetailMargin());
            b.setStatus(b.getStatus());
            return bookRepository.save(b);
        }).orElseThrow(() -> new IllegalArgumentException("Такой книги не существует"));
    }

    public ResponseEntity<?> deleteExistingBook(Long bookId) throws IllegalArgumentException{
        return bookRepository.findById(bookId).map(c -> {
            bookRepository.delete(c);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException("Такой книги не существует"));
    }
}
