package Database;

import java.util.ArrayList;
import java.util.List;

import Book.Book;

public class PersonalBook extends Book {
    private List<String> statuses;
    private List<Integer> timeSpents;
    private List<String> startDates;
    private List<String> endDates;
    private List<Double> userRatings;
    private List<String> userReviews;
    
    public PersonalBook(String title, String author, double rating, int reviews) {
        super(title, author);
        this.statuses = new ArrayList<>();
        this.timeSpents = new ArrayList<>();
        this.startDates = new ArrayList<>();
        this.endDates = new ArrayList<>();
        this.userRatings = new ArrayList<>();
        this.userReviews = new ArrayList<>();
    }
    public List<String> getStatuses() {
        return statuses;
    }

    public List<Integer> getTimeSpents() {
        return timeSpents;
    }

    public List<String> getStartDates() {
        return startDates;
    }

    public List<String> getEndDates() {
        return endDates;
    }
    public void addEntry(String status, int timeSpent, String startDate, String endDate) {
        statuses.add(status);
        timeSpents.add(timeSpent);
        startDates.add(startDate);
        endDates.add(endDate);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\nEntries:\n");
        for (int i = 0; i < statuses.size(); i++) {
            sb.append("Status: ").append(statuses.get(i))
              .append(", Time Spent: ").append(timeSpents.get(i))
              .append(", Start Date: ").append(startDates.get(i))
              .append(", End Date: ").append(endDates.get(i))
              .append(", User Rating: ").append(userRatings.get(i));
            if (i < userReviews.size()) sb.append(", User Review: ").append(userReviews.get(i));
            sb.append("\n");
        }
        return sb.toString();
    }
}