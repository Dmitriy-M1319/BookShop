package ru.vsu.cs.tech.bookshop.services;

import org.springframework.stereotype.Service;
import ru.vsu.cs.tech.bookshop.models.BookCategory;
import ru.vsu.cs.tech.bookshop.repositories.BookCategoryRepository;

import java.util.List;

@Service
public class BookCategoryService {
    private final BookCategoryRepository repository;

    public BookCategoryService(BookCategoryRepository repository) {
        this.repository = repository;
    }

    public List<BookCategory> getAllCategories() {
       return repository.findAll();
    }
}
