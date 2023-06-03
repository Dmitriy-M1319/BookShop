package ru.vsu.cs.tech.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.tech.bookshop.models.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
   @Query(value = "select b from Book b where b.category.id = ?1")
   List<Book> findBooksByBookCategoryId(Long categoryId);

   @Query(value = "select b from Book b where b.availability = true")
   List<Book> findAvailableBooks();

   List<Book> findBooksByStatus(String status);
}
