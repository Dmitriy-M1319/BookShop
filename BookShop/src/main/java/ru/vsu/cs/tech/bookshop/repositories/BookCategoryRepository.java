package ru.vsu.cs.tech.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.tech.bookshop.models.BookCategory;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {
    Optional<BookCategory> findBookCategoryByName(String name);
}
