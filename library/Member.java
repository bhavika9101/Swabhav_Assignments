package com.tss.library;

import javax.swing.text.DateFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Member {
    private final Integer memberId;
    private final String memberName;
    private final String emailId;
    private final String memberSince;
    private String memberTill;
    List<Book> bookList;

    static Set<String> emailIdSet = new HashSet<>();

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final Integer memberIdGenerator = 100;
    private static final Random random = new Random();
    private static Integer LOWER_BOUND = 1, UPPER_BOUND = 10;

    public Member(String memberName, String emailId) throws EntityInformationInvalidException{
        this.memberName = memberName;
        if(!emailIdSet.add(emailId)){
            throw new EntityInformationInvalidException("Duplicate email id for member.");
        }
        this.emailId = emailId;
        emailIdSet.add(this.emailId.toLowerCase());

        LocalDateTime localDateTime = LocalDateTime.now();
        this.memberSince = localDateTime.format(formatter);
        this.memberTill = localDateTime.plusYears(6).format(formatter);

        this.memberId = memberIdGenerator*random.nextInt(LOWER_BOUND, UPPER_BOUND);
        LOWER_BOUND = UPPER_BOUND;
        UPPER_BOUND +=10;

        this.bookList = new ArrayList<>();
    }

    public Integer getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getMemberSince() {
        return memberSince;
    }

    public String getMemberTill() {
        return memberTill;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void addBook(Book book){
        this.bookList.add(book);
    }
    public void removeBook(Book book){
        this.bookList.remove(book);
    }

    @Override
    public String toString() {
        return String.format("%-10s %-20s %-30s %-20s", memberId, memberName, emailId, memberTill);
    }
}
