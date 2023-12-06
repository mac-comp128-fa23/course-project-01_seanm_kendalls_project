import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AreasOfStudy {
    HashMap<String, ArrayList> areasOfStudyMap=new HashMap<>();
    ArrayList<String> listOfDegrees= new ArrayList<String>();


    public AreasOfStudy(){
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
        ALL.add("ECON 119");
        ALL.add("COMP 123");
        ALL.add("AMST 100");


        this.areasOfStudyMap.put("ALL", ALL);
        this.areasOfStudyMap.put("ECON", ECON);
        this.areasOfStudyMap.put("COMP", COMP);
        this.areasOfStudyMap.put("AMST", AMST);
    }
    
    
}
