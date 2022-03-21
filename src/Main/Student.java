package Main;

import java.time.Duration;
import java.time.OffsetTime;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ImageIcon;

/*
 * @author houren
 */
public class Student implements Comparable<Student> {

    private HashMap<Student, Integer> reputation = new HashMap<>();
    private Integer studentNum;
    private String name;
    private OffsetTime lunchStartTime; //time 
    private OffsetTime lunchEndTime;
    private long lunchPeriod;    //minute
    private Duration interval;
    private ArrayList<OffsetTime> timeSlot;
    private ArrayList<Student> enemy = new ArrayList<>();
    private ArrayList<Student> frenemy = new ArrayList<>();
    private ImageIcon imageIcon;
    private int divingRate;
    private ArrayList<Student> friends = new ArrayList<>();
    private boolean isProgrammingExpert;
    private boolean rumor;
    boolean searched = false;
    boolean convinced = false;
    boolean user = false;

    public Student(Integer studentNum, String name, OffsetTime lunchStartTime, long lunchPeriod, int divingRate, boolean isProgrammingExpert) {
        this.studentNum = studentNum;
        this.name = name;
        this.lunchStartTime = lunchStartTime;
        this.lunchPeriod = lunchPeriod;
        this.divingRate = divingRate;
        this.isProgrammingExpert = isProgrammingExpert;
        this.lunchEndTime = lunchStartTime.plusMinutes(lunchPeriod);
        this.interval = Duration.between(lunchStartTime, lunchEndTime);
        this.timeSlot = timeSlot();
        this.imageIcon = new ImageIcon(getClass().getResource("/swing/images/Student" + this.getStudentNum() + ".gif"));
    }

    public ArrayList<Student> getEnemy() {
        return enemy;
    }

    public void setEnemy(ArrayList<Student> enemy) {
        this.enemy = enemy;
    }

    public ArrayList<Student> getFrenemy() {
        return frenemy;
    }

    public void setFrenemy(ArrayList<Student> frenemy) {
        this.frenemy = frenemy;
    }

    public Duration getDuration() {
        return interval;
    }

    public Integer getStudentNum() {
        return studentNum;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public OffsetTime getLunchEndTime() {
        return lunchEndTime;
    }

    public HashMap<Student, Integer> getReputation() {
        return reputation;
    }

    public void setReputation(Student student, int rep) {
        if(rep>=10)
            this.reputation.put(student, 10);
        else
        this.reputation.put(student, rep);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OffsetTime getLunchStartTime() {
        return lunchStartTime;
    }

    public void setLunchStartTime(OffsetTime lunchStartTime) {
        this.lunchStartTime = lunchStartTime;
    }

    public long getLunchPeriod() {
        return lunchPeriod;
    }

    public void setLunchPeriod(long lunchPeriod) {
        this.lunchPeriod = lunchPeriod;
    }

    public int getDivingRate() {
        return divingRate;
    }

    public void setDivingRate(int divingRate) {
        this.divingRate = divingRate;
    }

    public ArrayList<Student> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<Student> friends) {
        this.friends = friends;
    }

    public boolean isIsProgrammingExpert() {
        return isProgrammingExpert;
    }

    public void setIsProgrammingExpert(boolean isProgrammingExpert) {
        this.isProgrammingExpert = isProgrammingExpert;
    }

    public void print() {
        System.out.println("Name: " + getName());
        System.out.println("Lunch Time: " + getLunchStartTime());
        System.out.println("Lunch Period: " + getLunchPeriod());
        System.out.println("Diving Rate: " + getDivingRate());
        System.out.println("isProgrammingExpert: " + isIsProgrammingExpert());
        getRep();

    }

    public void getRep() {
        for (Student i : reputation.keySet()) {
            System.out.println("Student: " + i.getName() + " Rep: " + reputation.get(i));
        }

    }

    public void addFriends(Student a) {
        this.friends.add(a);
    }

    public void addFrenemy(Student a) {
        this.frenemy.add(a);
    }

    public void addEnemy(Student a) {
        this.enemy.add(a);
    }

    public ArrayList<OffsetTime> timeSlot() {
        ArrayList<OffsetTime> list = new ArrayList<>();
        boolean added = false;
        OffsetTime a = lunchStartTime;
        list.add(a);
        list.add(a);
        list.add(a);
        do {
            added = false;
            a = a.plusMinutes(1);
            if (a.compareTo(lunchEndTime) == -1) {
                list.add(a);
                list.add(a);
                list.add(a);
                added = true;
            }

        } while (added);

        return list;

    }

    public void getFriendList() {
        System.out.print(getName() + "'s friend list : [ ");
        for (int i = 0; i < friends.size(); i++) {
            if (i == friends.size() - 1) {
                System.out.println(friends.get(i).getName() + " ] ");
            } else {
                System.out.print(friends.get(i).getName() + ", ");
            }
        }
    }

    public void getFrenemyList() {
        if (frenemy.isEmpty()) {
            return;
        }
        System.out.print(getName() + "'s frenemy list : [ ");
        for (int i = 0; i < frenemy.size(); i++) {
            if (i == frenemy.size() - 1) {
                System.out.println(frenemy.get(i).getName() + " ] ");
            } else {
                System.out.print(frenemy.get(i).getName() + ", ");
            }
        }
    }

    public void getEnemyList() {
        if (enemy.isEmpty()) {
            return;
        }
        System.out.print(getName() + "'s enemy list : [ ");
        for (int i = 0; i < enemy.size(); i++) {
            if (i == enemy.size() - 1) {
                System.out.println(enemy.get(i).getName() + " ] ");
            } else {
                System.out.print(enemy.get(i).getName() + ", ");
            }
        }
    }

    public ArrayList<OffsetTime> getTimeSlot() {
        return timeSlot;
    }

    public boolean inRepList(Student b) {
        return this.getReputation().containsKey(b);
    }

    public void setRumor(boolean rumor) {
        this.rumor = rumor;
    }

    public boolean isRumor() {
        return rumor;
    }

    @Override
    public String toString() {
        return "Student{" + "name=" + name + ", lunchStartTime=" + lunchStartTime + ", lunchEndTime=" + lunchEndTime + ", lunchPeriod=" + lunchPeriod + ", interval=" + interval + ", divingRate=" + divingRate + ", isProgrammingExpert=" + isProgrammingExpert + '}';
    }

    @Override
    public int compareTo(Student o) {
        return o.studentNum.compareTo(studentNum);
    }

}
