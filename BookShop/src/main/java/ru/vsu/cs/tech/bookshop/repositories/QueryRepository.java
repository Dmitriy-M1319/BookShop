package ru.vsu.cs.tech.bookshop.repositories;

import ru.vsu.cs.tech.bookshop.models.Query;

import java.util.ArrayList;
import java.util.List;

public class QueryRepository {
    private List<Query> database;
    private long id;

    public QueryRepository() {
        database = new ArrayList<>();
        id = 1;
    }

    public QueryRepository(List<Query> orders) {
        database = orders;
        id = orders.size() + 1;
    }

    public List<Query> getAllQueries() {
        return database;
    }
    public Query getQueryById(long id) throws Exception{
        Query result = (Query) database.stream().filter(o->o.getQueryId() == id);
        if (result.getQueryId() != id) {
            throw new Exception("No such query with id " + id);
        } else {
            return result;
        }
    }

    public List<Query> getQueriesByPublishingHouse(String publishingHouse) {
        return database.stream().filter(q->q.getPublishingHouse().equals(publishingHouse)).toList();
    }

    public List<Query> getQueriesByStatus(String status) {
        return database.stream().filter(q->q.getStatus().equals(status)).toList();
    }


    public void insert(Query query) {
        query.setQueryId(id);
        database.add(query);
        id++;
    }

    public void update(long queryId, Query query) throws Exception{
        Query updated = getQueryById(queryId);
        updated.copyDataFromAnotherQuery(query);
        database.add((int)queryId - 1, updated);
    }

    public void delete(long queryId) throws Exception{
        Query deleted = getQueryById(queryId);
        database.remove(deleted);
    }
}
