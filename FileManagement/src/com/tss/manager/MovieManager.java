package com.tss.manager;

import com.tss.exception.CapacityFullException;
import com.tss.exception.NoSuchMovieFoundException;
import com.tss.model.Movie;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MovieManager implements Serializable {
    private static int index = 0;
    private static int size = 5;
    private List<Movie> movies;
    private static final String SERIAL_DIRECTORY_PATH = "C:\\Users\\bhavika.chhatbar\\IdeaProjects\\FileManagement\\src\\com\\tss\\utilfiles\\serializedmovies";
    private static final String CSV_PATH = "C:\\Users\\bhavika.chhatbar\\IdeaProjects\\FileManagement\\src\\com\\tss\\utilfiles\\movies.csv";
//    private static int CVSCount = 0;

    public MovieManager(List<Movie> movies) {
        this.movies = new ArrayList<>();
        loadMovies();
    }

    public MovieManager() {
        this.movies = new ArrayList<>();
    }

    public void addMovie(String title, int year, String genre) throws CapacityFullException {
        if(index == size)
            throw new CapacityFullException(index);
        Movie movie = new Movie(index++, title, year, genre);
        movies.add(movie);
    }

    public void clearMovies(){
        movies.clear();
    }

    public Movie getMovieById(int id) throws NoSuchMovieFoundException{
        if(id >= index){
            throw new NoSuchMovieFoundException(id);
        }
        return movies.get(id);
    }

    public List<Movie> getMovies() {
        return movies;
    }
    public void loadMovies() {
        try {
            File file = new File(SERIAL_DIRECTORY_PATH);
            if(file.exists()){
                FileInputStream fis = null;
                ObjectInputStream ois = null;
                for(File f: file.listFiles()){
                    fis = new FileInputStream(f.getAbsolutePath());
                    ois = new ObjectInputStream(fis);
                    Movie movie = (Movie) ois.readObject();
                    movies.add(movie);
                    System.out.println(movie.toString());
                    fis.close();
                    ois.close();
                }
            }
            index = movies.size();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void serializeMovies(){
        if(index == 0){
            return;
        }
//        serialize
        try{
            for(int i = 0; i<index; i++){
                FileOutputStream fos = new FileOutputStream(SERIAL_DIRECTORY_PATH+"\\serialMovie"+i+".ser");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(movies.get(i));
                fos.close();
                oos.close();
            }
//            System.out.println("saved");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
//        save to
    }
    public void generateCSV(){
        try {
            File file = new File(CSV_PATH);
            BufferedWriter bufferedWriter;
            if(file.exists())
                 bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            else{
                bufferedWriter = new BufferedWriter(new FileWriter(file));
                bufferedWriter.write("Id,Title,Year,Genre");
                bufferedWriter.newLine();
            }
            for (int i = 0; i<index; i++){
                bufferedWriter.write(movies.get(i).toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public void deleteAllMovies(){
        File file = new File(SERIAL_DIRECTORY_PATH);
        for(File f: Objects.requireNonNull(file.listFiles())){
//            System.out.println(f.getName());
//            System.out.println(f.delete());
            f.delete();
        }
        movies.clear();
        index = 0;
    }
}
