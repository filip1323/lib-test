package test.root.entities.jsonextension;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import test.root.entities.Book;

import javax.persistence.Column;

/**
 * Created by Filip on 2015-08-02.
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BookReturnJSON {
    private long id;
    private String author;
    private String title;
    private String isbn;
    private long timeleft;

    public BookReturnJSON(Book book, long timeleft) {
        this.id = book.getId();
        this.author = book.getAuthor();
        this.title = book.getTitle();
        this.isbn = book.getIsbn();
        this.timeleft = timeleft;
    }

    public long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public long getTimeleft() {
        return timeleft;
    }
}
