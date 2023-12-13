import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException;
import java.io.File;


public class AreasOfStudy {
    private static HashMap<String, HashMap<String, String>> areasOfStudyMap;
    //private static ArrayList<String> listOfDegrees= new ArrayList<String>();
    private static ArrayList<String> allCourses;

    public AreasOfStudy(){
        allCourses = new ArrayList<>();
        areasOfStudyMap = new HashMap<>(); 

        makeAllCoursesList();

        makeAreasOfStudy();
        

    }

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
        //System.out.println(allCourses);
        return allCourses;
    }

    public static HashMap<String, HashMap<String, String>> makeAreasOfStudy() {
        String line = "";  
        String splitBy = ",";
       
        //the code to iterate through files in a directory is modified from Mike Samuel on stackoverflow: https://stackoverflow.com/a/4917347
        File dir = new File("csv_files");
        File[] files = dir.listFiles();
        if (files != null) {
            for (File f : files) {
                //this code is modified from *insert link*
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
                        // for (String course : courses) {
                        //     System.out.println(course);
                        // }
                        if (courses.length > 1) {
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
        // System.out.println(areasOfStudyMap);
        // for (HashMap<String, String> value : areasOfStudyMap.values()) {
        //     System.out.println(value.size());
        // }
        return areasOfStudyMap;
    }
    
    public ArrayList<String> getAllCourses() {
        //System.out.println(allCourses);
        return allCourses;
    }

    public static HashMap<String, HashMap<String, String>> getAreasOfStudy() {
        return areasOfStudyMap;
    }

    // public static void main(String[] args) {
    //     new AreasOfStudy();
    //     System.out.println(getAreasOfStudy());
    // }
}
