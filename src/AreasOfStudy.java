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



public class AreasOfStudy {
    HashMap<String, ArrayList> areasOfStudyMap=new HashMap<>();
    ArrayList<String> listOfDegrees= new ArrayList<String>();
    private static ArrayList<String> allCourses;

    public AreasOfStudy(){
        allCourses = new ArrayList<>();
        //Scanner sc= new Scanner(new File("fakeData"));
        this.listOfDegrees.add("COMP");
        this.listOfDegrees.add("AMST");
        this.listOfDegrees.add("ECON");

        ArrayList<String> ECON= new ArrayList<String>();
        ECON.add("119");
        ArrayList<String> COMP= new ArrayList<String>();
        COMP.add("123");
        ArrayList<String> AMST= new ArrayList<String>();
        AMST.add("100");
        ArrayList<String> ALL= new ArrayList<String>();
        ALL.add("119");
        ALL.add("123");
        ALL.add("100");


        this.areasOfStudyMap.put("ALL", ALL);
        this.areasOfStudyMap.put("ECON", ECON);
        this.areasOfStudyMap.put("COMP", COMP);
        this.areasOfStudyMap.put("AMST", AMST);


        String line = "";  
        String splitBy = ",";  
        try   
        {  
        //parsing a CSV file into BufferedReader class constructor  
        BufferedReader br = new BufferedReader(new FileReader("Areas_of_Study_-_AMST_Major_&_Minor.csv"));  
        ArrayList<String[]> departmentArray=new ArrayList<String[]>();
        while ((line = br.readLine()) != null)   //returns a Boolean value  
        {  
            
        String[] classInArea = line.split(splitBy);    // use comma as separator  
       // System.out.println("Class [Course Code=" + classInArea[1] + ", Required For Major=" + classInArea[2] + ", Required for Minor=" + classInArea[3] + "]");  
        }  
        }   
        catch (IOException e)   
        {  
        e.printStackTrace();  
        } 

        makeAllCoursesList();
        

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
        }
        catch (IOException e) {  
            e.printStackTrace();  
        }
        //System.out.println(allCourses);
        return allCourses;
    }
    
    public ArrayList<String> getAllCourses() {
        //System.out.println(allCourses);
        return allCourses;
    }
}
