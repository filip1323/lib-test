package test.root.entities;

import javax.persistence.*;

/**
 * Created by Filip on 2015-07-31.
 */
@Entity
@Table(name = "book")
public class Book {
    enum Status{
        Available,
        Unavailable
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Column(name = "status", nullable = false)
    private Status status;


    protected Book() {}

    public Book(String author, String title, String ISBN, Status status) {
        this.author = author;
        this.title = title;
        this.isbn = ISBN;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", ISBN='" + isbn + '\'' +
                ", status=" + status +
                '}';
    }

}
