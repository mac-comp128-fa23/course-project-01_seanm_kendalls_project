package degreeHelperProject;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException;
import java.io.File;

/**
 * The AreasOfStudy class organizes a csv file of Macalester's courses into an ArrayList
 * organizes Macalester's areas of study data and their requirements into HashMaps.
 * 
 * The code to iterate through files in a directory is modified from Mike Samuel on stackoverflow: https://stackoverflow.com/a/4917347.
 * 
 * The code to read a csv file modified from https://www.javatpoint.com/how-to-read-csv-file-in-java
 * 
 * @author Kendall Sullivan
 * @author Sean Maxfield
 * 
 */
public class AreasOfStudy {
    private static HashMap<String, HashMap<String, String>> areasOfStudyMap;
    private static ArrayList<String> allCourses;

    /**
     * Initializes the instance variables and runs functions to create list of all courses
     * and areas of study.
     */
    public AreasOfStudy(){
        allCourses = new ArrayList<>();
        areasOfStudyMap = new HashMap<>(); 

        makeAllCoursesList();
        makeAreasOfStudy();
    }

    /**
     * Reads course list csv file and adds the name of the course, the element at index 1 of
     * each line of the csv file, to the list. The list will be used to check the user's inputted
     * classes against in the DegreeHelper class.
     * 
     * @return an ArrayList of Strings representing courses.
     */
    public static ArrayList<String> makeAllCoursesList() {
        String line = "";  
        String splitBy = ","; 
        try {
            BufferedReader br = new BufferedReader(new FileReader("All_Courses.csv"));  
            while ((line = br.readLine()) != null) {   
                String[] courses = line.split(splitBy); 
                allCourses.add(courses[1].toUpperCase()); 
            }  
            br.close();
        } catch (IOException e) {  
            e.printStackTrace();  
        }
        return allCourses;
    }

    /**
     * Reads through every file in our csv_files folder in order to input the possible required
     * courses for each area of study and their requirement group level to them to their
     * own HashMap. Adds that HashMap and the String designating the name of the area of study
     * to the HashMap with all areas of study. If a csv file has data for both a major and a minor
     * in that area of study, fills two HashMaps with corresponding data.
     * 
     * @return a HashMap holding a String representing the name of an area of study as the key and 
     * a HashMap with that area of study's required courses and their requirement group as the value.
     */
    public static HashMap<String, HashMap<String, String>> makeAreasOfStudy() {
        String line = "";  
        String splitBy = ",";

        File dir = new File("csv_files");
        File[] files = dir.listFiles();
        if (files != null) {
            for (File f : files) {
                try {
                    BufferedReader br = new BufferedReader(new FileReader(f));  
                    String firstLine = br.readLine();
                    String[] firstValues = firstLine.split(splitBy);
                    String keyName1 = null;
                    String keyName2 = null;
                    HashMap<String, String> reqs1 = new HashMap<>();
                    HashMap<String, String> reqs2 = new HashMap<>();
                    if (firstValues.length > 2) {
                        keyName1 = firstValues[0] + " Major";
                        keyName2 = firstValues[0] + " Minor";
                    } else {
                        keyName1 = firstValues[0] + " Concentration";
                    }
                    while ((line = br.readLine()) != null) {   
                        String[] courses = line.split(splitBy);
                        if (courses.length > 2) {
                            reqs1.put(courses[1], courses[2]);
                        }
                        if (keyName2 != null) {
                            if (courses.length > 3) {
                                reqs2.put(courses[1], courses[3]);
                            }
                        }
                    }
                    areasOfStudyMap.put(keyName1, reqs1);
                    if (keyName2 != null) {
                        areasOfStudyMap.put(keyName2, reqs2);
                    }
                    br.close();
                } catch (IOException e) {  
                    e.printStackTrace();  
                }

            }
        }
        return areasOfStudyMap;
    }

    /**
     * Returns an ArrayList holding the name of each Macalester course.
     */
    public ArrayList<String> getAllCourses() {
        return allCourses;
    }
    
    /**
     * Returns a HashMap holding each Macalester area of study and its requirements information.
     */
    public static HashMap<String, HashMap<String, String>> getAreasOfStudy() {
        return areasOfStudyMap;
    }
}
