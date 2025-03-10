import java.util.List;
import java.util.Scanner;

public class MovieRecommendationApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MovieManager manager = new MovieManager();

        // Load movies
        String filePath = "src/movies.txt"; // Ensure this path is correct
        manager.loadMoviesFromFile(filePath);

        System.out.println("\nWould you like to (1) Get recommendations or (2) Add a new movie?");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (choice == 1) {
            // Get user input for recommendations
            System.out.print("Enter preferred genre: ");
            String genre = scanner.nextLine();

            System.out.print("Enter minimum release year: ");
            int year = scanner.nextInt();

            System.out.print("Enter minimum rating (1-10): ");
            double rating = scanner.nextDouble();

            // Get recommendations
            List<Movie> recommendations = manager.recommendMovies(genre, year, rating);

            // Display results
            if (recommendations.isEmpty()) {
                System.out.println("No recommendations found.");
            } else {
                System.out.println("\nRecommended Movies:");
                for (Movie movie : recommendations) {
                    System.out.println(movie);
                }
            }
        } else if (choice == 2) {
            // User wants to add a movie
            System.out.print("Enter movie title: ");
            String title = scanner.nextLine();

            System.out.print("Enter movie genre: ");
            String genre = scanner.nextLine();

            System.out.print("Enter release year: ");
            int year = scanner.nextInt();

            System.out.print("Enter rating (1-10): ");
            double rating = scanner.nextDouble();

            // Create new movie and add to file
            Movie newMovie = new Movie(title, genre, year, rating);
            manager.addMovieToFile(filePath, newMovie);
        } else {
            System.out.println("Invalid choice. Exiting program.");
        }

        scanner.close();
    }
}
