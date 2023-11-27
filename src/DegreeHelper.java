import java.util.ArrayList;

public class DegreeHelper {
    ArrayList<Integer> classesTaken;
    public DegreeHelper(ArrayList<Integer> classes){
        this.classesTaken=classes;

    }
    public ArrayList<Integer> getClassesTaken(){
        return this.classesTaken;
    }
    public static void main(String[] args) {
        ArrayList<Integer> classesTak=new ArrayList<Integer>();
        classesTak.add(128);
        classesTak.add(119);
        classesTak.add(240);
        DegreeHelper degreeHelper= new DegreeHelper(classesTaken);
        degreeHelper.classesTaken=classesTak;
        AreasOfStudy degreesMaps= new AreasOfStudy();


    }
}
