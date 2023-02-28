package ru.vsu.cs.tech.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.tech.bookshop.models.BookCategory;


@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {

}
