package ru.vsu.cs.tech.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.tech.bookshop.models.BooksQuery;

import java.util.List;

@Repository
public interface QueryRepository extends JpaRepository<BooksQuery, Long> {
    @Query("select q from BooksQuery q where q.book.bookId = ?1")
    List<BooksQuery> findQueriesByBookId(Long bookId);
    List<BooksQuery> findQueriesByStatus(String status);
}
