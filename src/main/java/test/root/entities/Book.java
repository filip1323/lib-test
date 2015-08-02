package test.root.entities;

import javax.persistence.*;

/**
 * Created by Filip on 2015-07-31.
 */
@Entity
@Table(name = "book")
public class Book {
    public enum Status{
        AVAILABLE,
        UNAVAILABLE
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
    @Enumerated(EnumType.STRING)
    private Status status;

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

    public Status getStatus() {
        return status;
    }

    public String getTimeleft(){return null;}//TODO timeleft book

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

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
