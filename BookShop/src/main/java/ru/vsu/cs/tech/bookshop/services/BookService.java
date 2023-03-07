package ru.vsu.cs.tech.bookshop.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.vsu.cs.tech.bookshop.dto.BookCategoryDto;
import ru.vsu.cs.tech.bookshop.dto.BookGetDto;
import ru.vsu.cs.tech.bookshop.dto.BookPostDto;
import ru.vsu.cs.tech.bookshop.mappers.BookMapper;
import ru.vsu.cs.tech.bookshop.models.Book;
import ru.vsu.cs.tech.bookshop.models.BookCategory;
import ru.vsu.cs.tech.bookshop.repositories.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookCategoryService categoryService;
    private final BookMapper mapper;

    public BookService(BookMapper mapper, BookRepository repository, BookCategoryService service) {
        this.mapper = mapper;
        this.bookRepository = repository;
        this.categoryService = service;
    }

    public List<BookGetDto> getAllBooks() {
       return bookRepository.findAll().stream().map(mapper::toGetDto).collect(Collectors.toList());
    }

    public BookGetDto getBookById(Long id) throws IllegalArgumentException {
        return bookRepository.findById(id)
                .map(mapper::toGetDto)
                .orElseThrow(() -> new IllegalArgumentException("Такой книги не существует"));
    }

    public List<BookGetDto> getBooksByCategory(Long categoryId) {
        return bookRepository.findBooksByBookCategoryId(categoryId)
                .stream().map(mapper::toGetDto).collect(Collectors.toList());
    }

    public List<BookGetDto> getAvailableBooks() {
        return bookRepository.findAvailableBooks()
                .stream().map(mapper::toGetDto).collect(Collectors.toList());
    }

    public List<BookGetDto> getBooksByStatus(String status) {
        return bookRepository.findBooksByStatus(status)
                .stream().map(mapper::toGetDto).collect(Collectors.toList());
    }

    public BookGetDto addBook(BookPostDto book) throws IllegalArgumentException {
        BookCategoryDto categoryDto = categoryService.getCategoryById(book.getCategoryId());
        BookGetDto newBook = new BookGetDto();
        newBook.setCategory(categoryDto);
        newBook.setAuthor(book.getAuthor());
        newBook.setName(book.getName());
        newBook.setAvailability(book.getAvailability());
        newBook.setPublishingHouse(book.getPublishingHouse());
        newBook.setPublishYear(book.getPublishYear());
        newBook.setPagesCount(book.getPagesCount());
        newBook.setPrice(book.getPrice());
        newBook.setCountInShop(book.getCountInShop());
        newBook.setRetailMargin(book.getRetailMargin());
        newBook.setStatus(book.getStatus());
        return mapper.toGetDto(bookRepository.save(mapper.toModel(newBook)));
    }

    public BookGetDto updateExistingBook(Long bookId, BookPostDto newBook) throws IllegalArgumentException {
        return bookRepository.findById(bookId).map(b -> {
            b.setCategory(categoryService.getCategoryModelById(newBook.getCategoryId()));
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
            return mapper.toGetDto(bookRepository.save(b));
        }).orElseThrow(() -> new IllegalArgumentException("Такой книги не существует"));
    }

    public ResponseEntity<?> deleteExistingBook(Long bookId) throws IllegalArgumentException{
        return bookRepository.findById(bookId).map(c -> {
            bookRepository.delete(c);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException("Такой книги не существует"));
    }
}
