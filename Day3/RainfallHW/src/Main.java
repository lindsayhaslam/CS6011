//Rainfall
//Lindsay Haslam
import java.util.Scanner;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) throws IOException {

        //ArrayList for rainfall amount in inches
        ArrayList<Double> rainfallAmount = new ArrayList<Double>();

        //Read the file in
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(new FileInputStream("/Users/lindsayhaslam/IdeaProjects/Rainfall/rainfall_data.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //Separate every line and split based on the space (or tab or new line)
        while (fileReader.hasNextLine()) {
            String[] line = fileReader.nextLine().split("\\s+");
            //If the line has more than two entries then add it to the arraylist
            if (line.length > 2) {
                rainfallAmount.add(Double.valueOf(line[2]));
            }
        }
        //get the total rainfall amount for all the data
        Double sum = 0.0;
        for (int i = 0; i < rainfallAmount.size(); i++) {
            sum += rainfallAmount.get(i);
        }

        //divide by arraylist size to get the average
        Double avg = 0.0;
        avg = sum / rainfallAmount.size();

        //format the result so it only shows two decimal places
        String totalAverageRain = new DecimalFormat("#.##").format(avg);

        // Define an array of month names
        String[] monthNames = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };

        // Define an array to store the monthly averages
        Double[] monthlyAverages = new Double[12];

        // Iterate through each month
        /*the month variable is used in the outer loop to specify which month's data you are currently processing,
        and it is used in the inner loop to index the data for that specific month across different years.
         */
        for (int month = 0; month < 12; month++) {
            //This variable is used to accumulate the total rainfall for the current month.
            Double monthlyRain = 0.0;

            // Iterate through the array for the current month (indexed by 'month')
            for (int i = month; i < rainfallAmount.size(); i += 12) {
                monthlyRain += rainfallAmount.get(i);
            }

            // Calculate the monthly average and store it in the array
            monthlyRain /= 20;
            monthlyAverages[month] = Double.valueOf(new DecimalFormat("#.##").format(monthlyRain));
        }

        //Create rainfall_results.txt
        FileWriter filewrite = new FileWriter("rainfall_results.txt");

        // Print the monthly averages with their respective month names
        for (int month = 0; month < 12; month++) {
            String sentenceAverage = "The overall average rainfall for " + monthNames[month] + " is: " + monthlyAverages[month] + "\n";
            filewrite.write(sentenceAverage);
        }
        filewrite.close();
    }
}