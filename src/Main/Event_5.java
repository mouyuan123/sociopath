package Main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Event_5 {

    static Random r = new Random();
    private Student user;
    private Student crush;
    private Student firstRumor;
    private ArrayList<String> print = new ArrayList<>();

    public Event_5(Student user, Student crush) {
        this.user = user;
        this.crush = crush;
        go();

    }

    public void go() {
  
        firstRumor = randomRumor(user, crush);
        addRumor(firstRumor);
        setUser(user);

        print.add("User: " + user.getName()+"\n");
        print.add("Crush: " + crush.getName()+"\n");
        print.add("First rumor: " + firstRumor.getName()+"\n");
        
        int count = 1;
        
        if (checkCluster(crush)) {
            print.add("Action!\n\n");
            while (true) {                
                print.add("Day :"+count+"\n");
                spreadRumor();
                if (spreadToCrush()) {
                    print.add("RIP!\n");
                    break;
                }
                actionToConvince(user, crush);
                resetSearch(StudentList.studentList);
                if (StudentList.rumorList.isEmpty()) {
                    print.add("Rumor ended!\n");
                    break;
                }
            count++;    
            }
        } else {

            print.add("No crush in the rumor cluster!\n");
        }
        


    }

    public void convince(Student user, Student student) {
        print.add(user.getName() + " try to convince " + student.getName() + "!\n");
        if (Math.random() >= 0.5) {
            student.convinced = true;
            removeRumor(student);
            print.add(user.getName() + " successfully conviced " + student.getName() + "!\n");
            print.add(student.getName() + " stopped spreading rumor!\n");
        } else {
            print.add(user.getName() + " failed to convice " + student.getName() + "!\n");
            print.add(student.getName() + " continues to spread rumor!\n");
        }
        print.add("\n");
    }

    public boolean checkCluster(Student crush) {
        Queue<Student> q = new LinkedList();

        q.add(crush);

        while (!q.isEmpty()) {
            crush = q.remove();
            crush.searched = true;

            if (crush.isRumor()) {
                resetSearch(StudentList.studentList);
                return true;
            }

            ArrayList<Student> friends = crush.getFriends();

            for (int i = 0; i < friends.size(); i++) {
                if (!friends.get(i).searched) {
                    friends.get(i).searched = true;
                    q.add(friends.get(i));
                }
            }
        }
        resetSearch(StudentList.studentList);
        return false;
    }

    public void actionToConvince(Student user, Student crush) {

        Queue<Student> q = new LinkedList();

        q.add(crush);

        while (!q.isEmpty()) {
            crush = q.remove();
            crush.searched = true;

            if (crush.isRumor()) {
                convince(user, crush);
                return;
            }

            ArrayList<Student> friends = crush.getFriends();

            for (int i = 0; i < friends.size(); i++) {
                if (!friends.get(i).searched) {
                    friends.get(i).searched = true;
                    q.add(friends.get(i));
                }
            }
        }
    }

    public void resetSearch(ArrayList<Student> studentList) {
        for (int i = 0; i < studentList.size(); i++) {
            studentList.get(i).searched = false;
        }
    }

    public void spreadRumor() {
        if (!StudentList.rumorList.isEmpty()) {
            ArrayList<Student> currentRumorList = new ArrayList();
            for (int i = 0; i < StudentList.rumorList.size(); i++) {
                currentRumorList.add(StudentList.rumorList.get(i));
            }
            for (int i = 0; i < currentRumorList.size(); i++) {
                ArrayList<Student> connections = new ArrayList();;
                for (int count1 = 0; count1 < currentRumorList.get(i).getFriends().size(); count1++) {
                    connections.add(currentRumorList.get(i).getFriends().get(count1));
                }
                for (int count2 = 0; count2 < currentRumorList.get(i).getEnemy().size(); count2++) {
                    connections.add(currentRumorList.get(i).getEnemy().get(count2));
                }
                for (int count3 = 0; count3 < currentRumorList.get(i).getFrenemy().size(); count3++) {
                    if (!connections.contains(currentRumorList.get(i).getFrenemy().get(count3))) {
                        connections.add(currentRumorList.get(i).getFrenemy().get(count3));
                    }
                }

                for (int j = 0; j < connections.size(); j++) {
                    Student temp = connections.get(j);
                    if (temp.user) {
                        print.add(temp.getName() + " is the user himself!\n\n");
                        continue;
                    }
                    if (temp.isRumor()) {
                        print.add(currentRumorList.get(i).getName() + " spread to " + temp.getName() + ". However, " + temp.getName() + " already knows the rumor!\n\n");
                        continue;
                    }
                    if (temp.convinced) {
                        print.add(currentRumorList.get(i).getName() + " spread to " + temp.getName() + ". However, " + temp.getName() + " is already convinced not to spread the rumor!\n\n");
                        continue;
                    }
                    if (!temp.isRumor() && !temp.convinced) {
                        addRumor(temp);
                        print.add(currentRumorList.get(i).getName() + " spread to " + temp.getName() + ". " + temp.getName() + " knows the rumor!\n\n");
                    }

                }
            }

        }
    }

    public void addRumor(Student rumor) {
        rumor.setRumor(true);
        StudentList.rumorList.add(rumor);
    }

    public void removeRumor(Student rumor) {
        rumor.setRumor(false);
        StudentList.rumorList.remove(rumor);
    }

    public boolean spreadToCrush() {
        if (crush.isRumor()) {
            print.add("Rumor spread to crush!\n");
            return true;
        } else {
            return false;
        }
    }

    public void setUser(Student user) {
        user.user = true;
    }

    public Student randomRumor(Student user, Student crush){
        int rumorIndex;
        int userIndex = StudentList.studentList.indexOf(user);
        int crushIndex = StudentList.studentList.indexOf(crush);
        while(true){
            rumorIndex = r.nextInt(10);
            if(rumorIndex != userIndex && rumorIndex != crushIndex){
                break;
            }
        }
        return StudentList.studentList.get(rumorIndex);
    }


    public ArrayList<String> getPrint() {
        return print;
    }
    
    
}
