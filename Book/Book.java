package Book;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Book {
    private String title;
    private String author;
    private double averageRating;
    private List<String> userReviews;
    private Map<String, Integer> userRatings;
    private boolean addedToPersonal;
    public Book() {
        this.title = "Unknown";
        this.author = "Unknown";
        this.averageRating = -1.0;
        this.userReviews = new ArrayList<>();
        this.userRatings = new HashMap<>();
        this.addedToPersonal = false;
    }
    public Book(String title, String author) {
        this.title = (title != null && !title.isEmpty()) ? title : "Unknown";
        this.author = (author != null && !author.isEmpty()) ? author : "Unknown";
        this.averageRating = -1.0;
        this.userReviews = new ArrayList<>();
        this.userRatings = new HashMap<>();
    }

    public String getTitle(){
        return title;
    }

    public String getAuthor(){
        return author;
    }

    public double getAverageRating(){
        return averageRating;
    }

    public void setAverageRating(double averageRating){
        this.averageRating = averageRating;
    }

    public List<String> getUserReviews(){
        return userReviews;
    }

    public Map<String, Integer> getUserRatings() {
        return userRatings;
    }

    public void addUserReview(String username, int rating){
        userRatings.put(username, rating);
    }

    public void addUserRating(String username, int rating){
        userRatings.put(username, rating);
    }

    public boolean hasNoRatings(){
        return userRatings.isEmpty();
    }

    public boolean hasNoReviews(){
        return userReviews.isEmpty();
    }

    public boolean isAddedToPersonal() {
        return addedToPersonal;
    }

    public void togglePersonalStatus() {
        addedToPersonal =! addedToPersonal;
    }

    public Object getStatus() {
        throw new UnsupportedOperationException("Unimplemented method 'getStatus'");
    }

    public int getTimeSpent() {
        throw new UnsupportedOperationException("Unimplemented method 'getTimeSpent'");
    }

    public Object getStartDate() {
        throw new UnsupportedOperationException("Unimplemented method 'getStartDate'");
    }

    public Object getEndDate() {
        throw new UnsupportedOperationException("Unimplemented method 'getEndDate'");
    }

    public Object getRating() {
        throw new UnsupportedOperationException("Unimplemented method 'getRating'");
    }

    public Object getReviews() {
        throw new UnsupportedOperationException("Unimplemented method 'getReviews'");
    }

    @Override
    public String toString() {
        return "Title: " + getTitle() + " Author: " + getAuthor() + "\n";
    }
}