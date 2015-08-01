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

    @Column(name = "userId", nullable = false)
    private long userId;

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public long getBookId() {
        return bookId;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    @Column(name = "bookId", nullable = false)
    private long bookId;

    @Column(name = "startTime", nullable = false)
    private long startTime;

    @Column(name = "endTime", nullable = false)
    private long endTime;

    protected Service(){}

    public Service(long userId, long bookId, long startTime, long endTime) {
        this.userId = userId;
        this.bookId = bookId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", userId=" + userId +
                ", bookId=" + bookId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
