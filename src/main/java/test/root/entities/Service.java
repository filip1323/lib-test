package test.root.entities;

import javax.persistence.*;

/**
 * Created by Filip on 2015-07-31.
 */
@Entity
@Table(name = "service")
public class Service {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @Column(name = "start_time", nullable = false)
    private long startTime;

    @Column(name = "end_time", nullable = false)
    private long endTime;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="book_id")
    private Book book;

    public Service(long startTime, long endTime, User user, Book book) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.user = user;
        this.book = book;
    }

    public long getId() {
        return id;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    protected Service(){}

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", user=" + user +
                ", book=" + book +
                '}';
    }
}
