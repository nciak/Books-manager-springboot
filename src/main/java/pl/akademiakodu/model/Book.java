package pl.akademiakodu.model;


import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;
import pl.akademiakodu.repository.BookRepository;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Norbert on 2017-10-14.
 */

@Entity
@Table(name = "BOOK")
public class Book{

    @Id
    @GeneratedValue
    @Column(name="ID")
    private Integer id;

    @Column(name="AUTHOR")
    @NotNull
    @NotEmpty(message = "Cannot be empty")
    private String author;

    @Column(name="TITLE")
    @NotNull
    @NotEmpty(message = "Cannot be empty")
    @Size(min = 5, message = "Minimum 5 characters")
    private String title;

    @Column(name="PRICE")
    @Digits(integer = 4, fraction = 2, message = "Maximum 4 digits, and 2 after decimal")
    private double price;

    public Book(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }

}
