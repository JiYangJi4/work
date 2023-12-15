package org.example.Hibernate;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authorISBN")
@Getter
@Setter
public class AuthorISBN  implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "authorID")
    private Author author;

    @Id
    @ManyToOne
    @JoinColumn(name = "isbn")
    private Title title;

    // Геттеры, сеттеры и другие методы
}
