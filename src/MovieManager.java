import java.io.*;
import java.util.*;

public class MovieManager {
    private List<Movie> movieList = new ArrayList<>();

    public void loadMoviesFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String title = parts[0];
                    String genre = parts[1];
                    int year = Integer.parseInt(parts[2]);
                    double rating = Double.parseDouble(parts[3]);
                    movieList.add(new Movie(title, genre, year, rating));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading movie data: " + e.getMessage());
        }
    }

    public List<Movie> recommendMovies(String genre, int minYear, double minRating) {
        List<Movie> recommendations = new ArrayList<>();
        for (Movie movie : movieList) {
            if (movie.getGenre().equalsIgnoreCase(genre) && movie.getReleaseYear() >= minYear && movie.getRating() >= minRating) {
                recommendations.add(movie);
            }
        }
        return recommendations;
    }

    public void addMovieToFile(String filename, Movie newMovie) {
        try (FileWriter fw = new FileWriter(filename, true); // 'true' appends to the file
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {

            pw.println(newMovie.getTitle() + "," + newMovie.getGenre() + "," + newMovie.getReleaseYear() + "," + newMovie.getRating());
            System.out.println("Movie added successfully!");

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

}
