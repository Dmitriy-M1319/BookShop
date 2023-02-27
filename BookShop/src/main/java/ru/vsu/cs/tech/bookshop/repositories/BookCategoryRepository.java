package ru.vsu.cs.tech.bookshop.repositories;

import ru.vsu.cs.tech.bookshop.models.BookCategory;

import java.util.ArrayList;
import java.util.List;

public class BookCategoryRepository {
    private List<BookCategory> database;
    private long id;

    public BookCategoryRepository(List<BookCategory> categories) {
       database = categories;
       id = categories.size() + 1;
    }

    public BookCategoryRepository() {
        database = new ArrayList<>();
        id = 1;
    }

    public List<BookCategory> getAllBookCategories() {
        return database;
    }

    public BookCategory getCategoryById(long id) throws Exception {
        BookCategory result = (BookCategory) database.stream().filter(c->c.getCategoryId() == id);
        if(result.getCategoryId() != id) {
            throw new Exception("No such category with id " + id);
        } else {
            return result;
        }
    }

    public BookCategory getCategoryByName(String name) throws Exception {
        BookCategory result = (BookCategory) database.stream().filter(c->c.getName().equals(name));
        if(!result.getName().equals(name)) {
            throw new Exception("No such category with name " + name);
        } else {
            return result;
        }
    }

    public void insert(BookCategory category) {
        category.setCategoryId(id);
        database.add(category);
        id++;
    }

    public void update(long categoryId, BookCategory category) throws Exception{
        BookCategory updatingCategory = getCategoryById(categoryId);
        updatingCategory = category;
        database.add((int)categoryId - 1, updatingCategory);
    }

    public void delete(long categoryId) throws Exception{
        BookCategory deletingCategory = getCategoryById(categoryId);
        database.remove(deletingCategory);
    }
}
