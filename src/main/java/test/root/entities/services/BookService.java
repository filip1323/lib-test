package test.root.entities.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import test.root.entities.Book;
import test.root.entities.BookRepository;
import test.root.entities.User;
import test.root.entities.UserRepository;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Filip on 2015-08-01.
 */
@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Collection<Book> getAllBooks() {
        //TODO encapsulate
        Collection<Book> list = new ArrayList<Book>();
        for (Book item : bookRepository.findAll()) {
            list.add(item);
        }
        return list;
    }

    public Collection<Book> getAvailableBooks() {
        //TODO encapsulate
        Collection<Book> list = new ArrayList<Book>();
        for (Book item : bookRepository.findAvailableBooks()) {
            list.add(item);
        }
        return list;
    }
    public Collection<Book> getBorrowedBooks(long id) {
        //TODO encapsulate
        Collection<Book> list = new ArrayList<Book>();
        for (Book item : bookRepository.findUnavailableBooks(id)) {
            list.add(item);
        }
        return list;
    }


    public Book getBookById(long id) {
        return bookRepository.findOne(id);
    }

    public Book getBookByLogin(String isbn) {
        return bookRepository.findOneByIsbn(isbn);
    }

    public Book create(String author, String title, String ISBN) {
        return bookRepository.save(new Book(author, title, ISBN));
    }

    public Book update(Book newBook){
        Book oldBook = getBookById(newBook.getId());
        oldBook.setAuthor(newBook.getAuthor());
        oldBook.setIsbn(newBook.getIsbn());
        oldBook.setTitle(newBook.getTitle());
        return bookRepository.save(oldBook);
    }

    public void delete(Book book){
        bookRepository.delete(book);
    }

}
