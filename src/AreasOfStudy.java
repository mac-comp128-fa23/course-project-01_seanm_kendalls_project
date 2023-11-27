import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AreasOfStudy {
    HashMap<String, ArrayList> areasOfStudyList;
    ArrayList<String> listOfDegrees= new ArrayList<String>();


    public AreasOfStudy(){
        this.areasOfStudyList=new HashMap<ArrayList>();
        this.areasOfStudyList.add(classesInDegree(null));
    }

    private ArrayList classesInDegree(String degree) {
       ArrayList<String> classesInDegreeMap = new ArrayList<String>();
       classesInDegreeMap.add("Com");
       classesInDegreeMap.put(128,"Comp 128");
       classesInDegreeMap.put(240,"Comp 240");
       return classesInDegreeMap;

    }
    
    
}
