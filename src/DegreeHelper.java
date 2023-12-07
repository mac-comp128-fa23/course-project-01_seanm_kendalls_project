import java.util.ArrayList;
import java.util.Scanner;

public class DegreeHelper {
    private static ArrayList<String> classesTaken;
   
    public DegreeHelper(ArrayList<String> classes){
        this.classesTaken=classes;

    }

    public ArrayList<String> inputClasses() {
        classesTaken = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a course you have taken (e.g. COMP 128): ");
        while (sc.nextLine() != "q") {
            String inp = sc.nextLine();
            
        }

        return classesTaken;
    }


    public ArrayList<String> getClassesTaken(){
        return this.classesTaken;
    }


    public static void main(String[] args) {
        // ArrayList<String> classesTak=new ArrayList<String>();
        // classesTak.add("COMP123");
        // classesTak.add("ECON119");
        DegreeHelper degreeHelper= new DegreeHelper(classesTaken);
        //degreeHelper.classesTaken=classesTak;
        AreasOfStudy degreesMaps= new AreasOfStudy();

        System.out.println(DegreeComparator.DegreeCompletionReport(degreesMaps,classesTaken));

    }
}
