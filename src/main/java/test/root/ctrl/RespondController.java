package test.root.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import test.root.entities.Book;
import test.root.entities.User;
import test.root.entities.services.BookService;
import test.root.entities.services.ServiceService;
import test.root.entities.services.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;


/**
 * Created by Filip on 2015-07-31.
 */
@RestController
public class RespondController {


    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @Autowired
    ServiceService serviceService;

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @RequestMapping(value = "/rest/get-logged-user", method = RequestMethod.POST)
    public User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        if (name == null) return null;
        return userService.getUserByLogin(name);
    }

    //ADD EDIT REMOVE BOOK//

    @RequestMapping(value = "/rest/get-books", method = RequestMethod.POST) Collection<Book> getBooks(){
        return bookService.getAllBooks();
    }
    //TODO
    @RequestMapping(value = "/rest/get-book", method = RequestMethod.POST) Collection<Book> getBook(){
        return bookService.getAllBooks();
    }
    //TODO DELETE
    @RequestMapping(value = "/rest/get-users", method = RequestMethod.POST) Collection<User> getUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/rest/add-book", method = RequestMethod.POST)
    public long addBook(@RequestBody Book book) {
        return bookService.create(book.getAuthor(), book.getTitle(), book.getIsbn(), Book.Status.AVAILABLE).getId();
    }

    @RequestMapping(value = "/rest/update-book", method = RequestMethod.POST)
    public Book updateBook(@RequestBody Book book) {
        return bookService.update(book);
    }

    @RequestMapping(value = "/rest/remove-book/{bookId}", method = RequestMethod.GET)
    public void removeBook(@PathVariable int bookId) {
        bookService.delete(bookService.getBookById(bookId));
    }

    //!ADD EDIT REMOVE BOOK//

    //SERVICE BOOK//

    @RequestMapping(value = "/rest/get-available-books", method = RequestMethod.POST) Collection<Book> getAvailableBooks(){
        return bookService.getAvailableBooks();
    }

    @RequestMapping(value = "/rest/borrow-book", method = RequestMethod.POST)
    public void borrowBook(@RequestBody Book book) {
        serviceService.create(getUser().getId(),book.getId(), 0, 0);
    }


    //!SERVICE BOOK//

    //RETURN BOOK//

    @RequestMapping(value = "/rest/get-borrowed-books", method = RequestMethod.GET) Collection<Book> getBorrowedBooks(){
        return bookService.getBorrowedBooks(getUser().getId());
//        return new ArrayList<Book>();
    }

    @RequestMapping(value = "/rest/return-book", method = RequestMethod.POST)
    public void returnBook(@RequestBody Book book) {
        serviceService.returnBook(bookService.getBookById(book.getId()));
    }
}
