package degreeHelperProject;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The DegreeHelper class has a main method that runs the entire program. It also runs
 * a scanner that takes a user's inputted classes and it puts them into an ArrayList.
 * 
 * @author Kendall Sullivan
 * 
 */
public class DegreeHelper {
    private static ArrayList<String> classesTaken;

    /**
     * Initializes the classesTaken instance variable. Uses a scanner to ask the user to input all
     * courses they have taken at Macalester. Checks that the course is in the list of all courses
     * and that the course has not already been inputted by the user.
     * 
     * @param areasOfStudy an AreasOfStudy object used to access all courses offered at Macalester.
     * @return an ArrayList of Strings representing the user's inputted classes.
     */
    public static ArrayList<String> inputClasses(AreasOfStudy areasOfStudy) {
        classesTaken = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a course you have taken (e.g. COMP 128): ");
        while (sc.hasNextLine()) {
            String inp = sc.nextLine();
            if (inp.equals("q")) {
                break;
            }
            inp = inp.toUpperCase().trim();
            if (!areasOfStudy.getAllCourses().contains(inp)) {
                System.out.println("The course you entered is not in the Course Catalog. Please enter a valid course: ");
            } else if (classesTaken.contains(inp)) {
                System.out.println("You already entered that course. Please enter a different course: ");
            } else if (areasOfStudy.getAllCourses().contains(inp)) {
                classesTaken.add(inp);
                System.out.println("Enter another course you have taken (enter 'q' after entering all of your courses): ");
            }
        }
        sc.close();
        return classesTaken;
    }

    /**
     * Main method that gets the user's courses and compares them to each area of study
     * at Macalester using the DegreeComparator class.
     */
    public static void main(String[] args) {
        AreasOfStudy areasOfStudy = new AreasOfStudy();
       
        inputClasses(areasOfStudy);
       
        new DegreeComparator(classesTaken);
    }
}
