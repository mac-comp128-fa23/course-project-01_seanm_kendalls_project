import java.util.ArrayList;
import java.util.Scanner;

public class DegreeHelper {
    private static ArrayList<String> classesTaken;
    private static AreasOfStudy degreesMaps;
   
    public DegreeHelper(ArrayList<String> classes){
        degreesMaps = new AreasOfStudy();
    }

    public static ArrayList<String> inputClasses() {
        classesTaken = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a course you have taken (e.g. COMP 128): ");
        while (sc.hasNextLine()) {
            String inp = sc.nextLine();
            if (inp.equals("q")) {
                break;
            }
            inp = inp.toUpperCase();
            if (!AreasOfStudy.getAllCourses().contains(inp)) {
                System.out.println("The course you entered is not in the Course Catalog. Please enter a valid course: ");
            } else if (classesTaken.contains(inp)) {
                System.out.println("You already entered that course. Please enter a different course: ");
            } else if (AreasOfStudy.getAllCourses().contains(inp)) {
                classesTaken.add(inp);
                System.out.println("Enter another course you have taken (enter 'q' after entering all of your courses): ");
            }
        }
        sc.close();
        return classesTaken;
    }

    public ArrayList<String> getClassesTaken(){
        return classesTaken;
    }


    public static void main(String[] args) {
        // ArrayList<String> classesTaken=new ArrayList<String>();
        // classesTaken.add("COMP123");
        // classesTak.add("ECON119");
        // degreeHelper.classesTaken=classesTak;


        // DegreeHelper degreeHelper= new DegreeHelper(classesTaken);
        // inputClasses();
        // System.out.println(classesTaken);


        //AreasOfStudy degreesMaps= new AreasOfStudy();
        //degreesMaps.getAllCourses();
        //System.out.println(DegreeComparator.DegreeCompletionReport(degreesMaps,classesTaken));

    }
}
