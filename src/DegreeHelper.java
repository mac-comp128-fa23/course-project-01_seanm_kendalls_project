import java.util.ArrayList;

public class DegreeHelper {
    ArrayList<String> classesTaken;
    public DegreeHelper(ArrayList<String> classes){
        this.classesTaken=classes;

    }
    public ArrayList<String> getClassesTaken(){
        return this.classesTaken;
    }
    public static void main(String[] args) {
        ArrayList<String> classesTak=new ArrayList<String>();
        classesTak.add("COMP123");
        classesTak.add("ECON119");
        DegreeHelper degreeHelper= new DegreeHelper(classesTak);
        //degreeHelper.classesTaken=classesTak;
        AreasOfStudy degreesMaps= new AreasOfStudy();

        System.out.println(DegreeComparator.DegreeCompletionReport(degreesMaps,classesTak));

    }
}
