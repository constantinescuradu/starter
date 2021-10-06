package com.example.starter;


public class Book {
  public String bookName;
  public String author;

  Book(String bookName, String author){

  }
  @Override
  public String toString() {
    return "Book{" +
      "bookName='" + bookName + '\'' +
      ", author='" + author + '\'' +
      '}';
  }
}

