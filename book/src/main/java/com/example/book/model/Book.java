package com.example.book.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
public class Book {
    public Book() {

    }

    public Book(int id, int year, String genre, String publisher, String author, String title) {
        this.id = id;
        this.year = year;
        this.genre = genre;
        this.publisher = publisher;
        this.author = author;
        this.title = title;
    }

    @Id
    @GeneratedValue
    private int id;
    @NotNull
    private String title;
    @NotNull
    private String author;
    @NotNull
    private String publisher;
    @NotNull
    private String genre;
    @NotNull
    @Min(value = 1000)
    private int year;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", genre='" + genre + '\'' +
                ", year=" + year +
                '}';
    }
}
