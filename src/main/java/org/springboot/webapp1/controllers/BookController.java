package org.springboot.webapp1.controllers;

import org.springboot.webapp1.entities.Book;
import org.springboot.webapp1.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;


    // Get Mappings


    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> books = this.bookService.getBooks();
        if(books.isEmpty()) {
            return ResponseEntity.of(Optional.of("\"No books found")).status(HttpStatus.NO_CONTENT).build();
        }
         return ResponseEntity.of(Optional.of(books)) ;
    }

    @GetMapping("/books/{id}")
    public  ResponseEntity<Book> getBookById( @PathVariable("id") int id) {
        try{
            Book book = this.bookService.getBookById(id);
            if(book == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.status(HttpStatus.OK).body(book);
        }
        catch(Exception e) {
            int status = e.hashCode();
            System.out.println(status);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Post mapping

    @PostMapping("/books")
    public void addBook( @RequestBody Book book) {
        this.bookService.addBook(book);
    }

    // Delete Mapping

    @DeleteMapping("/books/{bookId}")
    public int deleteBook(@PathVariable("bookId") int id ){
        return this.bookService.deleteBook(id);
    }

    // Update
    // Put Mapping

    @PutMapping("/books/{bookId}")
    public Book updateBook( @RequestBody Book book , @PathVariable("bookId") int bookId) {
        return this.bookService.updateBook(bookId,book);
    }


}
