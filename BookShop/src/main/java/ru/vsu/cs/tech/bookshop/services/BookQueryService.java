package ru.vsu.cs.tech.bookshop.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.vsu.cs.tech.bookshop.dto.BookQueryGetDto;
import ru.vsu.cs.tech.bookshop.dto.BookQueryPostDto;
import ru.vsu.cs.tech.bookshop.mappers.BookQueryMapper;
import ru.vsu.cs.tech.bookshop.models.BooksQuery;
import ru.vsu.cs.tech.bookshop.repositories.QueryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookQueryService {

    private final QueryRepository repository;
    private final BookService service;
    private final BookQueryMapper mapper;

    public BookQueryService(BookQueryMapper mapper, QueryRepository repository, BookService service) {
        this.mapper = mapper;
        this.repository = repository;
        this.service = service;
    }

    public List<BookQueryGetDto> getAllBookQueries() {
        return repository.findAll()
                .stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public BookQueryGetDto getBookQueryById(Long id) throws IllegalArgumentException {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Такого запроса в издательство не существует"));
    }

    public List<BookQueryGetDto> getBooksQueriesByBookId(Long id) {
        return repository.findQueriesByBookId(id).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public List<BookQueryGetDto> getBooksQueriesByStatus(String status) {
        return repository.findQueriesByStatus(status).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public BookQueryGetDto addNewQuery(BookQueryPostDto query) throws IllegalArgumentException {
        BookQueryGetDto newQuery = new BookQueryGetDto();
        newQuery.setBook(service.getBookById(query.getBookId()));
        newQuery.setPublishingHouse(query.getPublishingHouse());
        newQuery.setBooksCount(query.getBooksCount());
        newQuery.setStatus(query.getStatus());
        return mapper.toDto(repository.save(mapper.toModel(newQuery)));
    }

    public BookQueryGetDto updateExistingQuery(Long id, BookQueryPostDto query) throws IllegalArgumentException {
        return repository.findById(id).map(q -> {
            q.setBooksCount(query.getBooksCount());
            q.setPublishingHouse(query.getPublishingHouse());
            q.setStatus(query.getStatus());
            return mapper.toDto(repository.save(q));
        }).orElseThrow(() -> new IllegalArgumentException("Такого запроса в издательство не существует"));
    }

    public ResponseEntity<?> deleteExistingQuery(Long id) throws IllegalArgumentException {
        return repository.findById(id).map(c -> {
            repository.delete(c);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException("Такого запроса в издательство не существует"));
    }
}
