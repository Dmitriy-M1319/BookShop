package ru.vsu.cs.tech.bookshop.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.vsu.cs.tech.bookshop.dto.BookCategoryDto;
import ru.vsu.cs.tech.bookshop.mappers.BookCategoryMapper;
import ru.vsu.cs.tech.bookshop.models.BookCategory;
import ru.vsu.cs.tech.bookshop.repositories.BookCategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookCategoryService {
    private final BookCategoryRepository repository;
    private BookCategoryMapper mapper;

    public BookCategoryService(BookCategoryRepository repository, BookCategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<BookCategoryDto> getAllCategories() {
       return repository.findAll().stream()
               .map(mapper::toDto).collect(Collectors.toList());
    }

    public BookCategoryDto getCategoryById(Long id) throws IllegalArgumentException {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Такой категории не существует"));
    }

    public BookCategory getCategoryModelById(Long id) throws IllegalArgumentException {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Такой категории не существует"));
    }

    public BookCategoryDto getCategoryByName(String name) throws IllegalArgumentException {
        return repository.findBookCategoryByName(name)
                .map(mapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Такой категории не существует"));
    }

    public BookCategoryDto addNewCategory(BookCategoryDto newCategory) {
        return mapper.toDto(repository.save(mapper.toModel(newCategory)));
    }

    public BookCategoryDto updateExistingCategory(Long id, BookCategoryDto newCategory) throws IllegalArgumentException {
        return repository.findById(id).map(c -> {
            c.setName(newCategory.getName());
            return mapper.toDto(repository.save(c));
        }).orElseThrow(() -> new IllegalArgumentException("Такой категории не существует"));
    }

    public ResponseEntity<?> deleteExistingCategory(Long id) throws IllegalArgumentException {
        return repository.findById(id).map(c -> {
            repository.delete(c);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException("Такой категории не существует"));
    }
}
