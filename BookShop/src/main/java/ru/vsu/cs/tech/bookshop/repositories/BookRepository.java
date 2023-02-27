package ru.vsu.cs.tech.bookshop.repositories;

import ru.vsu.cs.tech.bookshop.models.Book;
import ru.vsu.cs.tech.bookshop.models.BookCategory;
import ru.vsu.cs.tech.bookshop.models.BookRatingComparator;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private List<Book> database;
    private long id;

    public BookRepository() {
        database = new ArrayList<>();
        id = 0;
    }

    public BookRepository(List<Book> books) {
        database = books;
        id = books.size() + 1;
    }

    public List<Book> getAllBooks() {
        return database;
    }

    public Book getBookById(long bookId) throws Exception{
        Book result = (Book) database.stream().filter(b->b.getBookId() == bookId);
        if (result.getBookId() != bookId) {
            throw new Exception("No such book with id " + id);
        } else {
            return result;
        }
    }

    public Book getBookByName(String bookName) throws Exception {
        Book result = (Book) database.stream().filter(b->b.getName().equals(bookName));
        if (!result.getName().equals(bookName)) {
            throw new Exception("No such book with name " + bookName);
        } else {
            return result;
        }
    }

    public List<Book> getBooksByCategory(BookCategory category) {
        return database.stream().filter(b->b.getCategory().equals(category)).toList();
    }

    public List<Book> getBooksByAuthor(String author) {
        return database.stream().filter(b->b.getAuthor().equals(author)).toList();
    }

    public List<Book> getBooksByPublishingHouse(String publishingHouse) {
        return database.stream().filter(b->b.getPublishingHouse().equals(publishingHouse)).toList();
    }

    public List<Book> getAvailableBooks() {
        return database.stream().filter(Book::getAvailability).toList();
    }

    public List<Book> getBooksSortedByRating() {
        return database.stream().sorted(new BookRatingComparator()).toList();
    }


    public void insert(Book newBook) {
        newBook.setBookId(id);
        database.add(newBook);
        id++;
    }

    public void update(long bookId, Book updatedBook) throws Exception{
       Book updated = getBookById(bookId);
       updated.copyInfoFromAnotherBook(updatedBook);
       database.add((int) bookId - 1, updated);
    }

    public void delete(long bookId) throws Exception{
        Book removed = getBookById(bookId);
        database.remove(removed);
    }
}
