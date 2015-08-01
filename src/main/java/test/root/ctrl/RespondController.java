package test.root.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import test.root.entities.Book;
import test.root.entities.User;
import test.root.entities.services.BookService;
import test.root.entities.services.UserService;

import java.security.Principal;
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

    @RequestMapping(value = "/rest/get-books", method = RequestMethod.POST) Collection<Book> getBooks(){
        return bookService.getAllBooks();
    }
    //TODO DELETE
    @RequestMapping(value = "/rest/get-users", method = RequestMethod.POST) Collection<User> getUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/rest/add-book", method = RequestMethod.POST)
    public void addBook(@RequestBody Book bookForm) {
        bookService.create(bookForm.getAuthor(), bookForm.getTitle(), bookForm.getIsbn(), Book.Status.AVAILABLE);
    }

    @RequestMapping(value = "/rest/remove-book/{bookId}", method = RequestMethod.GET)
    public void removeBook(@PathVariable int bookId) {
        bookService.delete(bookService.getBookById(bookId));
    }
}
