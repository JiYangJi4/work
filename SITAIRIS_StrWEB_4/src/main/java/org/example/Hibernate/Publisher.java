package org.example.Hibernate;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
@Entity
@Table(name = "Publishers")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisherID")
    @Getter @Setter
    private int publisherID;

    @Column(name = "publisherName")
    @Getter @Setter
    private String publisherName;

    public Publisher(String publisherName) {
        this.publisherName = publisherName;
    }

    public Publisher() {
        this.publisherName = null;
        this.publisherID = 0;
    }

    public String getName() {
        return this.publisherName;
    }

    public void setName(String publisherName) {
        this.publisherName = publisherName;
    }

    public void setId(int i) {
        this.publisherID = i;
    }

    // Геттеры, сеттеры и другие методы
}
