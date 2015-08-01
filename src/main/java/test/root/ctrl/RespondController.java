package test.root.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.root.entities.Book;
import test.root.entities.BookRepository;

import java.security.Principal;


/**
 * Created by Filip on 2015-07-31.
 */
@RestController
public class RespondController {


    @Autowired
    BookRepository bookRepository;

    @RequestMapping("/user")
    public Principal user(Principal user) {
        System.out.println("E: " + user);
        return user;
    }

    @RequestMapping(value = "/get/books", method = RequestMethod.POST)
    @ResponseBody Iterable<Book> getBooks() {
        return bookRepository.findAll();
    }

    @RequestMapping(value = "/add/book", method = RequestMethod.POST)
    public void addBook(@RequestParam(value = "book") Book book) {
        //add book
    }

    @RequestMapping(value = "/remove/book", method = RequestMethod.POST)
    public void removeBook(@RequestParam(value = "bookId") String bookId) {
        //remove book
    }
}
