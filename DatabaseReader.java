import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatabaseReader {

    public static void main(String[] args) {
        HashMap<List<String>, String> database = new HashMap<List<String>, String>();
        String csvFile = "broodsky.csv";
        String line;
        String cvsSplitBy = ",";
        Pattern pattern = Pattern.compile("\"([^\"])\"|(?<=,|^)([^,])(?:,|$)");

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine(); // this will read the first line
            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    String title = matcher.group(1) != null ? matcher.group(1) : matcher.group(2);
                    String author = "Unknown";

                    if (matcher.find()) {
                        author = matcher.group(1) != null ? "Unknown" : matcher.group(2);
                    }

                    if (title.startsWith("\"") && title.endsWith("\"")) {
                        title = title.substring(1, title.length() - 1);
                        String[] titles = title.split(",");
                        for (String t : titles) {
                            if (author.length() == 0) {
                                database.put(Arrays.asList(title), "Unknown");
                                System.out.println("Title: " + title + " Author: " + "Unknown");
                            }else{
                            database.put(Arrays.asList(t.trim().split(cvsSplitBy)), author);
                            System.out.println("Title: " + t.trim().split(cvsSplitBy) + " Author: " + author);
                        }}
                    } else {
                            if (author.length() == 0) {
                                database.put(Arrays.asList(title), "Unknown");
                                System.out.println("Title: " + title + " Author: " + "Unknown");
                            }else{
                            database.put(Arrays.asList(title), author);
                            System.out.println("Title: " + title + " Author: " + author);
                        }
                    }
                    }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
