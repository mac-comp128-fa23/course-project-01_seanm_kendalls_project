import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DegreeComparator {
    private static AreasOfStudy areasObject = new AreasOfStudy();
    private static HashMap<String, HashMap<String, String>> areasOfStudy = areasObject.getAreasOfStudy();

    public DegreeComparator(ArrayList<String> userClasses) {
        System.out.println("Degree Completion Report:");

        HashMap<String, Integer> reports = new HashMap<>();

        for (Map.Entry<String, HashMap<String, String>> area : areasOfStudy.entrySet()) {
            String areaName = area.getKey();
            HashMap<String, Integer> compareCourses = new HashMap<>();

            for (Map.Entry<String, String> entry : area.getValue().entrySet()) {
                if (!entry.getKey().startsWith("Other")) {
                    String code = entry.getValue();
                    String currCode = "";
                    if (code.length() > 1) {
                        if (code.length() == 2) {
                            currCode = code;
                        } else {
                            currCode = code.substring(0, 3);
                        }
                        if (!compareCourses.keySet().contains(currCode)) {
                            compareCourses.put(currCode, 0);
                        }
                    }
                }
            }
            

            for (Map.Entry<String, String> course : area.getValue().entrySet()) {

                if (userClasses.contains(course.getKey())) {
                    String userCode = course.getValue();
                    if (userCode.length() > 1) {
                        int val = 0;
                        if (userCode.length() == 2) {
                            val = compareCourses.get(userCode);
                        } else {
                            val = compareCourses.get(userCode.substring(0, 3));
                        }
                        if (val == Integer.parseInt(userCode.substring(2, 3))) {
                            if (userCode.length() >= 3) {
                                Integer length = 3;
                                while (length <= userCode.length()) {
                                    String group = userCode.substring(length-1, length);
                                    for (String courseVal : compareCourses.keySet()) {
                                        if (courseVal.startsWith(group)) {
                                            val = compareCourses.get(courseVal);
                                            //this is very recursive-y. should we make it recursive? if so how.
                                            if (val != Integer.parseInt(userCode.substring(2, 3))) {
                                                val++;
                                                compareCourses.replace(userCode.substring(0, 3), val);
                                            }
                                            break;
                                        }
                                    }
                                    length++;
                                }
                            }
                        } else if (val != Integer.parseInt(userCode.substring(2, 3))) {
                            val++;
                            compareCourses.replace(userCode.substring(0, 3), val);
                        }
                    }

                }
            }

            int num = 0;
            for (Map.Entry<String, Integer> c : compareCourses.entrySet()) {
                int currNum = (Integer.parseInt(c.getKey().substring(2, 3)) - c.getValue());
                num += currNum;
            }

            reports.put(areaName, num);

        }

        String closestArea = "";
        ArrayList<String> closestTies = new ArrayList<>();
        int minNum = 999;
        int tieNum = 999;

        for (Map.Entry<String, Integer> r : reports.entrySet()) {
            String currArea = r.getKey();
            int currNum = r.getValue();
            if (currNum == 0) {
                System.out.println("You have taken already all of the courses required for the " + currArea + "!");
            } else if (currNum == minNum) {
                closestTies.add(currArea);
                tieNum = currNum;
            } else if (currNum < minNum) {
                minNum = currNum;
                closestArea = currArea;
                if (tieNum > minNum) {
                    closestTies.clear();
                }
            }
        }

        if (closestTies != null && closestTies.size() > 1) {
            System.out.println("The degrees with the fewest number of courses you still need to take are: " + closestTies + ". You need to take " + tieNum + " more courses for each of these areas of study to finish each degree.");
            ArrayList<String> otherReqs = tiesGetOtherReqs(closestTies);
            if (otherReqs != null) {
                System.out.println("Please note these other aspects that are required for each of these areas of study: \n" + otherReqs);
            }
        } else {
            System.out.println("The degree with the fewest number of courses you still need to take is: " + closestArea + ". You need to take " + minNum + " more courses for this area of study to finish this degree.");
            String otherReqs = oneGetOtherReqs(closestArea);
            if (otherReqs != null) {
                System.out.println("Please note these other aspects that are required for this area of study: \n" + otherReqs);
            }
        }

        //loop through each name and compare num, making lowest num closest.
        //print num classes left to complete for each area
        //if num is 0, print separate statement saying already complete
        //for closest areaOfStudy, print you need to take n more classes
            //print other requirements
            //areaOfStudy.get(areaName)
                //get entry sets where key starts with "Other"
                //print those out

    }


    public ArrayList<String> tiesGetOtherReqs(ArrayList<String> closestTies) {
        ArrayList<String> otherReqs = new ArrayList<>();
        
        for (String area : closestTies) {
            ArrayList<String> tempReqs = new ArrayList<>();
            HashMap<String, String> areaReqs = areasOfStudy.get(area);
          //  System.out.println(areaReqs);
            for (Map.Entry<String, String> entry : areaReqs.entrySet()) {
                if (entry.getKey().startsWith("Other")) {
                   // System.out.println(entry);
                    tempReqs.add(entry.getValue());
                }
            }
            //System.out.println("tempreqs: " + tempReqs);
            if (!tempReqs.isEmpty()) {
                otherReqs.add(area + " other requirements: " + tempReqs);
            }
        }
        if (otherReqs.isEmpty()) {
            return null;
        } else {
            return otherReqs;
        }
    }

    public String oneGetOtherReqs(String closestArea) {
        String otherReqs = "";
        ArrayList<String> tempReqs = new ArrayList<>();

        HashMap<String, String> areaReqs = areasOfStudy.get(closestArea);
        for (Map.Entry<String, String> entry : areaReqs.entrySet()) {
            if (entry.getKey().startsWith("Other")) {
                tempReqs.add(entry.getValue());
            }
        }
        if (!tempReqs.isEmpty()) {
            otherReqs = closestArea + " other requirements: " + tempReqs;
            return otherReqs;
        } else {
            return null;
        }
    }

}
