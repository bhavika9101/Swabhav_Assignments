package com.tss.library;

import com.tss.DataValidator;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;

public class Library {
    static LinkedHashSet<Book> bookSet = new LinkedHashSet<>();
    static List<Member> memberList = new ArrayList<>();

    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        while (true){
            int choice = displayMenu();
            if(choice != 1 && choice != 0 && choice != 2 && choice != 10 && bookSet.isEmpty()){
                System.out.println("Add a book first.");
                choice = 1;
            }
            else if(choice != 2 && choice != 0 && choice != 5 && choice != 6 && choice!= 9 && choice != 1 && memberList.isEmpty()){
                System.out.println("Add a member first.");
                choice = 2;
            }
            switch (choice){
                case 1:{
                    System.out.println("ADD A BOOK");
                    Book book = createBook();
                    if(!bookSet.add(book)){
                        System.out.println("Attempt to add duplicate book.");
                    }else {
                        System.out.println("Book added. Id: " + book.getBookId());
                    }
                    break;
                }
                case 2:{
                    System.out.println("ADD A MEMBER");
                    try {
                        Member member = createMember();
                        memberList.add(member);
                        System.out.println("Member added. Id: " + member.getMemberId());
                    }catch (EntityInformationInvalidException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 3:{
                    if(!borrowBook())
                        System.out.println("Book borrowing failed.");
                    break;
                }
                case 4:{
                    if(!returnBook()){
                        System.out.println("Book return failed.");
                    }
                    break;
                }
                case 5:{
                    showBooks();
                    break;
                }
                case 6:{
                    showAvailableBooks();
                    break;
                }
                case 7:{
                    showBorrowedBooks();
                    break;
                }
                case 8:{
                    showBooksBorrowedByAMember();
                    break;
                }
                case 9:{
                    showABook();
                    break;
                }
                case 10:{
                    showMembers();
                    break;
                }
                case 0:{
                    System.out.println("LEAVING LIBRARY. RETURN BOOK WHEN YOU COME BACK.");
                    return;
                }
                default:{
                    System.out.println("Invalid choice.");
                    break;
                }
            }
        }
    }

    public static int displayMenu(){
        System.out.println("""
                __MENU__
                1. ADD BOOK TO RECORDS
                2. ADD MEMBER TO RECORDS
                3. BORROW BOOK
                4. RETURN BOOK
                5. SHOW ALL BOOKS
                6. SHOW AVAILABLE BOOKS
                7. SHOW BORROWED BOOKS
                8. SHOW BOOKS BORROWED BY A MEMBER
                9. SHOW A BOOK
                10. SHOW ALL MEMBERS
                0. EXIT
                ENTER YOUR CHOICE:""");
        return DataValidator.validateInt();
    }
    public static Book createBook(){
        System.out.println("Enter book title: ");
        String title = scanner.nextLine().trim();
        System.out.println("Enter author name:");
        String name =  DataValidator.validateUserName();
        BookCategory bookCategory = fetchBookCategory();
        return new Book(title, name, bookCategory);
    }

    public static BookCategory fetchBookCategory(){
        BookCategory[] categories= BookCategory.values();
        System.out.println("CATEGORIES: ");
        for (int i = 0; i < categories.length; i++) {
            System.out.println((i+1) + ". " + categories[i]);
        }
        System.out.println("Enter category: ");
        int choice = DataValidator.validateCategoryChoice();
        return categories[choice-1];
    }
    public static Member createMember() throws EntityInformationInvalidException{
        System.out.println("Enter member name: ");
        String name = DataValidator.validateUserName();
        System.out.println("Enter email id: ");
        String email = DataValidator.validateEmailId();
        return new Member(name, email);
    }
    public static boolean borrowBook(){
        if(bookSet.isEmpty()){
            System.out.println("No books in library.");
            return false;
        }
        Book book = findBookById();
        if(book == null)
            return false;
        if(book.getBorrowed()){
            System.out.println("Book already borrowed.");
            return false;
        }
        Member member = findMemberById();
        if(member == null){
            return false;
        }
        book.setBorrowed(true);
        book.setMemberId(member.getMemberId());
        member.addBook(book);
        System.out.println(book.getBookTitle() + " borrowed by " + member.getMemberName());
        return true;
    }
    public static boolean returnBook(){
        Member member = findMemberById();
        if(member == null){
            return false;
        }
        if(member.getBookList().isEmpty()){
            System.out.println("You have not borrowed any book.");
            return false;
        }
        System.out.println("YOUR BOOKS");
        System.out.printf("%-10s %-20s %-40s %-20s %-20s\n", "Book Id", "ISBN", "Book Title", "Author Name", "Category");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        for(Book book: member.getBookList()){
            System.out.println(book.toString());
        }
        Book book = findBookById();
        if(book == null || !book.getBorrowed() || !book.getMemberId().equals(member.getMemberId())){
            System.out.println("Book is not borrowed by you.");
            return false;
        }
        book.setMemberId(null);
        book.setBorrowed(false);
        member.removeBook(book);
        System.out.println(book.getBookTitle() + " returned by " + member.getMemberName());
        return true;
    }
    public static Book findBookById(){
        System.out.println("Enter book id: ");
        int id = DataValidator.validateInt();
        for(Book book: bookSet){
            if(book.getBookId() == id)
                return book;
        }
        System.out.println("No such book on records.");
        return null;
    }
    public static Member findMemberById(){
        System.out.println("Enter member id: ");
        int id = DataValidator.validateInt();
        for(Member member: memberList){
            if(member.getMemberId() == id)
                return member;
        }
        System.out.println("No such member on records.");
        return null;
    }
    public static void showBooks(){
        if(bookSet.isEmpty()){
            System.out.println("No books in library.");
            return;
        }
        System.out.printf("%-10s %-20s %-40s %-20s %-20s\n", "Book Id", "ISBN", "Book Title", "Author Name", "Category");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        for(Book book: bookSet){
            System.out.println(book);
        }
    }
    public static void showAvailableBooks(){
        boolean flag = false;
        System.out.printf("%-10s %-20s %-40s %-20s %-20s\n", "Book Id", "ISBN", "Book Title", "Author Name", "Category");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        for(Book book: bookSet){
            if(!book.getBorrowed()){
                System.out.println(book);
                flag = true;
            }
        }
        if(!flag){
            System.out.println("No books are available.");
        }
    }
    public static void showBorrowedBooks(){
        boolean flag = false;
        System.out.printf("%-10s %-20s %-40s %-20s %-20s\n", "Book Id", "ISBN", "Book Title", "Author Name", "Category");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        for(Book book: bookSet){
            if(book.getBorrowed()) {
                System.out.println(book);
                flag = true;
            }
        }
        if(!flag){
            System.out.println("No books are borrowed.");
        }
    }
    public static void showBooksBorrowedByAMember(){
        Member member = findMemberById();
        if(member == null)
            return;
        System.out.printf("%-10s %-20s %-30s %-20s\n", "Member Id", "Member Name", "E-mail Id", "Membership expiry");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        System.out.println(member);
        if(member.getBookList().isEmpty()){
            System.out.println("No books borrowed by this member.");
            return;
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-20s %-40s %-20s %-20s\n", "Book Id", "ISBN", "Book Title", "Author Name", "Category");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        for(Book book: member.getBookList()){
            System.out.println(book);
        }
    }
    public static void showABook(){
        Book book = findBookById();
        if(book == null)
            return;
        System.out.printf("%-10s %-20s %-40s %-20s %-20s\n", "Book Id", "ISBN", "Book Title", "Author Name", "Category");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        System.out.println(book);
    }
    public static void showMembers(){
        if(memberList.isEmpty()){
            System.out.println("No members to show.");
            return;
        }
        System.out.printf("%-10s %-20s %-30s %-20s\n", "Member Id", "Member Name", "E-mail Id", "Membership expiry");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        for(Member member: memberList){
            System.out.println(member);
        }
    }
}
