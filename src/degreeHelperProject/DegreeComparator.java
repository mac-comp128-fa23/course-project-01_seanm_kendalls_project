package degreeHelperProject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * The DegreeHelper class has a main method that runs the entire program. It also runs
 * a scanner that takes a user's inputted classes and it puts them into an ArrayList.
 * 
 * @author Kendall Sullivan
 * @author Sean Maxfield
 * 
 */
public class DegreeComparator {
    private static HashMap<String, HashMap<String, String>> areasOfStudy = AreasOfStudy.getAreasOfStudy();
    private static HashMap<String, Integer> compareCourses = new HashMap<>();
    private static int val = 0;
    public int numCoursesLeftForClosestStudy_ForTest=0;

    /**
     * Loops through each course in each area of study and compares it to each course from the list
     * of classes the user inputted. Puts the number of courses the user has left to take for each
     * area of study into a HashMap with the area's name and finds which area(s) they are closest
     * to finishing.
     * 
     * @param userClasses an ArrayList of Strings representing the user's inputted classes.
     */
    public DegreeComparator(ArrayList<String> userClasses) {
        System.out.println("\nDegree Completion Report:");

        HashMap<String, Integer> reports = new HashMap<>();

        for (Map.Entry<String, HashMap<String, String>> area : areasOfStudy.entrySet()) {
            String areaName = area.getKey();
            compareCourses = setUpCompareCourses(area);

            for (Map.Entry<String, String> course : area.getValue().entrySet()) {
                compareCourses = compareUserCourses(compareCourses, course, userClasses);
            }
    
            int num = 0;
            for (Map.Entry<String, Integer> c : compareCourses.entrySet()) {
                int currNum = (Integer.parseInt(c.getKey().substring(2, 3)) - c.getValue());
                num += currNum;
            }

            reports.put(areaName, num);

        }
        findClosestAreas(reports);
    }

    /**
     * For each entry set in an area of study, gets the first three characters of the value and adds it
     * with its course name to a HashMap if it is unique.
     * 
     * @param area a HashMap of Strings mapping to Strings representing the course names of an area of
     * study mapping to their String value used to indicate how the course is required in the area.
     * @return a HashMap of Strings mapping to integers representing a the first three characters of its
     * value in the area parameter, which represents the requirement group and the number of courses
     * required for that group, mapping to an integer value of zero representing the number of courses the
     * user has taken in that group.
     */
    public HashMap<String, Integer> setUpCompareCourses(Map.Entry<String, HashMap<String, String>> area) {
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
        return compareCourses;
    }

    /**
     * Checks if the list of the user's courses contains a specific course from an area of study and if so,
     * increases its value in CompareCourses, as long as it is not already at the max value based on the number
     * of required courses parsed from the course's entry value.
     * 
     * @param compareCourses a HashMap of Strings mapping to Integers representing the number of courses required
     * for a requirement group mapping to the number of courses a user has taken in that requirement group
     * @param course a HashMap entry of Strings representing a course naame and its requirement group value.
     * @param userClasses an ArrayList of Strings representing the inputted courses a user has taken.
     * @return compareCourses, with updated values. 
     */
    public HashMap<String, Integer> compareUserCourses(HashMap<String, Integer> compareCourses, Map.Entry<String, String> course, ArrayList<String> userClasses) { 
        if (userClasses.contains(course.getKey().toUpperCase().trim())) {
            String userCode = course.getValue();
            if (userCode.length() > 1) {
                if (userCode.length() == 3) {
                    val = compareCourses.get(userCode);
                } else {
                    val = compareCourses.get(userCode.substring(0, 3));
                }
                if (val == Integer.parseInt(userCode.substring(2, 3))) {
                    if (userCode.length() > 3) {
                        int x=0;
                        while (x==0){
                            for(Character c : userCode.substring(3).toCharArray()){
                                String key="";
                                for (String k : compareCourses.keySet()){
                                    if (Integer.parseInt(k.substring(0,1))==Integer.parseInt(c.toString())){
                                        key=k;
                                    }
                                }
                                int val=0;
                                if (compareCourses.keySet().contains(key)){
                                    val= compareCourses.get(key);
                                }
                                if(val!=Integer.parseInt(key.substring(2,3))){

                                    compareCourses.put(key, val+1);
                                    x=1;
                                }
                            }
                        }
                        
                    }
                   
                } else if (val != Integer.parseInt(userCode.substring(2, 3))) {
                    val++;
                    compareCourses.put(userCode.substring(0, 3), val);
                }
            }
        }
        return compareCourses;
    }

    /**
     * Takes in a HashMap of each area of study (String) and the number of courses (Integer) left for
     * the user to take in each area and compares them to each other, adding the name of the area to a
     * list of areas if the minimum number of classes left is the same as the previous minimum area.
     * Prints out the closest area(s) the user is to finishing and finds if there are any other requirements
     * for that area(s) and prints those as well.
     */
    public void findClosestAreas(HashMap<String, Integer> reports) {
        String closestArea = "";
        ArrayList<String> closestTies = new ArrayList<>();
        int minNum = 999;
        int tieNum = 999;
        System.out.println("\nNumber of courses left to take for each area: \n");
        for (Map.Entry<String, Integer> r : reports.entrySet()) {
            String currArea = r.getKey();
            int currNum = r.getValue();
            System.out.println(currArea + ": " + currNum + " courses");
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
                closestTies.add(currArea);
            }
        }

        if (closestTies != null && closestTies.size() > 1) {
            System.out.println("\nThe degrees with the fewest number of courses you still need to take are: " + closestTies + ". You need to take " + tieNum + " more courses for each of these areas of study to finish each degree.\n");
            ArrayList<String> otherReqs = tiesGetOtherReqs(closestTies);
            numCoursesLeftForClosestStudy_ForTest=tieNum;
            if (otherReqs != null) {
                System.out.println("Please note these other aspects that are required for each of these areas of study: \n" + otherReqs);
            }
        } else {
            System.out.println("\nThe degree with the fewest number of courses you still need to take is: " + closestArea + ". You need to take " + minNum + " more courses for this area of study to finish this degree.\n");
            String otherReqs = oneGetOtherReqs(closestArea);
            numCoursesLeftForClosestStudy_ForTest=tieNum;
            if (otherReqs != null) {
                System.out.println("Please note these other aspects that are required for this area of study: \n" + otherReqs);
            }
        }
    }

    /**
     * Finds what other requirements (non-course related) there are from the entry in areasOfStudy for each
     * closest area.
     * 
     * @param closestTies an ArrayList of Strings representing areas of study the user is closest to finishing.
     * @return an ArrayList of Strings representing each other requirement for each closest area.
     */
    public ArrayList<String> tiesGetOtherReqs(ArrayList<String> closestTies) {
        ArrayList<String> otherReqs = new ArrayList<>();
        
        for (String area : closestTies) {
            ArrayList<String> tempReqs = new ArrayList<>();
            HashMap<String, String> areaReqs = areasOfStudy.get(area);
            for (Map.Entry<String, String> entry : areaReqs.entrySet()) {
                if (entry.getKey().startsWith("Other")) {
                   
                    tempReqs.add(entry.getValue());
                }
            }
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

    /**
     * Finds what other requirements (non-course related) there are from the entry in areasOfStudy for the
     * closest area.
     * 
     * @param closestTies a String representing the area of study the user is closest to finishing.
     * @return an ArrayList of Strings representing each other requirement.
     */
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
