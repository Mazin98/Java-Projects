import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Analysis {

    
    public static void displayHistogram(ArrayList < Double > ratings) {
        int nBooks;
        double r;
        System.out.println("Histogram of Amazon Bestseller Ratings");
        System.out.println("--------------------------------------");

        // loop from ratings minimum-0.1 to maximum+0.1 in steps of 0.1
        for (r = ratings.get(0) - 0.1; r <= ratings.get(ratings.size() - 1) + 0.1; r = r + 0.1) {
            nBooks = 0;
            System.out.printf("%.1f ", r); // display the current rating

            // loop to count the number of books that have the same rating as r
            for (int i = 0; i < ratings.size(); i++) {
                // compare r with ith rating
                
                if (Math.abs(ratings.get(i) - r) <= 0.0001){
                    nBooks++;
                }
                else if (ratings.get(i) > r) {
                    break;
                }
            }

            // display * corresponding to number of books having rating r
            for (int i = 0; i < nBooks; i++){
                System.out.print("*");
            }
            System.out.println();
        }

        System.out.println("--------------------------------------");
    }


    public static double median(ArrayList < Double > ratings) {
        if (ratings.size() > 0) // validate size of list > 0
        {
            if (ratings.size() % 2 == 0) // even number of ratings
            {
                // median is the average of middle 2 ratings
                return (ratings.get((int) ratings.size() / 2) + ratings.get((int)(ratings.size() / 2) - 1)) / 2;
            } else // odd number of ratings
                return (ratings.get((int)(ratings.size() - 1) / 2)); // median is the middle value
        }

        return 0;
    }

   
    public static double average(ArrayList < Double > ratings) {
        double totalRatings = 0; // initialize totalRatings to 0

        // loop over the list of ratings, adding each rating to totalRatings
        for (int i = 0; i < ratings.size(); i++){
            totalRatings += ratings.get(i);
        }
        
        //loop to ensure there is input
        if (ratings.size() > 0) {
            return totalRatings / ratings.size(); 
            }// compute average by dividing totalRating by number of ratings
            
        //list is empty
        return 0; 
    }

    
    public static double standardDeviation(ArrayList < Double > ratings) {
       
       // get the average of all ratings
        double avg = average(ratings); 
        
        // initailize the total of squared difference to 0
        double stdDev = 0; 

        // loop to calculate sum of square difference of each rating from mean
        for (int i = 0; i < ratings.size(); i++) {
            stdDev += Math.pow(ratings.get(i) - avg, 2);
        }

        if (ratings.size() > 0) // list is not empty
            return Math.sqrt(stdDev / ratings.size());

        return 0; // list is empty
    }
     
     
    //main method
    public static void main(String[] args) {

        String filename;
        Scanner scnr = new Scanner(System.in);

        // input the filename
        System.out.print("Enter a filename: ");
        filename = scnr.nextLine();
        scnr.close();

        // create a File object
        File file = new File(filename);

        try {

            // open the file in read mode
            scnr = new Scanner(file);

            // create an empty array list to store rating of each record in file
            ArrayList < Double > ratings = new ArrayList < Double > ();

            String line = scnr.nextLine(); // read and discard the first header line

            // loop over the file line by line
            while (scnr.hasNextLine()) {
                line = scnr.nextLine(); // read a line from file
                
                // split the line into array of String using comma as the delimiter
                String[] fields = line.split(","); 

                
                ratings.add(Double.parseDouble(fields[2]));
            }

            scnr.close(); // close the input file
            
            // sort the list in ascending order using the built-in sort method
            Collections.sort(ratings); 

            displayHistogram(ratings); // display the histogram

            // display number of records
            System.out.println("Total books rated: " + ratings.size());

            // display median, average and standard deviation of scores
            System.out.printf("Median score: %.1f\n", median(ratings));
            System.out.printf("Average score: %.1f\n", average(ratings));
            System.out.printf("Standard Deviation: %.2f\n", standardDeviation(ratings));

        }
        catch (FileNotFoundException e) {
            // file not found, display error and exit the program
            System.out.println("ERROR - File " + filename + " not found");
        }

    }

}

