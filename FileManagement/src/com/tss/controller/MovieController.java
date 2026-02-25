package com.tss.controller;

import com.tss.exception.CapacityFullException;
import com.tss.exception.NoSuchMovieFoundException;
import com.tss.manager.MovieManager;
import com.tss.model.Movie;

import java.util.List;
import java.util.Scanner;

public class MovieController {
    private MovieManager manager;
    Scanner scanner = new Scanner(System.in);

    public MovieController() {
        manager = new MovieManager();
    }

    public void start() throws NoSuchMovieFoundException, CapacityFullException{
        manager.loadMovies();
        displayMenu();
    }

    private void displayMenu() throws NoSuchMovieFoundException, CapacityFullException{

        while (true){
            System.out.println("Menu");
            System.out.println("1. Display all movies");
            System.out.println("2. Display a movie");
            System.out.println("3. Add a movie");
            System.out.println("4. Update a movie");
            System.out.println("5. Delete all movies from disk");
            System.out.println("6. Clear all unsaved movies");
            System.out.println("7. Generate a .csv file");
            System.out.println("8. Save movies to disk");
            System.out.println("0. Exit");
            int ch = scanner.nextInt();
            scanner.nextLine();
            switch (ch){
                case 1:{
                    System.out.println("ALL MOVIES");
                    List<Movie> movies = manager.getMovies();
                    for(Movie m: movies){
                        System.out.println(m.toString());
                    }
                    break;
                }
                case 2:{
                    System.out.println("MOVIE");
                    System.out.println("Enter movie id: ");
                    try {
                        System.out.println(manager.getMovieById(scanner.nextInt()).toString());
                    }catch (NoSuchMovieFoundException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 3:{
                    System.out.println("ADD MOVIE");
                    System.out.println("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.println("Enter year: ");
                    int year = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter genre: ");
                    String genre = scanner.nextLine();
                    manager.addMovie(title, year, genre);
                    break;
                }
                case 4:{
                    System.out.println("UPDATE MOVIE");
                    updateMovieDetails();
                    break;
                }
                case 5:{
                    System.out.println("DELETE ALL MOVIES");
                    manager.deleteAllMovies();
                    break;
                }
                case 6:{
                    System.out.println("CLEAR UNSAVED MOVIES");
                    manager.clearMovies();
                    break;
                }
                case 7:{
                    manager.generateCSV();
                    break;
                }
                case 8:{
                    manager.serializeMovies();
                    break;
                }
                case 0:{
                    return;
                }
                default:{
                    break;
                }
            }
        }
    }
//set movie details
    public boolean updateMovieDetails(){
        System.out.println("Enter movie id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        try{
            Movie updateMovie = manager.getMovieById(id);
            System.out.println("Change movie title? (y/n): ");
            char choice = scanner.next().charAt(0);
            scanner.nextLine();
            if(choice == 'y'){
                System.out.println("Enter new title: ");
                String title = scanner.nextLine();
                manager.getMovieById(id);
                updateMovie.setName(title);
            }
            System.out.println("Change movie year? (y/n): ");
            choice = scanner.next().charAt(0);
            scanner.nextLine();
            if(choice == 'y'){
                System.out.println("Enter new title: ");
                int year = scanner.nextInt();
                scanner.nextLine();
                manager.getMovieById(id);
                updateMovie.setYear(year);
            }
            System.out.println("Change movie genre? (y/n): ");
            choice = scanner.next().charAt(0);
            scanner.nextLine();
            if(choice == 'y'){
                System.out.println("Enter new genre: ");
                String genre = scanner.nextLine();
                manager.getMovieById(id);
                updateMovie.setGenre(genre);
            }
            System.out.println(updateMovie.toString());
        }catch (NoSuchMovieFoundException e){
            System.out.println(e.getMessage());
        }
        return true;
    }
    public void wrapUp(){
        manager.serializeMovies();
    }
}
