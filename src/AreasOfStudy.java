import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.io.*;
import java.util.Scanner;
import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException;
import java.io.File;


public class AreasOfStudy {
    public static HashMap<String, HashMap<String, String>> areasOfStudyMap=new HashMap<>();
    private static ArrayList<String> listOfDegrees= new ArrayList<String>();
    private static ArrayList<String> allCourses;

    public AreasOfStudy(){
        allCourses = new ArrayList<>();
        areasOfStudyMap = new HashMap<>(); 

        makeAllCoursesList();

        


        //old code
        //Scanner sc= new Scanner(new File("fakeData"));
        // this.listOfDegrees.add("COMP");
        // this.listOfDegrees.add("AMST");
        // this.listOfDegrees.add("ECON");

        // ArrayList<String> ECON= new ArrayList<String>();
        // ECON.add("119");
        // ArrayList<String> COMP= new ArrayList<String>();
        // COMP.add("123");
        // ArrayList<String> AMST= new ArrayList<String>();
        // AMST.add("100");
        // ArrayList<String> ALL= new ArrayList<String>();
        // ALL.add("119");
        // ALL.add("123");
        // ALL.add("100");

        // this.areasOfStudyMap.put("ALL", ALL);
        // this.areasOfStudyMap.put("ECON", ECON);
        // this.areasOfStudyMap.put("COMP", COMP);
        // this.areasOfStudyMap.put("AMST", AMST);

        //     String line = "";  
        //     String splitBy = ",";  
        //     try   
        //     {  
        //     //parsing a CSV file into BufferedReader class constructor  
        //     BufferedReader br = new BufferedReader(new FileReader("Areas_of_Study_-_AMST_Major_&_Minor.csv"));  
        //     ArrayList<String[]> departmentArray=new ArrayList<String[]>();
        //     while ((line = br.readLine()) != null)   //returns a Boolean value  
        //     {  
                
        //     String[] classInArea = line.split(splitBy);    // use comma as separator  
        //    // System.out.println("Class [Course Code=" + classInArea[1] + ", Required For Major=" + classInArea[2] + ", Required for Minor=" + classInArea[3] + "]");  
        //     }  
        //     }   
        //     catch (IOException e)   
        //     {  
        //     e.printStackTrace();  
        //     } 
        

    //System.out.println(allCourses);
    }

    public ArrayList<String> makeAllCoursesList() {
        String line = "";  
        String splitBy = ","; 
        try {
            BufferedReader br = new BufferedReader(new FileReader("All_Courses.csv"));  
            while ((line = br.readLine()) != null) {   
                String[] courses = line.split(splitBy); 
                allCourses.add(courses[1]); 
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
                    HashMap<String, String> reqs = new HashMap<>();
                    if (firstValues[4] != null) {
                        keyName1 = firstValues[1] + "Major";
                        //areasOfStudyMap.put(keyName1, null);
                        keyName2 = firstValues[1] + "Minor";
                        //areasOfStudyMap.put(keyName2, null);
                    } else {
                        keyName1 = firstValues[1] + "Concentration";
                        //areasOfStudyMap.put(keyName1, null);
                    }
                    while ((line = br.readLine()) != null) {   
                        String[] courses = line.split(splitBy);
                        reqs.put(courses[1], courses[2]);
                        //next: make a new hasmap to modify the name entry in areasofstudy map
                    }  
                    areasOfStudyMap.put(keyName1, reqs);
                    if (keyName2 != null) {
                        areasOfStudyMap.put(keyName2, reqs);
                    }
                    br.close();
                } catch (IOException e) {  
                    e.printStackTrace();  
                }
            }
        }
        System.out.println(areasOfStudyMap);
        return areasOfStudyMap;
    }
    
    public static ArrayList<String> getAllCourses() {
        //System.out.println(allCourses);
        return allCourses;
    }

    public static HashMap<String, HashMap<String, String>> getAreasOfStudy() {
        return areasOfStudyMap;
    }

    public static void main(String[] args) {
        //makeAreasOfStudy();
        System.out.println(getAreasOfStudy());
    }
}
