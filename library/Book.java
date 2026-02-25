package com.tss.library;

import java.util.Objects;
import java.util.Random;

public class Book {
    private Integer bookId;
    private Long ISBN;
    private String bookTitle;
    private String authorName;
    private BookCategory bookCategory;
    private Boolean isBorrowed;
    private Integer memberId; //borrowed by

    private static Integer bookIdGenerator = 1;
    private static Long ISBNGenerator = 1000000000000L;
    private static final Random random = new Random();
    private static Long LOWER_BOUND = 10L, UPPER_BOUND = 20L;

    public Book(){}
    public Book(String bookTitle, String authorName, BookCategory bookCategory) {
        this.bookTitle = bookTitle;
        this.authorName = authorName;
        this.bookCategory = bookCategory;
        isBorrowed = false;
        memberId = null;

        bookId = bookIdGenerator++;
        ISBN = ISBNGenerator*random.nextLong(LOWER_BOUND, UPPER_BOUND);
        ISBNGenerator++;
        LOWER_BOUND = UPPER_BOUND;
        UPPER_BOUND += 10L;
    }


    public void setBorrowed(Boolean borrowed) {
        isBorrowed = borrowed;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public Long getISBN() {
        return ISBN;
    }

    public String getAuthorName() {
        return authorName;
    }

    public BookCategory getBookCategory() {
        return bookCategory;
    }

    public Boolean getBorrowed() {
        return isBorrowed;
    }

    public Integer getMemberId() {
        return memberId;
    }


    @Override
    public String toString() {
        return String.format("%-10s %-20s %-40s %-20s %-20s", bookId, ISBN, bookTitle, authorName, bookCategory.getValue());
    }

    @Override
    public boolean equals(Object obj) {
        Book book = (Book) obj;
        if(bookTitle.equals(book.getBookTitle()) && bookCategory.equals(book.getBookCategory()) && authorName.equals(book.getAuthorName())){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookTitle, authorName, bookCategory);
    }

    public String getBookTitle() {
        return bookTitle;
    }
}
