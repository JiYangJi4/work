package org.example.Hibernate;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "Authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authorID")
    @Getter @Setter
    private int authorID;

    @Column(name = "firstName")
    @Getter @Setter
    private String firstName;

    @Column(name = "lastName")
    @Getter @Setter
    private String lastName;

    // Геттеры, сеттеры и другие методы
}
