import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DegreeComparator {

    public DegreeComparator(ArrayList<String> userClasses) {
        HashMap<String, HashMap<String, String>> areasOfStudy = AreasOfStudy.getAreasOfStudy();

        for (Map.Entry<String, HashMap<String, String>> area : areasOfStudy.entrySet()) {
            String areaName = area.getKey();
            //HashMap<String, String> courses = area.getValue();
            for (Map.Entry<String, String> course : area.getValue().entrySet()) {
                if (userClasses.contains(course)) {
                    if (course.getValue().equals("1")) {

                    }
                }
            }
        }




    }


    // static String DegreeCompletionReport(AreasOfStudy areasOfStudy, ArrayList<String> classesTaken){
    //     String totalPrint="Degree Completion Report";
    //     for(String s: areasOfStudy.areasOfStudyMap.keySet()){

    //         ArrayList<String> classArray = AreasOfStudy.areasOfStudyMap.get(s);
            
    //         ArrayList<String> yetToTakeArray = classArray;
    //         Integer tempInt=classArray.size();
    //         for (String x: classArray) {
    //             System.out.println(s+"\n"+x);
    //             for(String c: classesTaken ){
    //                 String department=c.substring(0, 4);
    //                 System.out.println(department);
    //                 Integer classCode=Integer.parseInt(c.substring(4,7));
    //                 System.out.println(classCode);
    //                 if (classCode==Integer.parseInt(x)){
    //                     System.out.println(x+"ClassTaken!!");
    //                     //yetToTakeArray.remove(yetToTakeArray.indexOf(x));
    //                     tempInt=tempInt-1;
    //                     System.out.println(tempInt);
    //                 }
                    
                    
    //             }
    //         }
           
    //         // for (String v: classArray){
    //         //     if (!classesTaken.contains(v)){
    //         //         yetToTakeArray.add(v);
    //         //     }
    //         // }
            
    //         Double percent=(double) ((((double) classArray.size()-tempInt)/((double) classArray.size()))*100);
    //         String degreeSummary="\nFor the "+s+" major:\n  You still need to take "+tempInt+" more classes \n You are "+percent+"% complete\nHere are the other classes you must take:"+yetToTakeArray;
    //         totalPrint+=degreeSummary;
    //     }
    //     return totalPrint;
   //}
}
