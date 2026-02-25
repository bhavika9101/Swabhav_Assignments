package com.tss;

import com.tss.controller.MovieController;
import com.tss.exception.CapacityFullException;
import com.tss.exception.NoSuchMovieFoundException;

import java.util.Scanner;

public class MovieMain {
//    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        MovieController movieController = new MovieController();
        try {
            movieController.start();
        }catch (NoSuchMovieFoundException e){
            System.out.println(e.getMessage());
        }catch (CapacityFullException e ) {
            System.out.println(e.getMessage());
        }
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            movieController.wrapUp();
        }));
    }
}
