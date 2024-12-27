package org.springboot.webapp1.dao;

import org.springboot.webapp1.entities.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {

    public Book findBookById(int id);
}
