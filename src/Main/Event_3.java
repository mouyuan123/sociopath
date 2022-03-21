package Main;

import java.time.OffsetTime;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * @author houren
 */
public class Event_3 {

    int totalP = 0;
    ArrayList<Integer[]> integerArrays = new ArrayList<>();
    ArrayList<Student> finalList = new ArrayList<>();
    int totalMeet = 0;

    public Event_3(Student s) {
        go(s);
    }

    public void go(Student s) {
        ArrayList<Student> list = StudentList.studentList;
        ArrayList<Student> similarityList = new ArrayList<>();
        int num = StudentList.studentList.indexOf(s);
        Student a = s;
        list.remove(a);
        for (int i = 0; i < list.size(); i++) {
            Student temp = list.get(i);
            if (temp.getLunchStartTime().compareTo(a.getLunchStartTime()) >= 0 && temp.getLunchEndTime().compareTo(a.getLunchStartTime()) == 1 && temp.getLunchStartTime().compareTo(a.getLunchEndTime()) == -1) {
                similarityList.add(temp);
            }
        }

        int size = similarityList.size();
        int[] sizeArray = new int[size];
        for (int i = 0; i < size; i++) {
            sizeArray[i] = i;
        }

        getPermutations(sizeArray);

//        Start check which is the best
        for (int i = 0; i < totalP; i++) {
            ArrayList<Student> loopList = (ArrayList<Student>) similarityList.clone();
            ArrayList<Student> permutedLoopList = new ArrayList<>();
            ArrayList<Student> metStudent = new ArrayList<>();

            for (int j = 0; j < loopList.size(); j++) {
                permutedLoopList.add(loopList.get(integerArrays.get(i)[j]));
            }
            ArrayList<OffsetTime> timeSlot = (ArrayList<OffsetTime>) a.getTimeSlot().clone();
            ArrayList<OffsetTime> tempTimeSlot = (ArrayList<OffsetTime>) a.getTimeSlot().clone();
            int currentMeet = 0;

            for (int j = 0; j < permutedLoopList.size(); j++) {
                Student temp = permutedLoopList.get(j);
                OffsetTime start = temp.getLunchStartTime();
                boolean success = false;

                for (int k = 0; k < temp.getDuration().toMinutes(); k++) {
                    if (start.compareTo(a.getLunchEndTime()) >= 0) {
                        break;
                    } else {
                        if (timeSlot.contains(start)) {
                            timeSlot.remove(start);
                            tempTimeSlot.add(start);
                            success = true;
                        } else {
                            timeSlot.addAll(tempTimeSlot);
                            success = false;
                            break;
                        }
                    }
                    start = start.plusMinutes(1);
                }

                if (success) {
                    metStudent.add(temp);
                    currentMeet++;
                }
                tempTimeSlot.clear();
                if (currentMeet > totalMeet) {
                    finalList = (ArrayList<Student>) metStudent.clone();
                    totalMeet = currentMeet;
                }

            }
            metStudent.clear();
        }

        for (int i = 0; i < finalList.size(); i++) {
            if (a.getFriends().contains(finalList.get(i))) {
                a.setReputation(finalList.get(i), a.getReputation().get(finalList.get(i)) + 1);
                finalList.get(i).setReputation(a, finalList.get(i).getReputation().get(a) + 1);
            } else {
                a.addFriends(finalList.get(i));
                finalList.get(i).addFriends(a);
                a.setReputation(finalList.get(i), 2);
                finalList.get(i).setReputation(a, 2);
            }

        }

        StudentList.studentList.add(num, a);
        allRepList(StudentList.studentList);
    }

    public ArrayList<Student> getFinalList() {
        return finalList;
    }

    public int getTotalMeet() {
        return totalMeet;
    }

    public void allRepList(ArrayList<Student> a) {
        for (int i = 0; i < a.size(); i++) {
            a.get(i).getRep();
        }
    }

    public void getPermutations(int[] array) {
        helper(array, 0);
    }

    private void helper(int[] array, int pos) {

        if (pos >= array.length - 1) {
            Integer[] temp = Arrays.stream(array).boxed().toArray(Integer[]::new);
            integerArrays.add(temp);
            totalP++;
            return;
        }

        for (int i = pos; i < array.length; i++) {

            int t = array[pos];
            array[pos] = array[i];
            array[i] = t;

            helper(array, pos + 1);

            t = array[pos];
            array[pos] = array[i];
            array[i] = t;
        }
    }

}
