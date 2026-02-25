package com.tss.exception;

public class NoSuchMovieFoundException extends Exception{
    int movieId;

    public NoSuchMovieFoundException(int movieId){
        this.movieId = movieId;
    }

    @Override
    public String getMessage() {
        return "NoSuchMovieFoundException: No movie with id " + movieId;
    }
}
