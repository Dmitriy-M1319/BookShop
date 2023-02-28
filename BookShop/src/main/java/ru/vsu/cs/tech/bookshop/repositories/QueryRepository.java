package ru.vsu.cs.tech.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.tech.bookshop.models.Query;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface QueryRepository extends JpaRepository<Query, Long> {
    List<Query> findQueriesByBookId(Long bookId);
    List<Query> findQueriesByStatus(String status);
}
