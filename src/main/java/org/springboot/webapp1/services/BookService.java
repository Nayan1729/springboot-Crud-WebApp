package org.springboot.webapp1.services;

import org.springboot.webapp1.dao.BookRepository;
import org.springboot.webapp1.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
//    private static List<Book> books = new ArrayList<Book>();
//
//    static {
//        books.add(new Book(101, "Nayan", "Psychology of Money"));
//        books.add(new Book(102, "Nayan", "Psychology of Money"));
//        books.add(new Book(103, "Nayan", "Psychology of Money"));
//    }
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        books = (List<Book>) this.bookRepository.findAll();
        return books;
    }

    public Book getBookById(int id) {
        Book book = this.bookRepository.findBookById(id);
        return book;
    }

    public Book addBook(Book book){
        Book b =  this.bookRepository.save(book);
        return b;
    }

    public int deleteBook(int id) {
        // Here we need to use collect as .stream gives a new modified list and doesn't change the
        // original list
        this.bookRepository.deleteById(id);
        return id;
    }

    public Book updateBook(int bookId, Book book) {
        Book b = this.bookRepository.findBookById(bookId);
        b.setId(bookId);
        b.setTitle(book.getTitle());
        b.setAuthor(book.getAuthor());
        Book resultantBook = this.bookRepository.save(b);
        return resultantBook;
    }
}
