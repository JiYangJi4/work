package org.example.Hibernate;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Titles")
public class Title {
    @Id
    @Column(name = "isbn")
    @Getter @Setter
    private String isbn;

    @Column(name = "title")
    @Getter @Setter
    private String title;

    @Column(name = "editionNumber")
    @Getter @Setter
    private int editionNumber;

    @Column(name = "year")
    @Getter @Setter
    private String year;

    @ManyToOne
    @JoinColumn(name = "publisherID")
    @Getter @Setter
    private Publisher publisher;

    @Column(name = "price")
    @Getter @Setter
    private BigDecimal price;

}