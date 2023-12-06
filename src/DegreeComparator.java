import java.util.ArrayList;

public class DegreeComparator {
    static String DegreeCompletionReport(AreasOfStudy areasOfStudy, ArrayList<String> classesTaken){
        String totalPrint="Degree Completion Report";
        for(String s: areasOfStudy.areasOfStudyMap.keySet()){

            ArrayList<String> classArray = areasOfStudy.areasOfStudyMap.get(s);
            System.out.println(s+"\n"+classArray);
            ArrayList<String> yetToTakeArray = new ArrayList<String>();
            Integer tempInt=classArray.size();
            for(String c: classesTaken ){
                String department=c.substring(0, 4);
                System.out.println(department);
                String classCode=c.substring(4,7);
                System.out.println(classCode);
                if (s==department && classArray.contains(classCode)){
                    System.out.println(tempInt);
                    tempInt=tempInt-1;
                    System.out.println(tempInt);
                }
            }
            for (String v: classArray){
                if (!classesTaken.contains(v)){
                    yetToTakeArray.add(v);
                }
            }
            
            String degreeSummary="\nFor the "+s+" major:\n  You still need to take "+tempInt+" more classes \n You are "+(classArray.size()/tempInt)*100+"% complete\nHere are the other classes you must take:"+yetToTakeArray;
            totalPrint+=degreeSummary;
        }
        return totalPrint;
    }
}
